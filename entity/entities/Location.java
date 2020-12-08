package psu.lionchat.entity.entities;

import psu.lionchat.entity.Entity;

public class Location extends Entity {
	private String location;

	public Location()
	{
		this.entityName = "Location";
	}


	@Override
	public String getEntityInformation() {
		return this.location;
	}

	@Override
	public void setEntityInformation(String info) {
		this.location = info;
		this.hasInfo = true;
	}



}
