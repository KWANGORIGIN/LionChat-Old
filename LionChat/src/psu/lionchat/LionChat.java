package psu.lionchat;

import static com.github.messenger4j.Messenger.CHALLENGE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.MODE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.SIGNATURE_HEADER_NAME;
import static com.github.messenger4j.Messenger.VERIFY_TOKEN_REQUEST_PARAM_NAME;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.exception.MessengerVerificationException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.SenderActionPayload;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.send.senderaction.SenderAction;
import com.github.messenger4j.webhook.event.TextMessageEvent;

import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;
import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.ErieInfoIntent;
import psu.lionchat.intent.intents.GreetingIntent;
import psu.lionchat.intent.intents.UnknownIntent;

@RestController
@RequestMapping("/lionchat")
public class LionChat {
	private final Messenger messenger;
	private final ClassifierIF classifier;
	private Intent userIntent;
	private ConversationState convState;
	private String document;
	private String currentUserId;

	@Autowired
	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		convState = ConversationState.INTENTSTATE;
		document = "";

		Thread pythonServer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					File classifier = new File("C:\\Users\\jjkar\\git\\LionChat\\LionChat\\Classifier\\Classifier.py");
					String command = String.format("python %s", classifier.getAbsolutePath());

					// Run classifier from its parent directory as a python file.
					Process p = Runtime.getRuntime().exec(command, null, classifier.getParentFile());
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		pythonServer.start();

