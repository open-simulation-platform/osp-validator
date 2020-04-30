package com.opensimulationplatform.core.owlconfig;

import com.opensimulationplatform.gen.util.resource.Resources;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.Map;

public class OWLConfig {
  public final OWLOntology ontology;
  public final OWLOntologyManager manager;
  public final OWLDataFactory dataFactory;
  public final PrefixManager prefixManager;
  public final OWLReasoner reasoner;

  public OWLConfig() {
    this.ontology = load(Resources.OSP_OWL.toFile());
    this.manager = ontology.getOWLOntologyManager();
    this.dataFactory = manager.getOWLDataFactory();
    this.prefixManager = createPrefixManager(ontology);
    this.reasoner = createReasoner(ontology);
  }

  public void save(File owlFile) {
    try {
      OWLOntologyFormat format = ontology.getOWLOntologyManager().getOntologyFormat(ontology);
      OWLXMLOntologyFormat owlFormat = new OWLXMLOntologyFormat();
      if (format.isPrefixOWLOntologyFormat()) {
        owlFormat.copyPrefixesFrom(format.asPrefixOWLOntologyFormat());
      }
      ontology.getOWLOntologyManager().saveOntology(ontology, owlFormat, IRI.create(owlFile.toURI()));
    } catch (OWLOntologyStorageException e) {
      throw new RuntimeException("Error saving ontology to " + owlFile, e);
    }
  }

  private static OWLOntology load(File owlFile) {
    try {
      return OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(owlFile);
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading ontology from " + owlFile, e);
    }
  }

  private OWLReasoner createReasoner(OWLOntology ontology) {
    return new Reasoner.ReasonerFactory().createNonBufferingReasoner(ontology);
  }

  private PrefixManager createPrefixManager(OWLOntology ontology) {
    DefaultPrefixManager prefixManager = new DefaultPrefixManager();
    OWLOntologyFormat format = ontology.getOWLOntologyManager().getOntologyFormat(ontology);
    if (format.isPrefixOWLOntologyFormat()) {
      Map<String, String> map = format.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap();
      for (String prefixName : map.keySet()) {
        prefixManager.setPrefix(prefixName, map.get(prefixName));
      }
    }
    return prefixManager;
  }
}
