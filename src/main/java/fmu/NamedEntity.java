package fmu;

public class NamedEntity extends AbstractOWLDescribableEntity
{
	protected String name;

	public NamedEntity()
	{}
	
	public NamedEntity(final String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("- name: ");
		sb.append(name);
		sb.append('\n');

		return sb.toString();
	}	
}
