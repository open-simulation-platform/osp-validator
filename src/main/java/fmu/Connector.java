package fmu;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import utils.FMU2OWLConverter;
//import utils.OWLAPIUtils;
import utils.StringUtils;

public class Connector extends TypedNamedEntity
{
	private List<String> variables;
	private String unit;
	
	public Connector()
	{}
	
	public Connector(final String name, final String type, final List<String> variables, final String unit)
	{
		super(name, type);
		this.variables = variables;
		this.unit = unit;
	}
	
	public List<String> getVariables()
	{
		return variables;
	}
	
	public String getUnit()
	{
		return unit;
	}
	
	public void setVariables(final List<String> variables)
	{
		this.variables = variables;
	}
	
	public void setUnit(final String unit)
	{
		this.unit = unit;
	}
	

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());
		
		sb.append("- variables: ");
		sb.append(StringUtils.toStringAsBoxedSequenceOfItems(variables));
		sb.append('\n');

		sb.append("- unit: ");
		sb.append(unit);
		sb.append('\n');

		return sb.toString();
	}
	
//	public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
//	{
//		converter.convert(this, abbreviated_iri_name);
//	}
}