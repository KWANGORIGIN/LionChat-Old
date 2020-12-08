package psu.lionchat.dao;

import java.util.List;

import javax.sql.DataSource;

import psu.lionchat.intent.Intent;
import psu.lionchat.model.DataPointsModel;

public interface LionChatDAO {
	
	public List<DataPointsModel> getRatings();
	public void setDataSource(DataSource sourse);
	public String getDocumentFromIntent(Intent intent);
	public void addUserRating(Intent intent, int rating);
}
