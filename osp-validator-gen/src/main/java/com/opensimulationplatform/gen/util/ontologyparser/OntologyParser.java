/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.gen.util.ontologyparser;

import com.opensimulationplatform.gen.util.ontologycontent.OntologyContent;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class OntologyParser {
  public static OntologyContent parse(File ontologyFile) {
    try {
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology ontology = manager.loadOntologyFromOntologyDocument(ontologyFile);
      return new OntologyContent(ontology);
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Unable to load ontology from: " + ontologyFile.getAbsolutePath(), e);
    }
  }
}
