package com.opensimulationplatform.cseconfig.util.explanationinterpreter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.util.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.cseconfig.validator.CseConfigurationValidator;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ExplanationInterpreter {
  private static final Logger LOG = LoggerFactory.getLogger(ExplanationInterpreter.class);
  
  private static void checkForAndExplainIssueType1(OWLOntology configuration, Set<OWLAxiom> explanations) throws OWLOntologyCreationException {
    OWLObjectMaxCardinality maxOnePlugMateCardinality = OwlHelper.getObjectMaxCardinality(configuration, 1, OspOntologyObjectProperties.HAS_PLUG_MATE, OspOntologyClasses.PLUG);
    OWLClass socketClass = OwlHelper.getClass(configuration, OspOntologyClasses.SOCKET);
    OWLAxiom testAxiom = OwlHelper.getSubClassOfAxiom(configuration, socketClass, maxOnePlugMateCardinality);
    
    OWLOntology justification = configuration.getOWLOntologyManager().createOntology(getAxiomsWithoutAnnotations(explanations));
    if (justification.containsAxiomIgnoreAnnotations(testAxiom)) {
      LOG.error("A socket cannot have more than one plug mate.");
      
      justification.getOWLOntologyManager().removeAxiom(justification, testAxiom);
      
      HermitReasonerWrapper reasoner = new HermitReasonerWrapper(justification);
      if (reasoner.isConsistent()) {
        Set<OWLNamedIndividual> socketIndividuals = reasoner.getInstances(socketClass);
        OWLObjectProperty hasPlugMate = OwlHelper.getObjectProperty(configuration, OspOntologyObjectProperties.HAS_PLUG_MATE);
        socketIndividuals.forEach(socket -> {
          LOG.error(socket + " is a socket");
          reasoner.getObjectPropertyValues(socket, hasPlugMate).forEach(plug -> LOG.error(" has plug mate " + plug));
        });
      } else {
        LOG.error("Error: Something went wrong with explaining the inconsistency.");
      }
    }
  }
  
  private static void checkForAndExplainIssueType2(OWLOntology configuration, Set<OWLAxiom> explanations) throws OWLOntologyCreationException {
    List<OWLAxiom> testAxioms = new ArrayList<>();
    List<String> signalTypes = getSignalTypes();
    signalTypes.forEach(signalType -> testAxioms.add(getSignalConnectionRestrictionAxiom(configuration, signalType)));
    
    OWLOntology justification = configuration.getOWLOntologyManager().createOntology(getAxiomsWithoutAnnotations(explanations));
    List<OWLAxiom> testAxiomsFoundInJustification = testAxioms.stream().filter(justification::containsAxiomIgnoreAnnotations).collect(Collectors.toList());
    if (!testAxiomsFoundInJustification.isEmpty()) {
      OWLAxiom testAxiom = testAxiomsFoundInJustification.get(0);
      justification.getOWLOntologyManager().removeAxiom(justification, testAxiom);
      
      LOG.error("A plug (socket) can only be connected to a socket (plug) of the same signal type.");
      
      HermitReasonerWrapper reasoner = new HermitReasonerWrapper(justification);
      if (reasoner.isConsistent()) {
        OWLObjectProperty hasSignalConnectorMate = OwlHelper.getObjectProperty(configuration, OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR_MATE);
        OWLClass signalClass = new ArrayList<>(testAxiom.getClassesInSignature()).get(0);
        Set<OWLNamedIndividual> signalIndividuals = reasoner.getInstances(signalClass);
        
        reasoner.getDisjointClasses(signalClass).stream()
            .filter(disjointClass -> !disjointClass.isOWLNothing())
            .forEach(disjointClass -> {
              Set<OWLNamedIndividual> disjointIndividuals = reasoner.getInstances(disjointClass);
              signalIndividuals.forEach(signalIndividual -> {
                LOG.error(signalIndividual + " of signal type " + signalClass);
                Set<OWLNamedIndividual> signalMateIndividuals = reasoner.getObjectPropertyValues(signalIndividual, hasSignalConnectorMate);
                signalMateIndividuals.forEach(signalMateIndividual -> {
                  if (disjointIndividuals.contains(signalMateIndividual)) {
                    LOG.error("is connected via " + hasSignalConnectorMate + " to");
                    LOG.error(signalMateIndividual + " of signal type " + disjointClass);
                  }
                });
              });
            });
      } else {
        LOG.error("Error: Something went wrong with explaining the inconsistency.");
      }
    }
  }
  
  private static Set<OWLAxiom> getAxiomsWithoutAnnotations(Collection<OWLAxiom> axioms) {
    Set<OWLAxiom> result = new HashSet<>();
    axioms.forEach(axiom -> result.add(axiom.getAxiomWithoutAnnotations()));
    return result;
  }
  
  private static OWLAxiom getSignalConnectionRestrictionAxiom(OWLOntology configuration, String signalType) {
    OWLObjectAllValuesFrom objectAllValuesFrom = OwlHelper.geObjectAllValuesFrom(configuration, signalType, OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR_MATE);
    OWLClass signalClass = OwlHelper.getClass(configuration, signalType);
    return OwlHelper.getSubClassOfAxiom(configuration, signalClass, objectAllValuesFrom);
  }
  
  private static List<String> getSignalTypes() {
    List<String> signalTypes = new ArrayList<>();
    signalTypes.add(OspOntologyClasses.CURRENT);
    signalTypes.add(OspOntologyClasses.FORCE);
    signalTypes.add(OspOntologyClasses.GENERIC);
    signalTypes.add(OspOntologyClasses.POSITION);
    signalTypes.add(OspOntologyClasses.TORQUE);
    signalTypes.add(OspOntologyClasses.LINEAR_VELOCITY);
    signalTypes.add(OspOntologyClasses.ROTATIONAL_VELOCITY);
    signalTypes.add(OspOntologyClasses.VOLTAGE);
    
    return signalTypes;
  }
  
  public static void interpret(CseConfigurationValidator.Result result, Set<OWLAxiom> explanations) throws OWLOntologyCreationException {
    OWLOntology ontology = result.getOwlConfiguration().getOntology();
    checkForAndExplainIssueType1(ontology, explanations);
    checkForAndExplainIssueType2(ontology, explanations);
  }
}
