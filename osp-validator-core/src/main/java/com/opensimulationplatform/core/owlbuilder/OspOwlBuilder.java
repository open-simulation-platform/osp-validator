package com.opensimulationplatform.core.owlbuilder;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

public abstract class OspOwlBuilder<T> {

  protected OwlBuilderContext context;

  public abstract OWLNamedIndividual build(T ospObject);

  public void setContext(OwlBuilderContext context) {
    this.context = context;
  }

  public OwlBuilderContext getContext() {
    return context;
  }

  public void complete() {
    context.owl.manager.addAxioms(context.owl.ontology, context.axioms);
    context.owl.manager.addAxiom(context.owl.ontology, context.owl.dataFactory.getOWLDifferentIndividualsAxiom(context.individuals));
  }
}
