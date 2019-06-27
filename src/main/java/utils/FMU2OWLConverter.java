package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import fmu.Bond;
import fmu.Connector;
import fmu.Model;
import fmu.Plug;
import fmu.Socket;

public class FMU2OWLConverter
{
	private static String HAS_NAME = "has_name";
	private static String HAS_VARIABLE = "has_variable";
	private static String HAS_CONNECTOR = "has_connector";
	private static String HAS_UNIT = "has_unit";
	private static String HAS_BOND = "has_bond";
	
	private OWLOntologyManager manager;
	private OWLDataFactory df;
	private PrefixManager prefixManager;
//	private Registry registry;
	
	private Map<String,String> map_connectorName_connector;
	private Set<OWLAxiom> axioms;
	private String abbreviated_prefix_iri_rdl;
	private String prefix_iri_component_library;
//	private String abbreviated_prefix_iri_component;
	
	private String abbreviated_iri_hasName;
	private String abbreviated_iri_hasVariable;
	private String abbreviated_iri_hasConnector;
	private String abbreviated_iri_hasUnit;
	private String abbreviated_iri_hasBond;

//	public FMU2OWLConverter(final String prefix_iri_component)
	public FMU2OWLConverter(final String prefix_iri_rdl, final String abbreviated_prefix_iri_rdl, final String prefix_iri_component_library)
	{
//		System.out.println("FMU2OWLConverter(" + prefix_iri_rdl + ", " + abbreviated_prefix_iri_rdl + ", " + prefix_iri_component_library + ")");

		manager = OWLManager.createOWLOntologyManager();
		df = manager.getOWLDataFactory();
		prefixManager = new DefaultPrefixManager();
		
		this.abbreviated_prefix_iri_rdl = abbreviated_prefix_iri_rdl;
		setPrefix(abbreviated_prefix_iri_rdl, prefix_iri_rdl);

		this.prefix_iri_component_library = prefix_iri_component_library;
		this.abbreviated_iri_hasName = abbreviated_prefix_iri_rdl + HAS_NAME;
		this.abbreviated_iri_hasVariable = abbreviated_prefix_iri_rdl + HAS_VARIABLE;
		this.abbreviated_iri_hasConnector = abbreviated_prefix_iri_rdl + HAS_CONNECTOR;
		this.abbreviated_iri_hasUnit = abbreviated_prefix_iri_rdl + HAS_UNIT;
		this.abbreviated_iri_hasBond = abbreviated_prefix_iri_rdl + HAS_BOND;
		
		
		
//		prefixManager.setDefaultPrefix(prefix_iri_rdl); // in Turtle-format: @base <prefix_iri_rdl> . 

//		setRegistry(new Registry());
		
		map_connectorName_connector = new HashMap<String, String>();
		setAxioms(new HashSet<>());
	}

	private void addOWLClassAssertionAxiom(final String abbreviated_iri_class, final String abbreviated_iri_individual)
	{
		OWLClass cls = df.getOWLClass(abbreviated_iri_class, prefixManager);
		OWLNamedIndividual ind = df.getOWLNamedIndividual(abbreviated_iri_individual, prefixManager);
		axioms.add(df.getOWLClassAssertionAxiom(cls, ind));
	}

	private void addOWLObjectPropertyAssertionAxiom(final String abbreviated_iri_subject, final String abbreviated_iri_objectProperty, final String abbreviated_iri_object)
	{
//		System.out.println("addOWLObjectPropertyAssertionAxiom(\"" + abbreviated_iri_subject + "\", \"" + abbreviated_iri_predicate + "\", \"" + abbreviated_iri_object + "\")");
		
		OWLNamedIndividual subject = df.getOWLNamedIndividual(abbreviated_iri_subject, prefixManager);
		OWLObjectProperty objectProperty = df.getOWLObjectProperty(abbreviated_iri_objectProperty, prefixManager);
		OWLNamedIndividual object = df.getOWLNamedIndividual(abbreviated_iri_object, prefixManager);
		axioms.add(df.getOWLObjectPropertyAssertionAxiom(objectProperty, subject, object));
	}
		
