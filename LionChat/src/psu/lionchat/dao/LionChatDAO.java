package psu.lionchat.dao;

import java.util.List;

import javax.sql.DataSource;

import psu.lionchat.intent.Intent;
import psu.lionchat.model.IntentRatingsModel;

public interface LionChatDAO {
	
	public List<IntentRatingsModel> getRatings();
	public void setDataSource(DataSource dataSource);
	public String getDocumentFromIntent(Intent intent);
	public void addUserRating(Intent intent, int rating);
}
