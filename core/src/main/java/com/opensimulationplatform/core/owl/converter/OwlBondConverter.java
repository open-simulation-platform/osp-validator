package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class OwlBondConverter {
  public static OWLNamedIndividual convert(OspBond ospBond, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual bondIndividual = OwlHelper.getNamedIndividual(ontology, IndividualNameGenerator.getIndividualName(ospBond));
    OwlHelper.addClassAssertionAxiom(ontology, bondIndividual, OntologyClasses.BOND_CONNECTOR);
    owlConfiguration.addBond(bondIndividual, ospBond);
  
    ospBond.getOspPlugs().forEach(plug -> {
      OWLNamedIndividual plugIndividual = OwlPlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OntologyObjectProperties.BOND_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
  
    ospBond.getOspSockets().forEach(socket -> {
      OWLNamedIndividual socketIndividual = OwlSocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OntologyObjectProperties.BOND_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
  
    return bondIndividual;
  }
}
