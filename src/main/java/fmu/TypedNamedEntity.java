package fmu;

public class TypedNamedEntity extends NamedEntity 
{
	protected String type;
	
	public TypedNamedEntity()
	{}
	
	public TypedNamedEntity(final String name, final String type)
	{
		super(name);
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());
		
		sb.append("- type: ");
		sb.append(type);
		sb.append('\n');

		return sb.toString();
	}	
}
