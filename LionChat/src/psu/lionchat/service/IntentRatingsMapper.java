package psu.lionchat.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import psu.lionchat.model.IntentRatingsModel;

public class IntentRatingsMapper implements RowMapper<IntentRatingsModel> {
	public IntentRatingsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		IntentRatingsModel dpModel = new IntentRatingsModel();
		ResultSetMetaData meta = rs.getMetaData();
		for(int i = 2; i <= meta.getColumnCount(); i++) {
			String intent = meta.getColumnName(i);
			int rating = rs.getInt(intent);
			if(rating == 0) {
				continue;
			}
			dpModel.setIntent(intent.replace("IntentRating", ""));
			dpModel.setRating(rating);
			return dpModel;
		}
		throw new SQLException("No intent was found for this rating!");
	}
}
