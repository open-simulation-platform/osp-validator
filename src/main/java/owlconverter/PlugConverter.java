package owlconverter;

import datamodel.Plug;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyClasses;
import ospontologydatamodel.OspOntologyObjectProperties;

public class PlugConverter {
  public static OWLNamedIndividual convert(Plug plug, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual plugIndividual = dataFactory.getOWLNamedIndividual(plug.getId(), prefixManager);
    
    OWLClass plugClass = dataFactory.getOWLClass(OspOntologyClasses.PLUG, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(plugClass, plugIndividual));
    
    OWLClass typeClass = dataFactory.getOWLClass(plug.getType(), prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(typeClass, plugIndividual));
  
    plug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, ontology, prefixManager);
      OWLObjectProperty hasVariable = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_VARIABLE, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, plugIndividual, variableIndividual));
    });
  
    return plugIndividual;
  }
}
