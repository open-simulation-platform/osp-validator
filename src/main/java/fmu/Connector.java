package fmu;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;

import utils.FMU2OWLConverter;
import utils.OWLAPIUtils;
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
	
	public Set<OWLAxiom> getOWLDescription(final String prefix_iri_rdl, final String prefix_iri_component, final String individual_name_prefix)
	{
		if (registry == null) 
		{
			return null;
		}
		
		super.addClassAssertionAxiom(prefix_iri_rdl, prefix_iri_component, individual_name_prefix);
		
		Iterator<String> it = variables.iterator();
		while (it.hasNext())
		{

			super.addPropertyAssertionAxiom(prefix_iri_rdl, prefix_iri_component, individual_name_prefix);

			
			
			
			String class_name = "Variable";
			String registered_name_for_variable = registry.add(registered_name + "_variable", it.next());

			axioms.add( OWLAPIUtils.getClassAssertionAxiom(manager, IRI.create(prefix_iri_rdl, class_name), IRI.create(prefix_iri_component, registered_name_for_variable)) );
			
			// describe variable value
			
		}

		String class_name = "Unit";
		String registered_name_for_unit = registry.add(registered_name + "_unit", unit);

		axioms.add( OWLAPIUtils.getClassAssertionAxiom(manager, IRI.create(prefix_iri_rdl, class_name), IRI.create(prefix_iri_component, registered_name_for_unit)) );

		// describe unit value
		
		return axioms;
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
	
	public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
	{
		converter.convert(this, abbreviated_iri_name);
	}
}