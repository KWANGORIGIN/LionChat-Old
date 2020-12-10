package psu.lionchat.intent.intents;

import psu.lionchat.entity.entities.DateTimeEntity;
import psu.lionchat.entity.entities.LocationEntity;
import psu.lionchat.intent.Intent;

public class CampusEventsIntent extends Intent {
	public CampusEventsIntent() {
		super();
		this.intentName = "Campus Events";
		this.entities.add(new DateTimeEntity());
		this.entities.add(new LocationEntity());
	}

	@Override
	public String toString(){
		return "CampusEventsIntent";
	}
}