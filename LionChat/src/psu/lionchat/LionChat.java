package psu.lionchat;

import com.github.messenger4j.exception.MessengerVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import static com.github.messenger4j.Messenger.CHALLENGE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.MODE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.VERIFY_TOKEN_REQUEST_PARAM_NAME;

import com.github.messenger4j.Messenger;

import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;
import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.dao.LionChatDAOImpl;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;

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

	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		lionDAO = new LionChatDAOImpl();
		convState = ConversationState.INTENTSTATE;
		document = "";

		this.messenger = Messenger.create("PAGE_ACCESS_TOKEN", "APP_SECRET", "VERIFY_TOKEN");
		//this.messenger.verifyWebhook(mode, "VERIFY_TOKEN");

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

	public void sendResponse(String message)
	{

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