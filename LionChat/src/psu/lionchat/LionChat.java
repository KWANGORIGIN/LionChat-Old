package psu.lionchat;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.webhook.event.TextMessageEvent;

import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;
import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.dao.LionChatDAOImpl;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;

import static com.github.messenger4j.Messenger.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@RestController
@RequestMapping("/callback")
public class LionChat {
	private final Messenger messenger;
	private static LionChat lionChat = new LionChat();
	private final ClassifierIF classifier;
	private final LionChatDAO lionDAO;
	private Intent userIntent;
	private ConversationState convState;
	private String document;
	private boolean entitiesComplete;
	private String currentUserId;

	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		lionDAO = new LionChatDAOImpl();
		convState = ConversationState.INTENTSTATE;
		document = "";

		this.messenger = Messenger.create("PAGE_ACCESS_TOKEN", "APP_SECRET", "VERIFY_TOKEN");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
												@RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
												@RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge){
		try {
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

					try {
						sendTypingOn(currentUserId);
					} catch (MessengerApiException e) {
						e.printStackTrace();
					} catch (MessengerIOException e) {
						e.printStackTrace();
					}

					getResponse(message.text());//Calls our method to do processing

				}
			});
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (MessengerVerificationException e) {
			System.out.println("Callback payload failed");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	private void sendTypingOn(String recipientId) throws MessengerApiException, MessengerIOException {
		this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_ON));
	}

	private void sendTypingOff(String recipientId) throws MessengerApiException, MessengerIOException {
		this.messenger.send(SenderActionPayload.create(recipientId, SenderAction.TYPING_OFF));
	}

	public void getResponse(String message)
	{
		if(this.convState == ConversationState.INTENTSTATE)
		{
			this.userIntent = this.classifier.classifyUserIntent(message);

			this.convState = ConversationState.ENTITYSTATE;
		}

		if(this.convState == ConversationState.ENTITYSTATE)
		{
			if(this.getEntityInfoFromUser())
			{
				this.convState = ConversationState.RATINGSTATE;
			}
		}

		if(this.convState == ConversationState.RATINGSTATE)
		{

			this.convState = ConversationState.INTENTSTATE;
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

	public boolean getEntityInfoFromUser()
	{
		boolean hasAllEntities = true;
		for(Entity e : this.userIntent.getEntities())
		{
			if(!(e.getHasInfo()))
			{
				hasAllEntities = false;
				//get question
				//input entity info
				//send to setEntityInformation
				//e.setEntityInformation(string);
			}
		}

		return hasAllEntities;
	}

	public void sendDocument()
	{

	}


	public void storeRating(Intent intent, int rating)
	{

	}

	public ModelAndView getHomePage(ModelMap model)
	{
		return null;
	}

	public ModelAndView GetAnalyticsPage(ModelMap model)
	{
		return null;
	}


	public static LionChat getInstance() {
		return lionChat;
	}

	public ClassifierIF getClassifier() {
		return classifier;
	}
}

enum ConversationState
{
	INTENTSTATE,
	ENTITYSTATE,
	RATINGSTATE;
}