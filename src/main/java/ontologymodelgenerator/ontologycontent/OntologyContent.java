package ontologymodelgenerator.ontologycontent;

import ontologymodelgenerator.ontologyparser.ClassParser;
import ontologymodelgenerator.ontologyparser.DataPropertiesParser;
import ontologymodelgenerator.ontologyparser.IndividualsParser;
import ontologymodelgenerator.ontologyparser.ObjectPropertiesParser;
import org.semanticweb.owlapi.model.*;

import java.util.Map;

public class OntologyContent {
  private OWLOntology ontology;
  private IRI iri;
  private Map<String, OWLClass> classes;
  private Map<String, OWLObjectProperty> objectProperties;
  private Map<String, OWLDataProperty> dataProperties;
  private Map<String, OWLIndividual> individuals;
  
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
