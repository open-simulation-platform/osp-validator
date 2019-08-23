package com.opensimulationplatform.core.owl.util.ontologyparser;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectPropertiesParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(ObjectPropertiesParser.class);
  
  public static Map<String, OWLObjectProperty> parse(OWLOntology ontology) {
    Map<String, OWLObjectProperty> map = new HashMap<>();
    ontology.getObjectPropertiesInSignature().forEach(objectProperty -> {
      IRI iri = objectProperty.getIRI();
      String fragment = iri.getFragment();
      if (fragment != null && !Objects.equals(fragment, "")) {
        LOG.debug("Adding object property: " + iri.toString());
        map.put(fragment, objectProperty);
      } else {
        LOG.warn("IRI of object property: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }
  
}