		Map<String, String> tokensMap = new HashMap<>();
		File f = new File(System.getProperty("user.home") + "\\Desktop\\LionChat Tokens.txt");
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] split = line.split(":");
				if (split.length == 2) {
					tokensMap.put(split[0], split[1]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		final String pageAccessToken = tokensMap.get("PAGE_ACCESS_TOKEN");
		final String appSecret = tokensMap.get("APP_SECRET");
		final String verifyToken = tokensMap.get("VERIFY_TOKEN");
		
		this.messenger = Messenger.create(pageAccessToken, appSecret, verifyToken);
	}

	/**
	 * Verify the webhook for Facebook Messenger. This is needed to communicate with
	 * Facebook Messenger.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
			@RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
			@RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
		try {
			System.out.println("Verifying Webhook");
			this.messenger.verifyWebhook(mode, verifyToken);
			return ResponseEntity.ok(challenge);
		} catch (MessengerVerificationException e) {
			System.out.println("Webhook FAILED to verify");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	/**
	 * Called when a message is received from Facebook Messenger.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> receiveMessage(@RequestBody final String payload,
			@RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
		try {
			this.messenger.onReceiveEvents(payload, of(signature), event -> {
				if (event.isTextMessageEvent()) {
					TextMessageEvent message = event.asTextMessageEvent();
					this.currentUserId = message.senderId();

					sendTypingOn(currentUserId);// Indicates to user that LionChat is processing

					System.out.println("The message: " + message.toString());

					getResponse(message.text());
				}
			});
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (MessengerVerificationException e) {
			System.out.println("Callback payload failed");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	/**
	 * Send the typing indicator to the user to indicate LionChat is sending a
	 * response.
	 */
	private void sendTypingOn(String recipientId) {
		try {
			this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_ON));
		} catch (MessengerApiException e) {
			e.printStackTrace();
		} catch (MessengerIOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Turns off the typing indicator when LionChat is done typing a message.
	 */
	private void sendTypingOff(String recipientId) {
		try {
			this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_OFF));
		} catch (MessengerApiException e) {
			e.printStackTrace();
		} catch (MessengerIOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a response to the user through Facebook Messenger with the Messenger4J
	 * API.
	 * 
	 * @param message - the message to be send.
	 */
	public void sendResponse(String message) {
		try {
			final IdRecipient recipient = IdRecipient.create(currentUserId);
			final NotificationType notificationType = NotificationType.REGULAR;
			final String metadata = "DEVELOPER_DEFINED_METADATA";

			final TextMessage textMessage = TextMessage.create(message, empty(), of(metadata));
			final MessagePayload messagePayload = MessagePayload.create(recipient, MessagingType.RESPONSE, textMessage,
					of(notificationType), empty());
			this.messenger.send(messagePayload);

		} catch (MessengerApiException | MessengerIOException e) {
			System.out.println("Error sending message to user");
			e.printStackTrace();
		}
	}

	/**
	 * Get the response to the users message (usually a question).
	 * 
	 * @param message - the users message.
	 */
	public void getResponse(String message) {
		// code runs depending on current conversation state
		if (this.convState == ConversationState.INTENTSTATE) // if IntentState, classify
		{
			this.userIntent = this.classifier.classifyUserIntent(message);

			if (this.userIntent instanceof GreetingIntent) {
				sendResponse("Hello! I am LionChat: the ultimate chatbot for all things Penn State! We Are!");
				return;
			}

			if (this.userIntent instanceof UnknownIntent) {
				sendResponse("I'm sorry! I couldn't understand you.  Please repeat your question.");
				return;
			}

			if (this.userIntent == null) {
				return;
			}

			this.convState = ConversationState.ENTITYSTATE;

			if (this.userIntent.getEntities().size() > 0) {
				message = null; // set message to null for use in getEntityInfo()
			}
		}

		// if EntityState, gather entity information
		if (this.convState == ConversationState.ENTITYSTATE) {
			message = this.getEntityInfoFromUser(message);
		}

		// if in success state, see if question was answered
		if (this.convState == ConversationState.SUCCESSSTATE) {

			if (message == null) {
				sendResponse("Did this answer your question, yes or no?");
				return;
			}

			if (message.toLowerCase().equals("no")) {

				this.convState = ConversationState.INTENTSTATE;
				message = null;

				sendResponse(
						"I'm sorry I wasn't able to answer your question! Please try rephrasing or asking a new question");

				return;
				// wait for response
			} else if (message.toLowerCase().equals("yes")) {
				this.convState = ConversationState.RATINGSTATE;
				message = null;
			} else {
				sendResponse("Please enter yes or no");
				return;
			}
		}

		// if in ratings state, accept a 1 to 5 star review from the user
		if (this.convState == ConversationState.RATINGSTATE) {

			if (message == null) {
				sendResponse("How would you rate your LionChat experience, 1 to 5 stars?");
				return;
			}

			// wait for rating

			int rating = Integer.valueOf(message);

			if (rating < 1 || rating > 5) {
				sendResponse("Please enter a value between 1 and 5:");
				// wait for response
				return;
			}

			sendTypingOff(currentUserId);
			storeRating(this.userIntent, rating);
			sendResponse("Thank you for using LionChat!");
			this.convState = ConversationState.INTENTSTATE;
		}

	}

	/**
	 * Fill in the entity information by parsing message, and potentially ask the
	 * user to fill in additional information.
	 * 
	 * @param message - the user's message that may contain entity information.
	 * @return - the prompt to send the user to indicate they need to fill in more
	 *         information.
	 */
	public String getEntityInfoFromUser(String message) {
		// loop through each entity in userIntent
		for (int i = 0; i < this.userIntent.getEntities().size(); i++) {
			Entity e = this.userIntent.getEntities().get(i);
			if (!(e.getHasInfo())) // if info not filled
			{
				// loop through entities, set info for those whose hasInfo is false

				if (message == null) {
					sendResponse(e.getPrompt());
					return null;
					// user will input info
				}

				// if setEntityInformation fails
				if (!(e.setEntityInformation(message))) // if info fails to set
				{
					i--; // decrement i
				}
				message = null;
			}
		}

		this.convState = ConversationState.SUCCESSSTATE;
		getAnswer(this.userIntent, message);
		sendResponse(this.document);
		message = null;

		return message;
	}

	/**
	 * Stores a 5 star rating into the database.
	 * 
	 * @param intent - the intent of the user's question.
	 * @param rating - the 5 star rating of the user's review.
	 */
	public void storeRating(Intent intent, int rating) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			LionChatDAO lionDAO = (LionChatDAO) context.getBean("LionChatDAOImpl");
			lionDAO.addUserRating(intent, rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the answer to the user's question from the database.
	 * 
	 * @param intent  - the questions intent.
	 * @param message - the original message the user sent.
	 */
	public void getAnswer(Intent intent, String message) {
		if (intent instanceof ErieInfoIntent) {
			this.document = ((ErieInfoIntent) intent).getURL() + message.replaceAll("\\s", "+");
		} else {
			try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
				LionChatDAO lionDAO = (LionChatDAO) context.getBean("LionChatDAOImpl");
				this.document = lionDAO.getDocumentFromIntent(intent);

				if (this.document == null || this.document.trim().isEmpty()) {
					this.document = "Sorry!  No information could be found.";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * The state of the conversation. This is used to determine the way that
 * LionChat should respond to the user's question.
 */
enum ConversationState {
	INTENTSTATE, ENTITYSTATE, SUCCESSSTATE, RATINGSTATE;
}