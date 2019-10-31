package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

public abstract class OspOwlBuilder<T> {

  protected final OWLConfig config;
  protected final OWLOntology ontology;
  protected final OWLOntologyManager manager;
  protected final OWLDataFactory dataFactory;
  protected final PrefixManager prefixManager;
  protected final OWLReasoner reasoner;

  public OspOwlBuilder(OWLConfig config) {
    this.config = config;
    this.ontology = config.ontology;
    this.manager = config.manager;
    this.dataFactory = config.dataFactory;
    this.prefixManager = config.prefixManager;
    this.reasoner = config.reasoner;
  }

  public abstract OWLNamedIndividual build(T ospObject);
}
