package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.Plug;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses.PLUG;
import static com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties.HAS_VARIABLE;
import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;

class PlugConverter {
  public static OWLNamedIndividual convert(Plug plug, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual plugIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(plug));
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, PLUG);
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, plug.getType());
    owlConfiguration.addPlug(plugIndividual, plug);
  
    plug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, HAS_VARIABLE, variableIndividual);
    });
  
    return plugIndividual;
  }
}
