package com.opensimulationplatform.core.owl.util.ontologycontent;

import com.opensimulationplatform.core.owl.util.ontologyparser.ClassParser;
import com.opensimulationplatform.core.owl.util.ontologyparser.DataPropertiesParser;
import com.opensimulationplatform.core.owl.util.ontologyparser.IndividualsParser;
import com.opensimulationplatform.core.owl.util.ontologyparser.ObjectPropertiesParser;
import org.semanticweb.owlapi.model.*;

import java.util.Map;

public class OntologyContent {
  private final OWLOntology ontology;
  private final IRI iri;
  private final Map<String, OWLClass> classes;
  private final Map<String, OWLObjectProperty> objectProperties;
  private final Map<String, OWLDataProperty> dataProperties;
  private final Map<String, OWLIndividual> individuals;
  
  public OntologyContent(OWLOntology ontology) {
    this.iri = ontology.getOntologyID().getOntologyIRI();
    this.classes = ClassParser.parse(ontology);
    this.objectProperties = ObjectPropertiesParser.parse(ontology);
    this.dataProperties = DataPropertiesParser.parse(ontology);
    this.individuals = IndividualsParser.parse(ontology);
    this.ontology = ontology;
  }
  
  public Map<String, OWLClass> getClasses() {
    return classes;
  }
  
  public Map<String, OWLObjectProperty> getObjectProperties() {
    return objectProperties;
  }
  
  public Map<String, OWLDataProperty> getDataProperties() {
    return dataProperties;
  }
  
  public Map<String, OWLIndividual> getIndividuals() {
    return individuals;
  }
  
  public IRI getIri() {
    return iri;
  }
  
  public OWLOntology getOntology() {
    return ontology;
  }
}
