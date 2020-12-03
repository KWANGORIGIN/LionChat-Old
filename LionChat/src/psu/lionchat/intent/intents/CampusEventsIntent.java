package psu.lionchat.intent.intents;

import psu.lionchat.entity.entities.LocalDateTimeEntity;
import psu.lionchat.intent.Intent;

public class CampusEventsIntent extends Intent {
	public CampusEventsIntent() {
		super();
		this.intentName = "Campus Events";
		this.entities.add(new LocalDateTimeEntity());
	}
}
