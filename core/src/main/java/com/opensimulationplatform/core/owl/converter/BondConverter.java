package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Bond;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class BondConverter {
  public static OWLNamedIndividual convert(Bond bond, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual bondIndividual = OwlHelper.getNamedIndividual(ontology, NameConverter.getIndividualName(bond));
    OwlHelper.addClassAssertionAxiom(ontology, bondIndividual, OspOntologyClasses.BOND_CONNECTOR);
    owlConfiguration.addBond(bondIndividual, bond);
  
    bond.getPlugs().forEach(plug -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OspOntologyObjectProperties.BOND_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
  
    bond.getSockets().forEach(socket -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OspOntologyObjectProperties.BOND_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
  
    return bondIndividual;
  }
}
