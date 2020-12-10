package psu.lionchat;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.github.messenger4j.Messenger;

import psu.lionchat.classifier.ClassifierIF;
import psu.lionchat.classifier.MyNaiveBayesClassifier;
import psu.lionchat.dao.LionChatDAO;
import psu.lionchat.dao.LionChatDAOImpl;
import psu.lionchat.entity.Entity;
import psu.lionchat.intent.Intent;

public class LionChat {
	private final Messenger messenger = Messenger.create("PAGE_ACCESS_TOKEN", "APP_SECRET", "VERIFY_TOKEN");
	private static LionChat lionChat = new LionChat();
	private final ClassifierIF classifier;
	private final LionChatDAO lionDAO;
	private Intent userIntent;
	private ConversationState convState;
	private String document;

	public LionChat() {
		classifier = new MyNaiveBayesClassifier();
		lionDAO = new LionChatDAOImpl();
		convState = ConversationState.INTENTSTATE;
		document = "";
	}

	public void getResponse(String message)
	{
		if(this.convState == ConversationState.INTENTSTATE)
		{
			this.userIntent = this.classifier.classifyUserIntent(message);

			this.convState = ConversationState.ENTITYSTATE;
			message = null;
		}

		if(this.convState == ConversationState.ENTITYSTATE)
		{
			this.getEntityInfoFromUser(message);
		}

		if(this.convState == ConversationState.RATINGSTATE)
		{

			this.convState = ConversationState.INTENTSTATE;
		}

	}

	public void sendResponse(String message)
	{

	}

	public void getEntityInfoFromUser(String message)
	{
		for(Entity e : this.userIntent.getEntities())
		{
			if(!(e.getHasInfo()))
			{
				//loop through entities, set info for those whose hasInfo is false

				if(message == null)
				{

					//e.getprompt & display
					//user will input info
				}
				e.setEntityInformation(message);
				message = null;
			}
		}

		this.convState = ConversationState.RATINGSTATE;

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