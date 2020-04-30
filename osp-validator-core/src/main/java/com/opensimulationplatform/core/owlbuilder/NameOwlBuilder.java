package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.Name;

public class NameOwlBuilder extends OspOwlBuilder<Name> {
  @Override
  public OWLNamedIndividual build(Name name) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(name.getId().get(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.names.put(individual, name);

    OWLClass clazz = context.owl.dataFactory.getOWLClass(Name, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);

    return individual;
  }
}