	private void addOWLDataPropertyAssertionAxiom(final String abbreviated_iri_subject, final String abbreviated_iri_dataProperty, final String literal_value)
	{
//		System.out.println("addOWLObjectPropertyAssertionAxiom(\"" + abbreviated_iri_subject + "\", \"" + abbreviated_iri_predicate + "\", \"" + abbreviated_iri_object + "\")");
		
		OWLNamedIndividual subject = df.getOWLNamedIndividual(abbreviated_iri_subject, prefixManager);
		OWLDataProperty dataProperty = df.getOWLDataProperty(abbreviated_iri_dataProperty, prefixManager);
		axioms.add(df.getOWLDataPropertyAssertionAxiom(dataProperty, subject, literal_value));
	}
	
	
	private void setPrefix(final String abbreviation, final String full_name)
	{
		prefixManager.setPrefix(abbreviation, full_name); // in Turtle-format: @prefix abbreviation: <full_name> .
	}

//	public void setPrefix_Component(final String abbreviation, final String component_name)
//	{
//		prefixManager.setPrefix(abbreviation, prefix_iri_component_library + component_name); // in Turtle-format: @prefix
//																					// abbreviation:
//																					// <prefix_iri_component +
//																					// model_name> .
//	}

//	public Registry getRegistry()
//	{
//		return registry;
//	}
//
//	public void setRegistry(Registry registry)
//	{
//		this.registry = registry;
//	}

	public IRI getRDLIRI(final String suffix)
	{
		return IRI.create(prefixManager.getPrefix(abbreviated_prefix_iri_rdl), suffix);
	}
	
	public IRI getLibraryIRI(final String modelName)
	{
		return IRI.create(prefix_iri_component_library + "model_" + modelName);
	}
	
	public OWLOntologyManager getManager()
	{
		return manager;
	}
	
	public Set<OWLAxiom> getAxioms()
	{
		return axioms;
	}

	public void setAxioms(Set<OWLAxiom> axioms)
	{
		this.axioms = axioms;
	}
	
