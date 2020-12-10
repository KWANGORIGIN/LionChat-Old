package psu.lionchat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import psu.lionchat.entity.Entity;
import psu.lionchat.entity.entities.DateTimeEntity;
import psu.lionchat.entity.entities.OperatingSystemEntity;
import psu.lionchat.intent.Intent;
import psu.lionchat.intent.intents.CampusEventsIntent;
import psu.lionchat.intent.intents.ErieInfoIntent;
import psu.lionchat.intent.intents.WifiAssistanceIntent;
import psu.lionchat.model.IntentRatingsModel;
import psu.lionchat.service.IntentRatingsMapper;

public class LionChatDAOImpl implements LionChatDAO {
	// TODO: Bind this with a bean.
	private DataSource dataSource;
	// TODO: Initialize this somehow.
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public List<IntentRatingsModel> getRatings() {
		String SQL = "select * from lionchat.reviews";
		List<IntentRatingsModel> dataPoints = jdbcTemplateObject.query(SQL, new IntentRatingsMapper());
		return dataPoints;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public String getDocumentFromIntent(Intent intent) {
		if (intent instanceof CampusEventsIntent) {
			for (Entity e : intent.getEntities()) {
				if (e instanceof DateTimeEntity) {
					Timestamp after = ((DateTimeEntity)e).getTimeStamp();
					Timestamp before = Timestamp.valueOf(((DateTimeEntity)e).getTimeStamp().toLocalDateTime().plusDays(1));
					String SQL = String.format(
							"select * from lionchat.campuseventsdocuments WHERE starttime between '%s' and '%s' and status='CONFIRMED'",
							after, before);

					List<String> strings = jdbcTemplateObject.query(SQL, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							Timestamp starttime = rs.getTimestamp("starttime");
							long duration = rs.getTimestamp("endtime").getTime() - starttime.getTime();
							long minutes = duration / 1000 / 60;
							return String.format("%s\nStarts: %s\nDuration: %d minutes\nLocation: %s\nDescription %s\n",
									rs.getString("summary"), starttime, minutes, rs.getString("location"),
									rs.getString("description"));
						}
					});

					return strings.stream().collect(Collectors.joining("\n\n"));
				}
			}
		} else if (intent instanceof ErieInfoIntent) {
			// just return a url.
		} else if (intent instanceof WifiAssistanceIntent) {
			for (Entity e : intent.getEntities()) {
				if (e instanceof OperatingSystemEntity) {
					String OS = e.getEntityInformation();
					String SQL = "";
					if (OS.equals("windows")) {
						SQL = "select * from lionchat.wifiassistancedocuments where os='windows'";
					} else if (OS.equals("macos")) {
						SQL = "select * from lionchat.wifiassistancedocuments where os='macos'";
					}
					List<String> strings = jdbcTemplateObject.query(SQL, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							String s = rs.getString("assistance");
							return s;
						}
					});
					return strings.get(0);
				}
			}
		}

		return null;
	}

	@Override
	public void addUserRating(Intent intent, int rating) {
		String SQL = "insert into lionchat.reviews (" + intent.getClass().getSimpleName() + "Rating" + ") values ("
				+ rating + ")";
		jdbcTemplateObject.update(SQL);
	}

}
