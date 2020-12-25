package psu.lionchat.intent;

import java.util.ArrayList;
import java.util.List;

import psu.lionchat.entity.Entity;

public abstract class Intent {
	protected List<Entity> entities;
	protected String intentName;

	public Intent() {
		entities = new ArrayList<>();
	}

	/**
	 * Get the entities for this intent as a list.
	 * */
	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public String toString()
	{
		return this.intentName;
	}
}
