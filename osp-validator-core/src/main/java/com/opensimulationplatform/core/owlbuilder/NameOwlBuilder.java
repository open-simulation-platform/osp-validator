package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.Name;

public class NameOwlBuilder extends OspOwlBuilder<Name> {

  public NameOwlBuilder(OWLConfig config) {
    super(config);
  }

  @Override
  public OWLNamedIndividual build(Name name) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(name.getId().get(), prefixManager);

    setClass(individual);
    setDisjointAxioms(individual);

    config.addName(individual, name);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(Name, prefixManager);
    OWLAxiom axiom = dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    manager.addAxiom(ontology, axiom);
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(Name, prefixManager));
  }
}
