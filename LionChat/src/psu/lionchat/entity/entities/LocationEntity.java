package psu.lionchat.entity.entities;

import psu.lionchat.entity.Entity;

public class LocationEntity extends Entity {
	private String location;

	public LocationEntity()
	{
		this.entityName = "Location";
		this.prompt = "Please enter the location you are interested in:";
	}


	@Override
	public String getEntityInformation() {
		return this.location;
	}

	@Override
	public boolean setEntityInformation(String info) {
		this.location = info;
		this.hasInfo = true;
		return this.hasInfo;
	}
}
