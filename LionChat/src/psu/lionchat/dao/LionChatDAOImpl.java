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
	private DataSource dataSource;
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
					Timestamp after = ((DateTimeEntity) e).getTimestamp();
					Timestamp before = Timestamp
							.valueOf(((DateTimeEntity) e).getTimestamp().toLocalDateTime().plusDays(1));
					String SQL = String.format(
							"select * from lionchat.campuseventsdocuments WHERE starttime between '%s' and '%s' and status='CONFIRMED'",
							after, before);

					List<String> strings = jdbcTemplateObject.query(SQL, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							Timestamp starttime = rs.getTimestamp("starttime");
							long duration = rs.getTimestamp("endtime").getTime() - starttime.getTime();
							long minutes = duration / 1000 / 60;
							long hours = 0;
							StringBuilder timeString = new StringBuilder();
							if (minutes >= 60) {
								hours = minutes / 60;
								minutes %= 60;
								timeString.append(hours + " hour");
								if (hours > 1) {
									timeString.append("s ");
								} else {
									timeString.append(" ");
								}
							}
							if (minutes != 0) {
								timeString.append(minutes + " minutes");
							}
							return String.format("%s\nStarts: %s\nDuration: %s\nLocation: %s\nDescription %s\n",
									rs.getString("summary"), starttime, timeString.toString(), rs.getString("location"),
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
					} else {
						return "Invalid Input";
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
		if (intent == null) {
			System.err.println("Intent was null!");
			return;
		}
		String SQL = "insert into lionchat.reviews (" + intent.getClass().getSimpleName() + "Rating" + ") values ("
				+ rating + ")";
		jdbcTemplateObject.update(SQL);
	}

}
