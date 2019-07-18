package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.osp.model.OspOntologyClasses;
import com.opensimulationplatform.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import com.opensimulationplatform.validator.model.modeldefinition.Bond;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;

class BondConverter {
  public static OWLNamedIndividual convert(Bond bond, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual bondIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(bond));
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
