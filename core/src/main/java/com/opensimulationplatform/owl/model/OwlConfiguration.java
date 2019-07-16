package com.opensimulationplatform.owl.model;

import com.opensimulationplatform.msmivalidator.model.configuration.Configuration;
import com.opensimulationplatform.msmivalidator.model.modeldefinition.*;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.HashMap;
import java.util.Map;

public class OwlConfiguration {
  private OWLOntology ontology;
  private Configuration configuration;
  private final Map<OWLNamedIndividual, Simulator> simulators = new HashMap<>();
  private final Map<OWLNamedIndividual, Bond> bonds = new HashMap<>();
  private final Map<OWLNamedIndividual, Plug> plugs = new HashMap<>();
  private final Map<OWLNamedIndividual, Socket> sockets = new HashMap<>();
  private final Map<OWLNamedIndividual, Variable> variables = new HashMap<>();
  
  public OWLOntology getOntology() {
    return ontology;
  }
  
  public Configuration getConfiguration() {
    return configuration;
  }
  
  public void setOntology(OWLOntology ontology) {
    this.ontology = ontology;
  }
  
  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }
  
  public Map<OWLNamedIndividual, Simulator> getSimulators() {
    return simulators;
  }
  
  public Map<OWLNamedIndividual, Bond> getBonds() {
    return bonds;
  }
  
  public Map<OWLNamedIndividual, Plug> getPlugs() {
    return plugs;
  }
  
  public Map<OWLNamedIndividual, Socket> getSockets() {
    return sockets;
  }
  
  public Map<OWLNamedIndividual, Variable> getVariables() {
    return variables;
  }
  
  public void addSimulator(OWLNamedIndividual individual, Simulator simulator) {
    simulators.put(individual, simulator);
  }
  
  public void addBond(OWLNamedIndividual individual, Bond bond) {
    bonds.put(individual, bond);
  }
  
  public void addPlug(OWLNamedIndividual individual, Plug plug) {
    plugs.put(individual, plug);
  }
  
  public void addSocket(OWLNamedIndividual individual, Socket socket) {
    sockets.put(individual, socket);
  }
  
  public void addVariable(OWLNamedIndividual individual, Variable variable) {
    variables.put(individual, variable);
  }
  
  public Simulator getSimulator(OWLNamedIndividual individual) {
    return simulators.get(individual);
  }
  
  public Bond getBond(OWLNamedIndividual individual) {
    return bonds.get(individual);
  }
  
  public Plug getPlug(OWLNamedIndividual individual) {
    return plugs.get(individual);
  }
  
  public Socket getSocket(OWLNamedIndividual individual) {
    return sockets.get(individual);
  }
  
  public Variable getVariable(OWLNamedIndividual individual) {
    return variables.get(individual);
  }
}
