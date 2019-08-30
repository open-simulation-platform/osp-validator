package com.opensimulationplatform.core.owl.model;

import com.opensimulationplatform.core.model.configuration.OspConfiguration;
import com.opensimulationplatform.core.model.configuration.OspSimulator;
import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.HashMap;
import java.util.Map;

public class OwlConfiguration {
  private OWLOntology ontology;
  private OspConfiguration ospConfiguration;
  private final Map<OWLNamedIndividual, OspSimulator> simulators = new HashMap<>();
  private final Map<OWLNamedIndividual, OspBond> bonds = new HashMap<>();
  private final Map<OWLNamedIndividual, OspPlug> plugs = new HashMap<>();
  private final Map<OWLNamedIndividual, OspSocket> sockets = new HashMap<>();
  private final Map<OWLNamedIndividual, OspVariable> variables = new HashMap<>();
  
  public OWLOntology getOntology() {
    return ontology;
  }
  
  public OspConfiguration getOspConfiguration() {
    return ospConfiguration;
  }
  
  public void setOntology(OWLOntology ontology) {
    this.ontology = ontology;
  }
  
  public void setOspConfiguration(OspConfiguration ospConfiguration) {
    this.ospConfiguration = ospConfiguration;
  }
  
  public Map<OWLNamedIndividual, OspSimulator> getSimulators() {
    return simulators;
  }
  
  public Map<OWLNamedIndividual, OspBond> getBonds() {
    return bonds;
  }
  
  public Map<OWLNamedIndividual, OspPlug> getPlugs() {
    return plugs;
  }
  
  public Map<OWLNamedIndividual, OspSocket> getSockets() {
    return sockets;
  }
  
  public Map<OWLNamedIndividual, OspVariable> getVariables() {
    return variables;
  }
  
  public void addSimulator(OWLNamedIndividual individual, OspSimulator ospSimulator) {
    simulators.put(individual, ospSimulator);
  }
  
  public void addBond(OWLNamedIndividual individual, OspBond ospBond) {
    bonds.put(individual, ospBond);
  }
  
  public void addPlug(OWLNamedIndividual individual, OspPlug ospPlug) {
    plugs.put(individual, ospPlug);
  }
  
  public void addSocket(OWLNamedIndividual individual, OspSocket ospSocket) {
    sockets.put(individual, ospSocket);
  }
  
  public void addVariable(OWLNamedIndividual individual, OspVariable ospVariable) {
    variables.put(individual, ospVariable);
  }
  
  public OspSimulator getSimulator(OWLNamedIndividual individual) {
    return simulators.get(individual);
  }
  
  public OspBond getBond(OWLNamedIndividual individual) {
    return bonds.get(individual);
  }
  
  public OspPlug getPlug(OWLNamedIndividual individual) {
    return plugs.get(individual);
  }
  
  public OspSocket getSocket(OWLNamedIndividual individual) {
    return sockets.get(individual);
  }
  
  public OspVariable getVariable(OWLNamedIndividual individual) {
    return variables.get(individual);
  }
}
