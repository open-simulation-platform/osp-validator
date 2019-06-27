package fmu;

import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;

import utils.FMU2OWLConverter;

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

	public Set<OWLAxiom> getOWLDescription(final String prefix_iri_rdl, final String prefix_iri_component,
			final String individual_name_prefix)
	{
		if (registry == null)
		{
			return null;
		}

		super.addClassAssertionAxiom(prefix_iri_rdl, prefix_iri_component, individual_name_prefix);

//	public Set<OWLAxiom> addClassAssertionAxioms(final OWLOntologyManager manager, final Registry registry, final String prefix_iri_rdl, final String prefix_iri_component, final String individual_name_prefix)
//	{
//		Set<OWLAxiom> result = new HashSet<>();

//		super.addClassAssertionAxiom(prefix_iri_rdl, prefix_iri_component, individual_name_prefix);

//		String class_name_this = this.getClass().getSimpleName();
//		OWLClass cls_Model = OWLAPIUtils.getOWLClass(manager, IRI.create(prefix_iri_rdl, class_name_this));
//		String registered_name = registry.add(individual_name_prefix + "_" + class_name_this.toLowerCase(), this);
//		OWLNamedIndividual ind_this = OWLAPIUtils.getOWLNamedIndividual(manager, IRI.create(prefix_iri_component, registered_name));
//		result.add( OWLAPIUtils.getClassAssertionAxiom(manager, cls_Model, ind_this) ); 

		for (Plug plug : plugs)
		{
//			p.addClassAssertionAxiom(manager, registry, prefix_iri_rdl, prefix_iri_component, registered_name + "_");
			plug.setRegistry(registry);
			axioms.addAll(plug.getOWLDescription(prefix_iri_rdl, prefix_iri_component, registered_name));
		}

		for (Socket socket : sockets)
		{
//			result.addAll( s.toAxioms(registry, prefix_iri_rdl, prefix_iri_component, registered_name + "_") );
			socket.setRegistry(registry);
			axioms.addAll(socket.getOWLDescription(prefix_iri_rdl, prefix_iri_component, registered_name));
		}

		for (Bond bond : bonds)
		{
//			result.addAll( b.toAxioms(registry, prefix_iri_rdl, prefix_iri_component, registered_name + "_") );
			bond.setRegistry(registry);
			axioms.addAll(bond.getOWLDescription(prefix_iri_rdl, prefix_iri_component, registered_name));
		}

		return axioms;
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

	public void accept(final FMU2OWLConverter converter)
	{
		converter.convert(this);
	}

}
