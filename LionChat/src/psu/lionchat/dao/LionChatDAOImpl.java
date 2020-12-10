package psu.lionchat.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import psu.lionchat.entity.Entity;
import psu.lionchat.entity.entities.DateTimeEntity;
import psu.lionchat.entity.entities.OperatingSystemEntity;
import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.CampusEventsIntent;
import psu.lionchat.intent.intents.ErieInfoIntent;
import psu.lionchat.intent.intents.WifiAssistanceIntent;
import psu.lionchat.model.DataPointsModel;
import psu.lionchat.service.DataPointsMapper;

public class LionChatDAOImpl implements LionChatDAO {
	// TODO: Bind this with a bean.
	private DataSource dataSource;
	// TODO: Initialize this somehow.
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public List<DataPointsModel> getRatings() {
		String SQL = "select * from lionchat.reviews";
		List<DataPointsModel> dataPoints = jdbcTemplateObject.query(SQL, new DataPointsMapper());
		return dataPoints;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public String getDocumentFromIntent(Intent intent) {
		if(intent instanceof CampusEventsIntent) {
			for(Entity e : intent.getEntities()) {
				if(e instanceof DateTimeEntity dateTimeEntity) {
					Timestamp timeStamp = dateTimeEntity.getTimestamp();
					// get events within an hour from database.
				}
			}
		}else if (intent instanceof ErieInfoIntent) {
			// just return a url.
		}else if (intent instanceof WifiAssistanceIntent) {
			for(Entity e: intent.getEntities()) {
				if(e instanceof OperatingSystemEntity) {
					String OS = e.getEntityInformation();
					// get help from database
				}
			}
		}
		
		return null;
	}

	@Override
	public void addUserRating(Intent intent, int rating) {
		//String SQL = "insert into (" + intent.
	}

}
