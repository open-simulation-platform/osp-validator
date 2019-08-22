package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import com.opensimulationplatform.validator.model.ospmodeldescription.Plug;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.osp.model.OspOntologyClasses.PLUG;
import static com.opensimulationplatform.osp.model.OspOntologyObjectProperties.HAS_VARIABLE;
import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;

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
