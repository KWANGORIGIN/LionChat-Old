package psu.lionchat.entity;

public abstract class Entity {
	protected String entityName;
	
	public abstract String getEntityInformation();
	public abstract void setEntityInformation(String info);
	
	@Override
	public String toString() {
		return entityName;
	}
}
