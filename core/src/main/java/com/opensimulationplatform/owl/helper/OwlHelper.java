package com.opensimulationplatform.owl.helper;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwlHelper {
  
  private static final Logger LOG = LoggerFactory.getLogger(OwlHelper.class);
  
  public static void addClassAssertionAxiom(OWLOntology ontology, OWLNamedIndividual individual, String className) {
    LOG.trace("Adding class assertion axiom... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Individual: " + individual.toStringID());
    LOG.trace("Class:      " + className);
    
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLClass owlClass = dataFactory.getOWLClass(className, prefixManager);
    OWLClassAssertionAxiom axiom = dataFactory.getOWLClassAssertionAxiom(owlClass, individual);
    manager.addAxiom(ontology, axiom);
    
    LOG.trace("done!");
  }
  
  public static void addObjectPropertyAssertionAxiom(OWLOntology ontology, OWLNamedIndividual subject, String propertyName, OWLNamedIndividual object) {
    LOG.trace("Adding object property assertion axiom... ");
    LOG.trace("Ontology:  " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Subject:   " + subject.toStringID());
    LOG.trace("Predicate: " + propertyName);
    LOG.trace("Object:    " + object.toStringID());
    
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLObjectProperty objectProperty = dataFactory.getOWLObjectProperty(propertyName, prefixManager);
    OWLObjectPropertyAssertionAxiom axiom = dataFactory.getOWLObjectPropertyAssertionAxiom(objectProperty, subject, object);
    manager.addAxiom(ontology, axiom);
    
    LOG.trace("done!");
  }
  
  public static OWLNamedIndividual getNamedIndividual(OWLOntology ontology, String individualName) {
    LOG.trace("Getting individual... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Individual: " + individualName);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(individualName, prefixManager);
    LOG.trace("done!");
    return individual;
  }
  
  public static OWLObjectProperty getObjectProperty(OWLOntology ontology, String objectPropertyName) {
    LOG.trace("Getting object property... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Object property: " + objectPropertyName);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLObjectProperty objectProperty = dataFactory.getOWLObjectProperty(objectPropertyName, prefixManager);
    LOG.trace("done!");
    return objectProperty;
  }
  
  public static OWLClass getClass(OWLOntology ontology, String className) {
    LOG.trace("Getting class... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Class: " + className);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    DefaultPrefixManager prefixManager = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
    
    OWLClass clazz = dataFactory.getOWLClass(className, prefixManager);
    LOG.trace("done!");
    return clazz;
  }
  
  public static OWLObjectMaxCardinality getObjectMaxCardinality(OWLOntology ontology, int cardinality, String objectPropertyName, String className) {
    LOG.trace("Getting object max cardinality... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Class: " + className);
    LOG.trace("Object property: " + objectPropertyName);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    OWLClass clazz = OwlHelper.getClass(ontology, className);
    OWLObjectProperty objectProperty = OwlHelper.getObjectProperty(ontology, objectPropertyName);
  
    OWLObjectMaxCardinality objectMaxCardinality = dataFactory.getOWLObjectMaxCardinality(cardinality, objectProperty, clazz);
    
    LOG.trace("done!");
    return objectMaxCardinality;
  }
  
  public static OWLObjectAllValuesFrom geObjectAllValuesFrom(OWLOntology ontology, String className, String objectPropertyName) {
    LOG.trace("Getting object all values from... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Object property: " + objectPropertyName);
    LOG.trace("Class: " + className);
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
  
    OWLClass clazz = getClass(ontology, className);
    OWLObjectProperty objectProperty = getObjectProperty(ontology, objectPropertyName);
    OWLObjectAllValuesFrom objectAllValuesFrom = dataFactory.getOWLObjectAllValuesFrom(objectProperty, clazz);
    
    LOG.trace("done!");
    return objectAllValuesFrom;
  }
  
  public static OWLAxiom getSubClassOfAxiom(OWLOntology ontology, OWLClassExpression superClass, OWLClassExpression subClass) {
    LOG.trace("Getting sub class of axiom... ");
    LOG.trace("Ontology:   " + ontology.getOntologyID().getOntologyIRI().toString());
    LOG.trace("Super class: " + superClass.toString());
    LOG.trace("Sub class: " + subClass.toString());
    
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    
    OWLSubClassOfAxiom subClassOfAxiom = dataFactory.getOWLSubClassOfAxiom(superClass, subClass);
    
    LOG.trace("done!");
    return subClassOfAxiom;
  }
  
  public static boolean isClass(OWLOntology ontology, OWLNamedIndividual owlNamedIndividual, String className) {
    return owlNamedIndividual.getTypes(ontology).stream().anyMatch(owlClassExpression -> owlClassExpression.asOWLClass().getIRI().getFragment().equalsIgnoreCase(className));
  }
}
