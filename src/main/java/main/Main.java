package main;

import datamodel.Configuration;
import datamodel.ConfigurationFactory;
import jsonmodel.parsing.ConfigurationJsonFileParser;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import owlconverter.ConfigurationConverter;

import java.io.File;

public class Main {
  public static void main(String[] args) throws Exception {
    File ospOwlFile = new File("./src/main/resources/osp.owl");
    File cseConfigFile = new File("./src/main/resources/json/cse-config.json");
  
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(cseConfigFile));
  
    OWLOntology configOntology = ConfigurationConverter.convert(config, ospOwlFile);
  
    File configOwlFile = new File("./configuration.owl");
    configOntology.getOWLOntologyManager().saveOntology(configOntology, System.out);
    configOntology.getOWLOntologyManager().saveOntology(configOntology, IRI.create(configOwlFile));
  }
}
