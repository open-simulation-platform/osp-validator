package fmu;

import java.util.List;

//import utils.FMU2OWLConverter;
import utils.StringUtils;

public class Bond extends TypedNamedEntity
{
	private List<String> plugs;
	private List<String> sockets;
	
	public Bond()
	{}
	
	public Bond(final String name, final String type, final List<String> plugNames, final List<String> socketNames)
	{
		super(name, type);
		this.plugs = plugNames;
		this.sockets = socketNames;
	}
	
	public List<String> getPlugs()
	{
		return plugs;
	}	

	public List<String> getSockets()
	{
		return sockets;
	}	

	public void setPlugs(final List<String> plugs)
	{
		this.plugs = plugs;
	}

	public void setSockets(final List<String> sockets)
	{
		this.sockets = sockets;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Bond:\n");
		sb.append(super.toString());
		
		sb.append("- plug names: ");
		sb.append(StringUtils.toStringAsBoxedSequenceOfItems(plugs));
		sb.append('\n');

		sb.append("- socket names: ");
		sb.append(StringUtils.toStringAsBoxedSequenceOfItems(sockets));
		sb.append('\n');

		return sb.toString();
	}
	
//	public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
//	{
//		converter.convert(this, abbreviated_iri_name);
//	}
}