	public void convert(final Model model)
	{
		if (model == null) return;
		
		String component_name = "model_" + model.getName();
		
		String iri_component = prefix_iri_component_library + component_name + '/';
		String abbreviated_iri_component = component_name + ':';
		setPrefix(abbreviated_iri_component, iri_component);

		String class_name =  model.getClass().getSimpleName();
		String abbreviated_iri_class = abbreviated_prefix_iri_rdl + class_name;

		String component_instance_name = "ind_" + component_name;
		String abbreviated_iri_component_instance_name = abbreviated_iri_component + component_instance_name;
		
		addOWLClassAssertionAxiom(abbreviated_iri_class, abbreviated_iri_component_instance_name);
		
		
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_component_instance_name, abbreviated_iri_hasName, model.getName());

		
		List<Plug> plugs = model.getPlugs();
		if (plugs != null)
		for (int plug_index = 0; plug_index < plugs.size(); plug_index++)
		{
			Plug plug = plugs.get(plug_index);
			String abbreviated_iri_plug = abbreviated_iri_component_instance_name + "_plug" + Integer.toString(plug_index);
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_component_instance_name, abbreviated_iri_hasConnector, abbreviated_iri_plug);
			plug.accept(this, abbreviated_iri_plug);
		}
		
		List<Socket> sockets = model.getSockets();
		if (sockets != null)		
		for (int socket_index = 0; socket_index < sockets.size(); socket_index++)
		{
			Socket socket = sockets.get(socket_index);
			String abbreviated_iri_socket = abbreviated_iri_component_instance_name + "_socket" + Integer.toString(socket_index);
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_component_instance_name, abbreviated_iri_hasConnector, abbreviated_iri_socket);
			socket.accept(this, abbreviated_iri_socket);
		}
		
		List<Bond> bonds = model.getBonds();
		if (bonds != null)		
		for (int bond_index = 0; bond_index < bonds.size(); bond_index++)
		{
			Bond bond = bonds.get(bond_index);
			String abbreviated_iri_bond = abbreviated_iri_component_instance_name + "_bond" + Integer.toString(bond_index);
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_component_instance_name, abbreviated_iri_hasBond, abbreviated_iri_bond);
			bond.accept(this, abbreviated_iri_bond);
		}
	}

	public void convert(final Connector connector, final String abbreviated_iri_name)
	{
		if ((connector == null)||(abbreviated_iri_name == null)) return;
		
		if (connector.getClass() == Plug.class) convert((Plug)connector, abbreviated_iri_name);
		
		if (connector.getClass() == Socket.class) convert((Socket)connector, abbreviated_iri_name);		
	}
	
	public void convert(final Plug plug, final String abbreviated_iri_name)
	{
		if ((plug == null)||(abbreviated_iri_name == null)) return;
		
		String class_name =  plug.getClass().getSimpleName();
		String abbreviated_iri_class = abbreviated_prefix_iri_rdl + class_name;
		addOWLClassAssertionAxiom(abbreviated_iri_class, abbreviated_iri_name);
		
	
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasName, plug.getName());

		map_connectorName_connector.put(plug.getName(), abbreviated_iri_name);
		
		
		String abbreviated_iri_type = abbreviated_prefix_iri_rdl + plug.getType();
		addOWLClassAssertionAxiom(abbreviated_iri_type, abbreviated_iri_name);
		

		List<String> variables = plug.getVariables();
		if (variables != null)		
		for (int variable_index = 0; variable_index < variables.size(); variable_index++)
		{
			String variable = variables.get(variable_index);
			String abbreviated_iri_variable = abbreviated_iri_name + "_variable" + Integer.toString(variable_index); 
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasVariable, abbreviated_iri_variable);
			
			addOWLClassAssertionAxiom(abbreviated_prefix_iri_rdl + "Variable", abbreviated_iri_variable);

			addOWLDataPropertyAssertionAxiom(abbreviated_iri_variable, abbreviated_iri_hasName, variable);		
		}

		
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasUnit, plug.getUnit());
	}

	public void convert(final Socket socket, final String abbreviated_iri_name)
	{
//		System.out.println(abbreviated_iri_name); // debug
//		System.out.println(socket); // debug
		
		if ((socket == null)||(abbreviated_iri_name == null)) return;

		String class_name =  socket.getClass().getSimpleName();
		String abbreviated_iri_class = abbreviated_prefix_iri_rdl + class_name;
		addOWLClassAssertionAxiom(abbreviated_iri_class, abbreviated_iri_name);
		
	
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasName, socket.getName());

		map_connectorName_connector.put(socket.getName(), abbreviated_iri_name);

		
		String abbreviated_iri_type = abbreviated_prefix_iri_rdl + socket.getType();
		addOWLClassAssertionAxiom(abbreviated_iri_type, abbreviated_iri_name);
		

		List<String> variables = socket.getVariables();
		if (variables != null)		
		for (int variable_index = 0; variable_index < variables.size(); variable_index++)
		{
			String variable = variables.get(variable_index);
			String abbreviated_iri_variable = abbreviated_iri_name + "_variable" + Integer.toString(variable_index); 
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasVariable, abbreviated_iri_variable);

			addOWLClassAssertionAxiom(abbreviated_prefix_iri_rdl + "Variable", abbreviated_iri_variable);

			addOWLDataPropertyAssertionAxiom(abbreviated_iri_variable, abbreviated_iri_hasName, variable);		
		}

		
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasUnit, socket.getUnit());
	}

	public void convert(final Bond bond, final String abbreviated_iri_name)
	{
		if ((bond == null)||(abbreviated_iri_name == null)) return;
		
		String class_name =  bond.getClass().getSimpleName();
		String abbreviated_iri_class = abbreviated_prefix_iri_rdl + class_name;
		addOWLClassAssertionAxiom(abbreviated_iri_class, abbreviated_iri_name);
		
	
		addOWLDataPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasName, bond.getName());

		
		String abbreviated_iri_type = abbreviated_prefix_iri_rdl + bond.getType();
		addOWLClassAssertionAxiom(abbreviated_iri_type, abbreviated_iri_name);
		

		List<String> plugNames = bond.getPlugNames();
		if (plugNames != null)		
		for (int plugName_index = 0; plugName_index < plugNames.size(); plugName_index++)
		{
			String plugName = plugNames.get(plugName_index);
						
			if (!map_connectorName_connector.containsKey(plugName))
			{
				throw new RuntimeException("FMU2OWLConverter Error: bond \"" + bond.getName() + "\" refers to unknown plug name \"" + plugName + "\"");
			}
			
			String abbreviated_iri_connector = map_connectorName_connector.get(plugName);
			addOWLObjectPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasConnector, abbreviated_iri_connector);
		}

		
		List<String> socketNames = bond.getSocketNames();
		if (socketNames != null)		
			for (int socketName_index = 0; socketName_index < socketNames.size(); socketName_index++)
			{
				String socketName = socketNames.get(socketName_index);
				
				if (!map_connectorName_connector.containsKey(socketName))
				{
					System.out.println("map_connectorName_connector: " + map_connectorName_connector);
					
					throw new RuntimeException("FMU2OWLConverter Error: bond refers to unknown socket name \"" + socketName + "\"");
				}
				
				String abbreviated_iri_connector = map_connectorName_connector.get(socketName);
				addOWLObjectPropertyAssertionAxiom(abbreviated_iri_name, abbreviated_iri_hasConnector, abbreviated_iri_connector);
			}
	}
	
	public OWLOntology getOntology()
	{
		OWLOntology result = null;
		
		try
		{
			result = manager.createOntology(getAxioms());
		} catch (OWLOntologyCreationException e)
		{
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static void main(String[] args)
	{
		FMU2OWLConverter converter = new FMU2OWLConverter("http://www.dnvgl.com/irm/osp/rdl/", "rdl:", "http://www.dnvgl.com/irm/osp/fmu/");

//		converter.setPrefix("rdl:", "http://www.dnvgl.com/irm/osp/rdl/");
//		converter.setPrefix_FMU_Model("h1:", "Hull1/");

//		converter.addClassAssertionAxiom("rdl:Class1", "h1:ind1");

		List<String> variables1 = new ArrayList<>();
		variables1.add("x");
		variables1.add("y");
		variables1.add("z");
				
		List<Plug> plugs = new ArrayList<>();
		Plug plug = new Plug();
		plug.setName("Plug1");
		plug.setType("ConnectorType1");
		plug.setVariables(variables1);
		plug.setUnit("Unit1");
		plugs.add(plug);
//		plugs.add( new Plug("Plug1", "ConnectorType1", variables1, "Unit1") );

		List<String> variables2 = new ArrayList<>();
		variables2.add("a");
		variables2.add("b");
		variables2.add("c");
		
		List<Socket> sockets = new ArrayList<>();
		Socket socket = new Socket();
		socket.setName("Socket2");
		socket.setType("ConnectorType2");
		socket.setVariables(variables2);
		socket.setUnit("Unit2");
		sockets.add(socket);
//		sockets.add( new Socket("Socket2", "ConnectorType2", variables2, "Unit2") );
		
		
		
		List<String> pluglist = new ArrayList<>();
		pluglist.add("Plug1");
		
		List<String> socketlist = new ArrayList<>();
		socketlist.add("Socket2");
		
		
		List<Bond> bonds = new ArrayList<>();
		bonds.add( new Bond("Bond1", "BondType1", pluglist, socketlist) );
		
		
		Model m = new Model("Hull", plugs, sockets, bonds);
		
		
		m.accept(converter);
		
		System.out.println(StringUtils.toStringAsOneItemPerLine(converter.getAxioms()));
		
		
//		System.out.println("rdl-iri: " + converter.getRDLIRI("ConnectorType1"));
//		System.out.println("library-iri: " + converter.getLibraryIRI("Hull"));
		
		
//		OWLOntology ontology = converter.getOntology();
//		System.out.println("ontology: " + ontology);
//		ReasonerFactory factory = new ReasonerFactory();
//		Configuration configuration = new Configuration();
//		configuration.throwInconsistentOntologyException = false;
//		OWLReasoner reasoner = factory.createReasoner(ontology, configuration);
//		System.out.println("reasoner.isConsistent(): " + reasoner.isConsistent());
		

		
	}
}
