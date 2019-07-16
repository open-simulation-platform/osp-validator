package com.opensimulationplatform.owl.helper;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OwlHelper {
  
  private static final Logger LOG = LoggerFactory.getLogger(OwlHelper.class);
  
  public static void addClassAssertionAxiom(OWLOntology ontology, OWLNamedIndividual individual, String className) {
    LOG.debug("Adding class assertion axiom... ");
    LOG.debug("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.debug("Individual: " + individual.toStringID());
    LOG.debug("Class:      " + className);
    
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLClass owlClass = dataFactory.getOWLClass(className, prefixManager);
    OWLClassAssertionAxiom axiom = dataFactory.getOWLClassAssertionAxiom(owlClass, individual);
    manager.addAxiom(ontology, axiom);
    
    LOG.debug("done!");
  }
  
  public static void addObjectPropertyAssertionAxiom(OWLOntology ontology, OWLNamedIndividual subject, String propertyName, OWLNamedIndividual object) {
    LOG.debug("Adding class assertion axiom... ");
    LOG.debug("Ontology:  " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.debug("Subject:   " + subject.toStringID());
    LOG.debug("Predicate: " + propertyName);
    LOG.debug("Object:    " + object.toStringID());
    
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLObjectProperty objectProperty = dataFactory.getOWLObjectProperty(propertyName, prefixManager);
    OWLObjectPropertyAssertionAxiom axiom = dataFactory.getOWLObjectPropertyAssertionAxiom(objectProperty, subject, object);
    manager.addAxiom(ontology, axiom);
    
    LOG.debug("done!");
  }
  
  public static OWLNamedIndividual getNamedIndividual(OWLOntology ontology, String individualName) {
    LOG.debug("Getting individual... ");
    LOG.debug("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.debug("Individual: " + individualName);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    try {
      OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(String.valueOf(IRI.create(URLEncoder.encode(individualName, "UTF-8"))), prefixManager);
      LOG.debug("done!");
      return individual;
    } catch (UnsupportedEncodingException e) {
      String message = "Error creating individual for " + individualName;
      LOG.error(message);
      throw new RuntimeException(message, e);
    }
  }
  
  public static boolean isClass(OWLOntology ontology, OWLNamedIndividual owlNamedIndividual, String className) {
    return owlNamedIndividual.getTypes(ontology).stream().anyMatch(owlClassExpression -> owlClassExpression.asOWLClass().getIRI().getFragment().equalsIgnoreCase(className));
  }
}
