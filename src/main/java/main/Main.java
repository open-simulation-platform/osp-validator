package main;

import com.clarkparsia.owlapi.explanation.BlackBoxExplanation;
import com.clarkparsia.owlapi.explanation.DefaultExplanationGenerator;
import com.clarkparsia.owlapi.explanation.HSTExplanationGenerator;
import com.clarkparsia.owlapi.explanation.util.SilentExplanationProgressMonitor;
import datamodel.Configuration;
import datamodel.ConfigurationFactory;
import jsonmodel.parsing.ConfigurationJsonFileParser;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import owlconverter.ConfigurationConverter;

import java.io.File;
import java.util.Set;

public class Main {
  
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);
  
  public static void main(String[] args) throws Exception {
    File ospOwlFile = new File("./src/main/resources/osp.owl");
    File cseConfigFile = new File("./src/main/resources/json/cse-config.json");
    
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(cseConfigFile));
    
    OWLOntology configOntology = ConfigurationConverter.convert(config, ospOwlFile);
    
    org.semanticweb.HermiT.Configuration reasonerConfig = new org.semanticweb.HermiT.Configuration();
    reasonerConfig.throwInconsistentOntologyException = false;
    OWLReasoner reasoner = new Reasoner.ReasonerFactory().createReasoner(configOntology, reasonerConfig);
  
    if (!reasoner.isConsistent()) {
      LOG.error("Ontology is inconsistent!");
      LOG.error("Computing explanations for the inconsistency...");
      Reasoner.ReasonerFactory factory = new Reasoner.ReasonerFactory() {
        protected OWLReasoner createHermiTOWLReasoner(org.semanticweb.HermiT.Configuration configuration, OWLOntology ontology) {
          configuration.throwInconsistentOntologyException = false;
          return new Reasoner(configuration, ontology);
        }
      };
  
      BlackBoxExplanation exp = new BlackBoxExplanation(configOntology, factory, reasoner);
      HSTExplanationGenerator multExplanator = new HSTExplanationGenerator(exp);
      Set<Set<OWLAxiom>> explanations = multExplanator.getExplanations(configOntology.getOWLOntologyManager().getOWLDataFactory().getOWLThing());
      for (Set<OWLAxiom> explanation : explanations) {
        LOG.info("------------------");
        LOG.info("Axioms causing the inconsistency: ");
        for (OWLAxiom causingAxiom : explanation) {
          LOG.info(causingAxiom.toString());
        }
        LOG.info("------------------");
      }
    }
//    File configOwlFile = new File("./configuration.owl");
//    configOntology.getOWLOntologyManager().saveOntology(configOntology, System.out);
//    configOntology.getOWLOntologyManager().saveOntology(configOntology, IRI.create(configOwlFile));
  }
}
