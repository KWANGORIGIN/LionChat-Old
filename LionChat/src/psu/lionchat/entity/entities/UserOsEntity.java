package psu.lionchat.entity.entities;

import psu.lionchat.entity.Entity;

public class UserOsEntity extends Entity {
	protected String userOs;
	
	public UserOsEntity() {
		this.entityName = "User OS";
	}

	@Override
	public String getEntityInformation() {
		return userOs;
	}

	@Override
	public void setEntityInformation(String info) {
		this.userOs = info;
	}
}
