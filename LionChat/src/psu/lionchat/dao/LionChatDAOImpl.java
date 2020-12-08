package psu.lionchat.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import psu.lionchat.intent.Intent;
import psu.lionchat.model.DataPointsModel;
import psu.lionchat.service.DataPointsMapper;

public class LionChatDAOImpl implements LionChatDAO {
	// TODO: Bind this with a bean.
	private DataSource dataSource;
	// TODO: Initialize this somehow.
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public List<DataPointsModel> getRatings() {
		String SQL = "select * from lionchat.datapoints";
		List<DataPointsModel> dataPoints = jdbcTemplateObject.query(SQL, new DataPointsMapper());
		return dataPoints;
	}
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	@Override
	public String getDocumentFromIntent(Intent intent) {
		return null;
	}

	@Override
	public void addUserRating(Intent intent, int rating) {
		
	}

}
