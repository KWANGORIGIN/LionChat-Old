package psu.lionchat.entity;

public abstract class Entity {
	protected String entityName;
	protected boolean hasInfo;
	protected String prompt;

	public Entity()
	{
		this.hasInfo = false;
	}
	/**
	 * Get the information associated with this entity as a string.
	 * @return a readable form of the information stored in this entity.
	 * */
	public abstract String getEntityInformation();
	/**
	 * Parse the readable information to fill in the entity's fields.
	 * @param info - the information in a readable format to store in the entity.
	 * */
	public abstract boolean setEntityInformation(String info);
	
	@Override
	public String toString() {
		return entityName;
	}

	/**
	 * Get the prompt to ask the user for them to fill in this entity.
	 * Ex. What operating system are you using?
	 * @return the prompt to ask the user
	 * */
	public String getPrompt()
	{
		return this.prompt;
	}

	/**
	 * Determines whether or not the entity has more information which needs filled in.
	 * @return whether or not the entity is filled in.
	 * */
	public boolean getHasInfo()
	{
		return this.hasInfo;
	}
}
