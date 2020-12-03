package psu.lionchat.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import psu.lionchat.intent.Intent;

public class LionChatDAOImpl implements LionChatDAO {
	// TODO: Bind this with a bean.
	private DataSource dataSource;
	// TODO: Initialize this somehow.
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public List<Object> getRatings() {
		return null;
	}

	@Override
	public List<Object> getFrequentlyAskedQuestions() {
		return null;
	}

	@Override
	public List<Object> poorlyAnsweredQuestions() {
		return null;
	}

	@Override
	public String getDocumentFromIntent(Intent intent) {
		return null;
	}

	@Override
	public void addUserRating(Intent intent, int rating) {
		
	}

}
