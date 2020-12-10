package psu.lionchat.entity.entities;

import psu.lionchat.entity.Entity;

public class OperatingSystemEntity extends Entity {
	protected String userOs;

	public OperatingSystemEntity() {
		this.entityName = "User OS";
		this.prompt = "Choose your operating system: Windows or MacOS.";
	}

	@Override
	public String getEntityInformation() {
		return userOs;
	}

	@Override
	public boolean setEntityInformation(String info) {
		this.userOs = info;
		this.hasInfo = true;
		return this.hasInfo;
	}
}
