package fmu;

import java.util.List;

import utils.FMU2OWLConverter;
import utils.StringUtils;

public class Bond extends TypedNamedEntity
{
	private List<String> plugNames;
	private List<String> socketNames;
	
	public Bond()
	{}
	
	public Bond(final String name, final String type, final List<String> plugNames, final List<String> socketNames)
	{
		super(name, type);
		this.plugNames = plugNames;
		this.socketNames = socketNames;
	}
	
	public List<String> getPlugNames()
	{
		return plugNames;
	}	

	public List<String> getSocketNames()
	{
		return socketNames;
	}	

	public void setPlugNames(final List<String> plugNames)
	{
		this.plugNames = plugNames;
	}

	public void setSocketNames(final List<String> socketNames)
	{
		this.socketNames = socketNames;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Bond:\n");
		sb.append(super.toString());
		
		sb.append("- plug names: ");
		sb.append(StringUtils.toStringAsBoxedSequenceOfItems(plugNames));
		sb.append('\n');

		sb.append("- socket names: ");
		sb.append(StringUtils.toStringAsBoxedSequenceOfItems(socketNames));
		sb.append('\n');

		return sb.toString();
	}
	
	public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
	{
		converter.convert(this, abbreviated_iri_name);
	}
}
