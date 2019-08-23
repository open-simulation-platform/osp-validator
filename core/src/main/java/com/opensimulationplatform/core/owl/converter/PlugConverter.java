package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Plug;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.NameConverter.getIndividualName;

class PlugConverter {
  public static OWLNamedIndividual convert(Plug plug, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual plugIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(plug));
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, OspOntologyClasses.PLUG);
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, plug.getType());
    owlConfiguration.addPlug(plugIndividual, plug);
  
    plug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OspOntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
  
    return plugIndividual;
  }
}
