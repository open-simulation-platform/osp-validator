/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.gen.util.ontologycontent;


import com.opensimulationplatform.gen.util.ontologyparser.ClassParser;
import com.opensimulationplatform.gen.util.ontologyparser.DataPropertiesParser;
import com.opensimulationplatform.gen.util.ontologyparser.IndividualsParser;
import com.opensimulationplatform.gen.util.ontologyparser.ObjectPropertiesParser;
import org.semanticweb.owlapi.model.*;

import java.util.Map;

public class OntologyContent {
  public static final String PREFIX = "osp:";

  private final Map<String, OWLClass> classes;
  private final Map<String, OWLObjectProperty> objectProperties;
  private final Map<String, OWLDataProperty> dataProperties;
  private final Map<String, OWLIndividual> individuals;

  public OntologyContent(OWLOntology ontology) {
    this.classes = ClassParser.parse(ontology);
    this.objectProperties = ObjectPropertiesParser.parse(ontology);
    this.dataProperties = DataPropertiesParser.parse(ontology);
    this.individuals = IndividualsParser.parse(ontology);
  }

  public Map<String, OWLClass> getClasses() {
    return classes;
  }

  public Map<String, OWLObjectProperty> getObjectProperties() {
    return objectProperties;
  }

  public Map<String, OWLDataProperty> getDataProperties() {
    return dataProperties;
  }

  public Map<String, OWLIndividual> getIndividuals() {
    return individuals;
  }
}
