package owlconverter;

import datamodel.Plug;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.PLUG;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_VARIABLE;

public class PlugConverter {
  public static OWLNamedIndividual convert(Plug plug, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual plugIndividual = OwlHelper.getNamedIndividual(ontology, plug.getId(), prefixManager);
    
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, PLUG, prefixManager);
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, plug.getType(), prefixManager);
  
    plug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, HAS_VARIABLE, variableIndividual, prefixManager);
    });
  
    return plugIndividual;
  }
}
