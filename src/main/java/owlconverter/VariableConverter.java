package owlconverter;

import datamodel.Variable;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyClasses;

public class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual variableIndividual = dataFactory.getOWLNamedIndividual(variable.getId(), prefixManager);
    OWLClass variableClass = dataFactory.getOWLClass(OspOntologyClasses.VARIABLE, prefixManager);
    
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(variableClass, variableIndividual));
    
    return variableIndividual;
  }
}
