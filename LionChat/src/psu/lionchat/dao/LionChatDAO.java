package psu.lionchat.dao;

import java.util.List;

import javax.sql.DataSource;

import psu.lionchat.intent.Intent;
import psu.lionchat.model.IntentRatingsModel;

public interface LionChatDAO {
	/**
	 * Gets a list of the ratings for each intent on a 5 star 
	 * scale by querying the database.
	 * @return a list of the ratings and their intent.
	 * */
	public List<IntentRatingsModel> getRatings();
	/**
	 * Sets the data source for the database access object. This set by a bean.
	 * */
	public void setDataSource(DataSource dataSource);
	/**
	 * Gets the document for an intent.
	 * @param intent - the intent of the users question (what they want to do).
	 * @return the document (the answer) for their question.
	 * */
	public String getDocumentFromIntent(Intent intent);
	/**
	 * Store a 5 star rating for the associated intent in the database.
	 * @param intent - the intent to store the rating to.
	 * @param rating - the review on a 5 star scale.
	 * */
	public void addUserRating(Intent intent, int rating);
}
