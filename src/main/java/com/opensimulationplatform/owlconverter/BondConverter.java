package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.Bond;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import org.semanticweb.owlapi.model.*;

public class BondConverter {
  public static OWLNamedIndividual convert(Bond bond, OWLOntology ontology) {
    OWLNamedIndividual bondIndividual = OwlHelper.getNamedIndividual(ontology, bond.getId());
    
    OwlHelper.addClassAssertionAxiom(ontology, bondIndividual, OspOntologyClasses.BOND_CONNECTOR);
  
    bond.getPlugs().forEach(plug -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, plugIndividual);
    });
  
    bond.getSockets().forEach(socket -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, socketIndividual);
    });
  
    return bondIndividual;
  }
}
