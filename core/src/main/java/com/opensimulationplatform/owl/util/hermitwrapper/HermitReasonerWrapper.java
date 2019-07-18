package com.opensimulationplatform.owl.util.hermitwrapper;

import com.clarkparsia.owlapi.explanation.BlackBoxExplanation;
import com.clarkparsia.owlapi.explanation.HSTExplanationGenerator;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class HermitReasonerWrapper {
  
  private static final Logger LOG = LoggerFactory.getLogger(HermitReasonerWrapper.class);
  
  private final OWLOntology ontology;
  private final OWLReasoner reasoner;
  
  public HermitReasonerWrapper(OWLOntology ontology) {
    this.ontology = ontology;
    Configuration reasonerConfig = new Configuration();
    reasonerConfig.throwInconsistentOntologyException = false;
    this.reasoner = new Reasoner.ReasonerFactory().createReasoner(ontology, reasonerConfig);
  }
  
  public boolean isConsistent() {
    return reasoner.isConsistent();
  }
  
  public Set<OWLNamedIndividual> getInstances(OWLClassExpression classExpression) {
    return reasoner.getInstances(classExpression, true).getFlattened();
  }
  
  public Set<OWLNamedIndividual> getObjectPropertyValues(OWLNamedIndividual individual, OWLObjectPropertyExpression objectProperty) {
    return reasoner.getObjectPropertyValues(individual, objectProperty).getFlattened();
  }
  
  public Set<OWLClass> getDisjointClasses(OWLClassExpression classExpression) {
    return reasoner.getDisjointClasses(classExpression).getFlattened();
  }
  
  public Set<Set<OWLAxiom>> getExplanations() {
    LOG.debug("Computing explanations...");
    Reasoner.ReasonerFactory factory = new Reasoner.ReasonerFactory() {
      protected OWLReasoner createHermiTOWLReasoner(Configuration configuration, OWLOntology ontology) {
        configuration.throwInconsistentOntologyException = false;
        return new Reasoner(configuration, ontology);
      }
    };
    
    HSTExplanationGenerator explainer = new HSTExplanationGenerator(new BlackBoxExplanation(ontology, factory, reasoner));
    Set<Set<OWLAxiom>> explanations = explainer.getExplanations(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLThing(), 1);
    LOG.debug("done!");
    
    return explanations;
  }
}
