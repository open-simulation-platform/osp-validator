package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.Variable;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;

public class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(variable));
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OspOntologyClasses.VARIABLE);
    owlConfiguration.addVariable(variableIndividual, variable);
    
    return variableIndividual;
  }
}
