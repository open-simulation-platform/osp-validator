package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.Variable;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.owlhelper.OwlHelper;
import org.semanticweb.owlapi.model.*;

public class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OWLOntology ontology) {
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, variable.getId());
    
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OspOntologyClasses.VARIABLE);
    
    return variableIndividual;
  }
}
