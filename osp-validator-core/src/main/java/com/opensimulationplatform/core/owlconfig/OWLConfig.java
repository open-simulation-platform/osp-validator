package com.opensimulationplatform.core.owlconfig;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.owlbuilder.SimulatorOwlBuilder;
import com.opensimulationplatform.core.owlbuilder.VariableConnectionOwlBuilder;
import com.opensimulationplatform.core.owlbuilder.VariableGroupConnectionOwlBuilder;
import com.opensimulationplatform.gen.util.resource.Resources;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OWLConfig {
  public final OWLOntology ontology;
  public final OWLOntologyManager manager;
  public final OWLDataFactory dataFactory;
  public final PrefixManager prefixManager;
  public final OWLReasoner reasoner;

  private final Map<OWLNamedIndividual, Simulator> indToSimulator = new HashMap<>();
  private final Map<Simulator, OWLNamedIndividual> simulatorToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, Variable> indToVariable = new HashMap<>();
  private final Map<Variable, OWLNamedIndividual> variableToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, VariableGroup> indToVariableGroup = new HashMap<>();
  private final Map<VariableGroup, OWLNamedIndividual> variableGroupToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, VariableConnection> indToVariableConnection = new HashMap<>();
  private final Map<VariableConnection, OWLNamedIndividual> variableConnectionToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, VariableGroupConnection> indToVariableGroupConnection = new HashMap<>();
  private final Map<VariableGroupConnection, OWLNamedIndividual> variableGroupConnectionToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, Unit> indToUnit = new HashMap<>();
  private final Map<Unit, OWLNamedIndividual> unitToInd = new HashMap<>();

  private final Map<OWLNamedIndividual, Name> indToName = new HashMap<>();
  private final Map<Name, OWLNamedIndividual> nameToInd = new HashMap<>();

  public OWLConfig(OWLOntology ontology) {
    this.ontology = ontology;
    this.manager = this.ontology.getOWLOntologyManager();
    this.dataFactory = this.manager.getOWLDataFactory();
    this.prefixManager = createPrefixManager(this.ontology);
    this.reasoner = createReasoner(this.ontology);
  }

  public OWLConfig(File owlFile) {
    this(load(owlFile));
  }

  public OWLConfig(SystemStructure systemStructure) {
    this((load(Resources.OSP_OWL.toFile())));

    systemStructure.getSimulators().forEach(simulator -> {
      SimulatorOwlBuilder builder = new SimulatorOwlBuilder(this);
      builder.build(simulator);
    });

    systemStructure.getVariableGroupConnections().forEach(variableGroupConnection -> {
      VariableGroupConnectionOwlBuilder builder = new VariableGroupConnectionOwlBuilder(this);
      builder.build(variableGroupConnection);
    });

    systemStructure.getVariableConnections().forEach(variableConnection -> {
      VariableConnectionOwlBuilder builder = new VariableConnectionOwlBuilder(this);
      builder.build(variableConnection);
    });
  }

  private static OWLOntology load(File owlFile) {
    try {
      return OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(owlFile);
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading ontology from " + owlFile, e);
    }
  }

  public void save(File owlFile) {
    try {
      OWLOntologyFormat format = ontology.getOWLOntologyManager().getOntologyFormat(ontology);
      OWLXMLOntologyFormat owlFormat = new OWLXMLOntologyFormat();
      if (format.isPrefixOWLOntologyFormat()) {
        owlFormat.copyPrefixesFrom(format.asPrefixOWLOntologyFormat());
      }
      ontology.getOWLOntologyManager().saveOntology(ontology, owlFormat, IRI.create(owlFile.toURI()));
    } catch (OWLOntologyStorageException e) {
      throw new RuntimeException("Error saving ontology to " + owlFile, e);
    }
  }

  private OWLReasoner createReasoner(OWLOntology ontology) {
    return new Reasoner.ReasonerFactory().createNonBufferingReasoner(ontology);
  }

  private PrefixManager createPrefixManager(OWLOntology ontology) {
    DefaultPrefixManager prefixManager = new DefaultPrefixManager();
    OWLOntologyFormat format = ontology.getOWLOntologyManager().getOntologyFormat(ontology);
    if (format.isPrefixOWLOntologyFormat()) {
      Map<String, String> map = format.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap();
      for (String prefixName : map.keySet()) {
        prefixManager.setPrefix(prefixName, map.get(prefixName));
      }
    }
    return prefixManager;
  }

  public void addObjectPropertyAssertionAxiom(OWLNamedIndividual subject, OWLObjectProperty predicate, OWLNamedIndividual object) {
    manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(predicate, subject, object));
  }

  public void addClassAssertionAxiom(OWLClass clazz, OWLNamedIndividual individual) {
    OWLAxiom axiom = dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    manager.addAxiom(ontology, axiom);
  }

  public void addDifferentIndividualsAxiom(OWLNamedIndividual a, OWLNamedIndividual b) {
    OWLAxiom axiom = dataFactory.getOWLDifferentIndividualsAxiom(a, b);
    manager.addAxiom(ontology, axiom);
  }

  public void makeDisjointWithAllIndividualsOfClass(OWLNamedIndividual individual, OWLClass clazz) {
    Set<OWLNamedIndividual> individuals = reasoner.getInstances(clazz, false).getFlattened();
    individuals.forEach(i -> addDifferentIndividualsAxiom(individual, i));
  }

  public void makeDisjointWithAllOtherIndividuals(OWLNamedIndividual individual) {
    Set<OWLNamedIndividual> individuals = reasoner.getInstances(dataFactory.getOWLThing(), false).getFlattened();
    individuals.forEach(i -> {
      if (!i.equals(individual)) {
        addDifferentIndividualsAxiom(individual, i);
      }
    });
  }

  public void addName(OWLNamedIndividual individual, Name name) {
    indToName.put(individual, name);
    nameToInd.put(name, individual);
  }

  public Name getName(OWLNamedIndividual individual) {
    return indToName.get(individual);
  }

  public OWLNamedIndividual getName(Name name) {
    return nameToInd.get(name);
  }

  public void addSimulator(OWLNamedIndividual individual, Simulator simulator) {
    indToSimulator.put(individual, simulator);
    simulatorToInd.put(simulator, individual);
  }

  public Simulator getSimulator(OWLNamedIndividual individual) {
    return indToSimulator.get(individual);
  }

  public OWLNamedIndividual getSimulator(Simulator simulator) {
    return simulatorToInd.get(simulator);
  }

  public void addUnit(OWLNamedIndividual individual, Unit unit) {
    indToUnit.put(individual, unit);
    unitToInd.put(unit, individual);
  }

  public Unit getUnit(OWLNamedIndividual individual) {
    return indToUnit.get(individual);
  }

  public OWLNamedIndividual getUnit(Unit unit) {
    return unitToInd.get(unit);
  }

  public void addVariableConnection(OWLNamedIndividual individual, VariableConnection variableConnection) {
    indToVariableConnection.put(individual, variableConnection);
    variableConnectionToInd.put(variableConnection, individual);
  }

  public VariableConnection getVariableConnection(OWLNamedIndividual individual) {
    return indToVariableConnection.get(individual);
  }

  public OWLNamedIndividual getVariableConnection(VariableConnection variableConnection) {
    return variableConnectionToInd.get(variableConnection);
  }

  public void addVariableGroupConnection(OWLNamedIndividual individual, VariableGroupConnection variableGroupConnection) {
    indToVariableGroupConnection.put(individual, variableGroupConnection);
    variableGroupConnectionToInd.put(variableGroupConnection, individual);
  }

  public VariableGroupConnection getVariableGroupConnection(OWLNamedIndividual individual) {
    return indToVariableGroupConnection.get(individual);
  }

  public OWLNamedIndividual getVariableGroupConnection(VariableGroupConnection variableGroupConnection) {
    return variableGroupConnectionToInd.get(variableGroupConnection);
  }

  public void addVariableGroup(OWLNamedIndividual individual, VariableGroup variableGroup) {
    indToVariableGroup.put(individual, variableGroup);
    variableGroupToInd.put(variableGroup, individual);
  }

  public VariableGroup getVariableGroup(OWLNamedIndividual individual) {
    return indToVariableGroup.get(individual);
  }

  public OWLNamedIndividual getVariableGroup(VariableGroup variableGroup) {
    return variableGroupToInd.get(variableGroup);
  }

  public void addVariable(OWLNamedIndividual individual, Variable variable) {
    indToVariable.put(individual, variable);
    variableToInd.put(variable, individual);
  }

  public Variable getVariable(OWLNamedIndividual individual) {
    return indToVariable.get(individual);
  }

  public OWLNamedIndividual getVariable(Variable variable) {
    return variableToInd.get(variable);
  }
}
