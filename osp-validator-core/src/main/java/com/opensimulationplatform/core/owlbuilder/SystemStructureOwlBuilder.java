package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.OSPSystemStructure;

public class SystemStructureOwlBuilder extends OspOwlBuilder<SystemStructure> {
  @Override
  public OWLNamedIndividual build(SystemStructure systemStructure) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(systemStructure.getId().get(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.systemStructures.put(individual, systemStructure);

    OWLClass clazz = context.owl.dataFactory.getOWLClass(OSPSystemStructure, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);

    buildSimulators(systemStructure);
    buildVariableConnection(systemStructure);
    buildVariableGroupConnections(systemStructure);

    return individual;
  }

  private void buildVariableConnection(SystemStructure systemStructure) {
    VariableConnectionOwlBuilder builder = new VariableConnectionOwlBuilder();
    builder.setContext(context);
    systemStructure.getVariableConnections().forEach(builder::build);
  }

  private void buildVariableGroupConnections(SystemStructure systemStructure) {
    VariableGroupConnectionOwlBuilder builder = new VariableGroupConnectionOwlBuilder();
    builder.setContext(context);
    systemStructure.getVariableGroupConnections().forEach(builder::build);
  }

  private void buildSimulators(SystemStructure systemStructure) {
    SimulatorOwlBuilder builder = new SimulatorOwlBuilder();
    builder.setContext(context);
    systemStructure.getSimulators().forEach(builder::build);
  }
}
