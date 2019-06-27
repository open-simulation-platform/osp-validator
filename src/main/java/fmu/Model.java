package fmu;

import java.util.List;
import java.util.Set;

//import org.semanticweb.owlapi.model.OWLAxiom;

//import utils.FMU2OWLConverter;

public class Model extends NamedEntity
{
	private List<Plug> plugs;
	private List<Socket> sockets;
	private List<Bond> bonds;

	public Model()
	{
	}

	public Model(final String name, final List<Plug> plugs, final List<Socket> sockets, final List<Bond> bonds)
	{
		super(name);
		this.plugs = plugs;
		this.sockets = sockets;
		this.bonds = bonds;
	}

	public List<Plug> getPlugs()
	{
		return plugs;
	}

	public List<Socket> getSockets()
	{
		return sockets;
	}

	public List<Bond> getBonds()
	{
		return bonds;
	}

	public void setPlugs(final List<Plug> plugs)
	{
		this.plugs = plugs;
	}

	public void setSockets(final List<Socket> sockets)
	{
		this.sockets = sockets;
	}

	public void setBonds(final List<Bond> bonds)
	{
		this.bonds = bonds;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Model:\n");
		sb.append(super.toString());

		sb.append("- plugs:\n");
		for (Plug plug : plugs)
		{
			sb.append(plug.toString());
			sb.append('\n');
		}

		sb.append(" - sockets:\n");
		for (Socket socket : sockets)
		{
			sb.append(socket.toString());
			sb.append('\n');
		}

		sb.append("- bonds:\n");
		for (Bond bond : bonds)
		{
			sb.append(bond.toString());
			sb.append('\n');
		}

		return sb.toString();
	}

//	public void accept(final FMU2OWLConverter converter)
//	{
//		converter.convert(this);
//	}

}
