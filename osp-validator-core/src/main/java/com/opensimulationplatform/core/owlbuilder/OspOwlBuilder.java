package com.opensimulationplatform.core.owlbuilder;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.io.File;
import java.util.Set;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.ValidationError;

public abstract class OspOwlBuilder<T> {

  protected OwlBuilderContext context;

  public abstract OWLNamedIndividual build(T ospObject);

  public void setContext(OwlBuilderContext context) {
    this.context = context;
  }

  public void complete() {
    context.owl.manager.addAxioms(context.owl.ontology, context.axioms);
    context.owl.manager.addAxiom(context.owl.ontology, context.owl.dataFactory.getOWLDifferentIndividualsAxiom(context.individuals));

    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(ValidationError, context.owl.prefixManager);
    Set<OWLClass> validationErrorClasses = context.owl.reasoner.getSubClasses(validationErrorClass, false).getFlattened();
    for (OWLClass errorClass : validationErrorClasses) {
      Set<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(errorClass, false).getFlattened();
      context.invalidIndividuals.put(errorClass, invalidIndividuals);
    }

    context.owl.save(new File("test.owl"));
  }
}
