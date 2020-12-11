package psu.lionchat;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;
import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.ErieInfoIntent;
import psu.lionchat.intent.intents.GreetingIntent;
import psu.lionchat.intent.intents.UnknownIntent;

import static com.github.messenger4j.Messenger.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@RestController
@RequestMapping("/lionchat")
public class LionChat {
	private final Messenger messenger;
	private final ClassifierIF classifier;
	private Intent userIntent;
	private ConversationState convState;
	private String document;
	private String currentUserId;

	@RequestMapping(value = "/get-intent", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void getIntent(@RequestBody String utterance) {
		System.out.println(utterance);
		System.out.println(this.getClassifier().getIntentString(utterance));
	}

	@Autowired
	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		convState = ConversationState.INTENTSTATE;
		document = "";

		this.messenger = Messenger.create("EAADAZBEirnFQBAP8z2bOax6fDZC97ZA9jcbfgaTbg512mTxKMJLcEqAZANX8GdU3743JABJ4sH47ySZBZCFkxr9hCj7LjDC5kdIEeqYkDGC38btZBPqE1JVPAZCO4KqdBjSZBqtSZBYLRoGQQDwrlCy9QyO6brZAHENyCewv7kxG1MNJAZDZD", "3d00de1106c4ab254deb9aeb6d1d6d21", "YeetYeetYeet");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
												@RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
												@RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge){
		try {
			System.out.println("Verifying Webhook");
			this.messenger.verifyWebhook(mode, verifyToken);
			return ResponseEntity.ok(challenge);
		} catch (MessengerVerificationException e) {
			System.out.println("Webhook FAILED to verify");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> receiveMessage(@RequestBody final String payload, @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
		try {
			this.messenger.onReceiveEvents(payload, of(signature), event -> {
				if (event.isTextMessageEvent()) {
					TextMessageEvent message = event.asTextMessageEvent();
					this.currentUserId = message.senderId();

					sendTypingOn(currentUserId);//Indicates to user that LionChat is processing

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

	public void sendResponse(String message) {
		try {
			final IdRecipient recipient = IdRecipient.create(currentUserId);
			final NotificationType notificationType = NotificationType.REGULAR;
			final String metadata = "DEVELOPER_DEFINED_METADATA";

			final TextMessage textMessage = TextMessage.create(message, empty(), of(metadata));
			final MessagePayload messagePayload = MessagePayload.create(recipient, MessagingType.RESPONSE, textMessage, of(notificationType), empty());
			this.messenger.send(messagePayload);

		} catch (MessengerApiException | MessengerIOException e) {
			System.out.println("Error sending message to user");
		}
	}

	private void sendTypingOn(String recipientId){
		try{
			this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_ON));
		}catch(MessengerApiException e){
			e.printStackTrace();
		}catch(MessengerIOException e){
			e.printStackTrace();
		}
	}

	private void sendTypingOff(String recipientId){
		try{
			this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_OFF));
		}catch(MessengerApiException e){
			e.printStackTrace();
		}catch(MessengerIOException e){
			e.printStackTrace();
		}
	}

	public void getResponse(String message)
	{
		//code runs depending on current conversation state
		if(this.convState == ConversationState.INTENTSTATE) //if IntentState, classify
		{
			this.userIntent = this.classifier.classifyUserIntent(message);

			if(this.userIntent instanceof GreetingIntent)
			{
				sendResponse("Hello there!");
				return;
			}

			if(this.userIntent instanceof UnknownIntent)
			{
				sendResponse("I'm sorry! I couldn't understand you.  Please repeat your question.");
				return;
			}
			
			if(this.userIntent == null) {
				return;
			}
			
			this.convState = ConversationState.ENTITYSTATE;

			if(this.userIntent.getEntities().size() > 0)
			{
				message = null; //set message to null for use in getEntityInfo()
			}
		}

		//if EntityState, gather entity information
		if(this.convState == ConversationState.ENTITYSTATE)
		{
			message = this.getEntityInfoFromUser(message);
		}

		//if in success state, see if question was answered
		if(this.convState == ConversationState.SUCCESSSTATE)
		{

			if(message == null)
			{
				sendResponse("Did this answer your question, yes or no?");
				return;
			}

			if(message.toLowerCase().equals("no"))
			{

				this.convState = ConversationState.INTENTSTATE;
				message = null;

				sendResponse("Please enter a question.");

				return;
				//wait for response
			}
			else if(message.toLowerCase().equals("yes"))
			{
				this.convState = ConversationState.RATINGSTATE;
				message = null;
			}
			else
			{
				sendResponse("Please enter yes or no");
				return;
			}
		}

		//if in ratings state, accept a 1 to 5 star review from the user
		if(this.convState == ConversationState.RATINGSTATE)
		{

			if(message == null)
			{
				sendResponse("How would you rate your LionChat experience, 1 to 5 stars?");

				if (true) {
					return;
				}
			}

			//wait for rating

			int rating = Integer.valueOf(message);

			if(rating < 1 || rating > 5)
			{
				sendResponse("Please enter a value between 1 and 5:");
				//wait for response
				return;
			}

			sendTypingOff(currentUserId);
			storeRating(this.userIntent, rating);
			sendResponse("Thank you for using LionChat!");
			this.convState = ConversationState.INTENTSTATE;
		}

	}

	public String getEntityInfoFromUser(String message)
	{
		//loop through each entity in userIntent
		for(int i = 0; i < this.userIntent.getEntities().size(); i++)
		{
			Entity e = this.userIntent.getEntities().get(i);
			if(!(e.getHasInfo())) //if info not filled
			{
				//loop through entities, set info for those whose hasInfo is false

				if(message == null)
				{
					sendResponse(e.getPrompt());
					return null;
					//user will input info
				}

				//if setEntityInformation fails
				if(!(e.setEntityInformation(message))) //if info fails to set
				{
					i--; //decrement i
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

	public void storeRating(Intent intent, int rating)
	{
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			LionChatDAO lionDAO = (LionChatDAO) context.getBean("LionChatDAOImpl");
			lionDAO.addUserRating(intent, rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAnswer(Intent intent, String message)
	{
		if(intent instanceof ErieInfoIntent)
		{
			this.document = ((ErieInfoIntent) intent).getURL() + message.replaceAll("\\s", "+");
		}
		else
		{
			try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
				LionChatDAO lionDAO = (LionChatDAO) context.getBean("LionChatDAOImpl");
				this.document = lionDAO.getDocumentFromIntent(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public ModelAndView getHomePage(ModelMap model)
	{
		return null;
	}

	public ModelAndView GetAnalyticsPage(ModelMap model)
	{
		return null;
	}


	public ClassifierIF getClassifier() {
		return classifier;
	}
}

enum ConversationState
{
	INTENTSTATE,
	ENTITYSTATE,
	SUCCESSSTATE,
	RATINGSTATE;
}