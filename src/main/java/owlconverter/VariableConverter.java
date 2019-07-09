package owlconverter;

import datamodel.Variable;
import org.semanticweb.owlapi.model.*;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.*;

public class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OWLOntology ontology) {
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, variable.getId());
    
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, VARIABLE);
    
    return variableIndividual;
  }
}
