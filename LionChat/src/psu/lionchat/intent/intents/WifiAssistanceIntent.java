package psu.lionchat.intent.intents;

import psu.lionchat.entity.entities.OperatingSystemEntity;
import psu.lionchat.intent.Intent;

public class WifiAssistanceIntent extends Intent {
	public WifiAssistanceIntent() {
		super();
		this.intentName = "Wifi Assistance";
		this.entities.add(new OperatingSystemEntity());
	}

	@Override
	public String toString(){
		return "WifiAssistanceIntent";
	}

}