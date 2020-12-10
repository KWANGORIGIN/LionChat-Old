package psu.lionchat.intent;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import psu.lionchat.entity.Entity;

public abstract class Intent {
	protected List<Entity> entities;
	protected String intentName;

	public Intent() {
		entities = new ArrayList<>();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public String toString()
	{
		return this.intentName;
	}
}
