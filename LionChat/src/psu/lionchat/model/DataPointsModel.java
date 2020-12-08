package psu.lionchat.model;

public class DataPointsModel {
	private String intent;
	private int rating;

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " " + this.intent + ": " + this.rating; 
	}
}
