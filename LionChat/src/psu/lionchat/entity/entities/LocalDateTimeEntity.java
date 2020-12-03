package psu.lionchat.entity.entities;

import java.time.LocalDateTime;

import psu.lionchat.entity.Entity;

public class LocalDateTimeEntity extends Entity {
	private LocalDateTime dateTime;
	@Override
	public String getEntityInformation() {
		return this.dateTime.toString();
	}

	@Override
	public void setEntityInformation(String info) {
		// select entity
	}

}
