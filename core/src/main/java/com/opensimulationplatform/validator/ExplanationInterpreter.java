package com.opensimulationplatform.validator;

import com.opensimulationplatform.owl.util.hermitwrapper.HermitReasonerWrapper;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ExplanationInterpreter
{
    private static final Logger LOG = LoggerFactory.getLogger(ExplanationInterpreter.class);

    private static String iri_prefix = "http://www.dnvgl.com/irm/semantics/osp/rdl/";

    private static IRI iri_socket = IRI.create(iri_prefix, "socket");
    private static IRI iri_plug = IRI.create(iri_prefix,"plug");
    private static IRI iri_has_plug_mate = IRI.create(iri_prefix,"has_plug_mate");
    private static IRI iri_has_signal_connector_mate = IRI.create(iri_prefix,"has_signal_connector_mate");
    private static IRI iri_current = IRI.create(iri_prefix,"current");
    private static IRI iri_force = IRI.create(iri_prefix,"force");
    private static IRI iri_generic = IRI.create(iri_prefix,"generic");
    private static IRI iri_position = IRI.create(iri_prefix,"position");
    private static IRI iri_torque = IRI.create(iri_prefix,"torque");
    private static IRI iri_linear_velocity = IRI.create(iri_prefix,"linear_velocity");
    private static IRI iri_rotational_velocity = IRI.create(iri_prefix,"rotational_velocity");
    private static IRI iri_voltage = IRI.create(iri_prefix,"voltage");

    private static IRI[] iri_signal_types = {iri_current, iri_force, iri_generic, iri_position, iri_torque, iri_linear_velocity, iri_rotational_velocity, iri_voltage};

    private static OWLClass[] cls_signal_types = new OWLClass[iri_signal_types.length];
    private static OWLAxiom[] test_axioms = new OWLAxiom[iri_signal_types.length + 1];

    private static OWLClass cls_plug;
    private static OWLClass cls_socket;
    private static OWLObjectProperty has_plug_mate;
    private static OWLObjectProperty has_signal_connector_mate;
    private static OWLObjectMaxCardinality at_most_one_plug_mate;

    private static void check_for_and_explain_issue_type_1(final OWLOntology justification)
    {
        OWLAxiom test_axiom = test_axioms[iri_signal_types.length];

        if (!justification.containsAxiomIgnoreAnnotations(test_axiom)) return;

        LOG.error("A socket cannot have more than one plug mate.");

        // make justification consistent by removing the test axiom that has been found
        justification.getOWLOntologyManager().removeAxiom(justification,test_axiom);

        HermitReasonerWrapper reasoner = new HermitReasonerWrapper(justification);

        if (!reasoner.isConsistent())
        {
            LOG.error("Error: Something went wrong with explaning the inconsistency.");
            return;
        }

        // get socket instances
        Set<OWLNamedIndividual> instances_socket = reasoner.getInstances(cls_socket);

        instances_socket.forEach(ind1 -> {
            LOG.error(ind1 + " is a socket");
            reasoner.getObjectPropertyValues(ind1,has_plug_mate).forEach(ind2 -> {
                LOG.error(" has plug mate " + ind2);
            });
        });

        // add back the removed axiom
        justification.getOWLOntologyManager().addAxiom(justification,test_axiom);
    }

    private static void check_for_and_explain_issue_type_2(final OWLOntology justification)
    {
        int index = iri_signal_types.length;
        while (--index >= 0)
        {
            if (justification.containsAxiomIgnoreAnnotations(test_axioms[index])) break;
        }

        if (index < 0) return;

        LOG.error("A plug (socket) can only be connected to a socket (plug) of the same signal type.");

        // make justification consistent by removing the test axiom that has been found
        justification.getOWLOntologyManager().removeAxiom(justification,test_axioms[index]);

        HermitReasonerWrapper reasoner = new HermitReasonerWrapper(justification);

        if (!reasoner.isConsistent())
        {
            LOG.error("Error: Something went wrong with explaning the inconsistency.");
            return;
        }

        OWLClass signal_type1 = cls_signal_types[index];
        Set<OWLNamedIndividual> instances_signal_type1 = reasoner.getInstances(signal_type1);

        reasoner.getDisjointClasses(signal_type1).forEach(signal_type2 -> {

            if(signal_type2.isOWLNothing()) return;

            Set<OWLNamedIndividual> instances_signal_type2 = reasoner.getInstances(signal_type2);

            instances_signal_type1.forEach(ind1 -> {

                LOG.error(ind1 + " of signal type " + signal_type1);

                reasoner.getObjectPropertyValues(ind1,has_signal_connector_mate).forEach(ind2 -> {

                    if (instances_signal_type2.contains(ind2))
                    {
                        LOG.error("is connected via " + has_signal_connector_mate + " to");
                        LOG.error(ind2 + " of signal type " + signal_type2);
                    }
                });
            });
        });

        // add back the removed axiom
        justification.getOWLOntologyManager().addAxiom(justification,test_axioms[index]);
    }

    private static Set<OWLAxiom> getAxiomsWithoutAnnotations(final Collection<OWLAxiom> axioms)
    {
        Set<OWLAxiom> result = new HashSet<>();
        axioms.forEach(axiom -> result.add(axiom.getAxiomWithoutAnnotations()));
        return result;
    }

    public static void interpret(final MsmiValidator.Result result, Set<OWLAxiom> justification) throws OWLOntologyCreationException
    {
        OWLOntology ontology = result.getOwlConfiguration().getOntology();
        OWLDataFactory dataFactory = result.getOwlConfiguration().getOntology().getOWLOntologyManager().getOWLDataFactory();

        // construct symbols
        has_signal_connector_mate = dataFactory.getOWLObjectProperty(iri_has_signal_connector_mate);
        cls_plug = dataFactory.getOWLClass(iri_plug);
        cls_socket = dataFactory.getOWLClass(iri_socket);
        has_plug_mate = dataFactory.getOWLObjectProperty(iri_has_plug_mate);
        at_most_one_plug_mate = dataFactory.getOWLObjectMaxCardinality(1, has_plug_mate, cls_plug);

        // build and collect axioms used for analysing justifications
        for(int i = 0; i < iri_signal_types.length; i++)
        {
            cls_signal_types[i] = dataFactory.getOWLClass(iri_signal_types[i]);
            test_axioms[i] = dataFactory.getOWLSubClassOfAxiom(cls_signal_types[i], dataFactory.getOWLObjectAllValuesFrom(has_signal_connector_mate, cls_signal_types[i]));
        }
        test_axioms[iri_signal_types.length] = dataFactory.getOWLSubClassOfAxiom(cls_socket, at_most_one_plug_mate);

        // sanity check: test axioms should be contained in ontology -- could be omitted
        for(OWLAxiom axiom : test_axioms)
        {
            if (!ontology.containsAxiomIgnoreAnnotations(axiom))
            {
                LOG.error("Cannot interpret explanation (perhaps IRIs have changed?)");
                return;
            }
        }

        // check which of the test axioms are contained in the justification
        OWLOntology explanation_ontology = ontology.getOWLOntologyManager().createOntology(getAxiomsWithoutAnnotations(justification));
        check_for_and_explain_issue_type_1(explanation_ontology);
        check_for_and_explain_issue_type_2(explanation_ontology);
    }
}
