package owlhelper;

import org.semanticweb.owlapi.model.*;

public class OwlHelper {
  public static void addClassAssertionAxiom(OWLOntology ontology, OWLNamedIndividual individual, String className, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    OWLClass owlClass = dataFactory.getOWLClass(className, prefixManager);
    OWLClassAssertionAxiom axiom = dataFactory.getOWLClassAssertionAxiom(owlClass, individual);
    manager.addAxiom(ontology, axiom);
  }
  
  public static void addObjectPropertyAssertionAxiom(OWLOntology ontology, OWLNamedIndividual subject, String propertyName, OWLNamedIndividual object, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    OWLObjectProperty objectProperty = dataFactory.getOWLObjectProperty(propertyName, prefixManager);
    OWLObjectPropertyAssertionAxiom axiom = dataFactory.getOWLObjectPropertyAssertionAxiom(objectProperty, subject, object);
    manager.addAxiom(ontology, axiom);
  }
  
  public static OWLNamedIndividual getNamedIndividual(OWLOntology ontology, String individualName, PrefixManager prefixManager) {
    OWLDataFactory dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    return dataFactory.getOWLNamedIndividual(individualName, prefixManager);
  }
}
