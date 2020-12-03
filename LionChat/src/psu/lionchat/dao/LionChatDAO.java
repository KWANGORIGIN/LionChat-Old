package psu.lionchat.dao;

import java.util.List;

import psu.lionchat.intent.Intent;

public interface LionChatDAO {
	
	public List<Object> getRatings();
	// TODO: Remove this since getRatings will likely be List<HashMap<Intent, Integer>>
	// and we can process it to find FAQ / PAQ
	public List<Object> getFrequentlyAskedQuestions();
	// TODO: Remove
	public List<Object> poorlyAnsweredQuestions();
	public String getDocumentFromIntent(Intent intent);
	public void addUserRating(Intent intent, int rating);
}
