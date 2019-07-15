package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.Bond;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;

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
