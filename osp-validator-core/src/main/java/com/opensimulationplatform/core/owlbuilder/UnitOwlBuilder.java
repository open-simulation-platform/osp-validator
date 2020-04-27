package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import static com.opensimulationplatform.gen.model.OntologyClasses.Unit;

public class UnitOwlBuilder extends OspOwlBuilder<Unit> {
  public UnitOwlBuilder(OWLConfig config) {
    super(config);
  }

  @Override
  public OWLNamedIndividual build(Unit unit) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(unit.getId().get(), prefixManager);

    setClass(individual);
    setDisjointAxioms(individual);

    config.addUnit(individual, unit);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(Unit, prefixManager);
    OWLAxiom axiom = dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    manager.addAxiom(ontology, axiom);
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(Unit, prefixManager));
  }
}
