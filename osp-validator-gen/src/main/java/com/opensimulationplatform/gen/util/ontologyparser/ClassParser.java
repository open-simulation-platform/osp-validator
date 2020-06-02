/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.gen.util.ontologyparser;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClassParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(ClassParser.class);
  
  public static Map<String, OWLClass> parse(OWLOntology ospOntology) {
    Map<String, OWLClass> map = new HashMap<>();
    ospOntology.getClassesInSignature().forEach(owlClass -> {
      IRI iri = owlClass.getIRI();
      String fragment = iri.getFragment();
      if (fragment != null && !Objects.equals(fragment, "")) {
        LOG.debug("Adding class: " + iri.toString());
        map.put(fragment, owlClass);
      } else {
        LOG.warn("IRI of class: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }
}