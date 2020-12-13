package psu.lionchat.entity;

public abstract class Entity {
	protected String entityName;
	protected boolean hasInfo;
	protected String prompt;

	public Entity()
	{
		this.hasInfo = false;
	}
	public abstract String getEntityInformation();
	public abstract boolean setEntityInformation(String info);
	
	@Override
	public String toString() {
		return entityName;
	}

	public String getPrompt()
	{
		return this.prompt;
	}

	public boolean getHasInfo()
	{
		return this.hasInfo;
	}
}
