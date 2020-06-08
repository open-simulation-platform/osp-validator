/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.gen.util.ontologyparser;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataPropertiesParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(DataPropertiesParser.class);
  
  public static Map<String, OWLDataProperty> parse(OWLOntology ontology) {
    Map<String, OWLDataProperty> map = new HashMap<>();
    ontology.getDataPropertiesInSignature().forEach(dataProperty -> {
      IRI iri = dataProperty.getIRI();
      String fragment = iri.getFragment();
      if (fragment != null && !Objects.equals(fragment, "")) {
        LOG.debug("Adding data property: " + iri.toString());
        map.put(fragment, dataProperty);
      } else {
        LOG.warn("IRI of data property: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }
}