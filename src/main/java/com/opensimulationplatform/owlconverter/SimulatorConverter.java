package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.Simulator;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses.MODEL;
import static com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties.HAS_BOND_CONNECTOR;
import static com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR;
import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;

public class SimulatorConverter {
  public static void convert(Simulator simulator, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(simulator));
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, MODEL);
    owlConfiguration.addSimulator(simulatorIndividual, simulator);
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_BOND_CONNECTOR, bondIndividual);
    });
  }
}
