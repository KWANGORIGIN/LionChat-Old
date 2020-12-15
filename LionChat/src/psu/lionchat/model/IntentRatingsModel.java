package psu.lionchat.model;

public class IntentRatingsModel {
	private String intent;
	private int rating;

	/**
	 * Get the intent for this datapoint.
	 * */
	public String getIntent() {
		return intent;
	}

	/**
	 * Set the intent for this datapoint.
	 * */
	public void setIntent(String intent) {
		this.intent = intent;
	}

	/**
	 * Get the 5 star rating associated with this datapoint.
	 * */
	public int getRating() {
		return rating;
	}

	
	/**
	 * Set the 5 star rating associated with this datapoint.
	 * */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " " + this.intent + ": " + this.rating; 
	}
}
