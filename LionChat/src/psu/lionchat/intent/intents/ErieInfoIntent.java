package psu.lionchat.intent.intents;

import psu.lionchat.intent.Intent;

public class ErieInfoIntent extends Intent {
	private String URL;

	public ErieInfoIntent() {
		super();
		this.intentName = "ErieInfoIntent";
		this.URL = "https://www.google.com/search?q=";
	}

	/**
	 * Get the URL which answers the user's question.
	 * */
	public String getURL()
	{
		return this.URL;
	}
}
