package com.opensimulationplatform.gen.util.ontologyparser;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IndividualsParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(IndividualsParser.class);
  
  public static Map<String, OWLIndividual> parse(OWLOntology ontology) {
    Map<String, OWLIndividual> map = new HashMap<>();
    ontology.getIndividualsInSignature().forEach(individual -> {
      IRI iri = individual.getIRI();
      String fragment = iri.getFragment();
      if (fragment != null && !Objects.equals(fragment, "")) {
        LOG.debug("Adding individual: " + iri.toString());
        map.put(fragment, individual);
      } else {
        LOG.warn("IRI of individual: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }
  
}