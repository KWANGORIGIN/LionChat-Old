package psu.lionchat.classifier;

import psu.lionchat.intent.Intent;

public interface ClassifierIF {
	/**
	 * Get the intent of the user's question. This is the goal the user
	 * wants to accomplish by asking a question.
	 * 
	 * Example: Help, my computer exploded! Intent: itHelp.
	 * @param utterance The question the user asks.
	 * @return The intent of the users question, as a string.
	 * */
	public String getIntentString(String utterance);
	
	/**
	 * Get the intent of the user's question. This is the goal the user
	 * wants to accomplish by asking a question.
	 * 
	 * Example: Help, my computer exploded! Intent: itHelp.
	 * @param utterance The question the user asks.
	 * @return The intent of the users question.
	 * */
	public Intent classifyUserIntent(String utterance);
}
