package reasoner;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.*;

public class ReasonerTest {
  
  private static <X, Y> String mapToString(final Map<X, Y> map, final char separator) {
    StringBuilder sb = new StringBuilder();
    
    for (Map.Entry<X, Y> e : map.entrySet()) {
      sb.append(e.getKey());
      sb.append(separator);
      sb.append(e.getValue());
      sb.append('\n');
    }
    
    return sb.toString();
  }
  
  private static Set<OWLNamedIndividual> getInstances(final OWLReasoner reasoner, final OWLClassExpression classExpression)
      throws OWLRuntimeException {
    Set<OWLNamedIndividual> result = new HashSet<>();
  
    NodeSet<OWLNamedIndividual> nodes = reasoner.getInstances(classExpression, true);
    
    for (Node<OWLNamedIndividual> node : nodes) {
      for (OWLNamedIndividual owlNamedIndividual : node) {
        result.add(owlNamedIndividual);
      }
    }
    
    return result;
  }
  
  private static Map<OWLClass, Set<OWLNamedIndividual>> getInstances(final OWLReasoner reasoner, final Set<OWLClass> classes) {
    Map<OWLClass, Set<OWLNamedIndividual>> result = new HashMap<>();
    
    for (OWLClass cls : classes) {
      result.put(cls, getInstances(reasoner, cls));
    }
    
    return result;
  }
  
  private static Set<String> convertToShortForm(final Set<? extends OWLEntity> entities, final DefaultPrefixManager prefixManager) {
    Set<String> result = new HashSet<>();
    
    for (OWLEntity entity : entities) {
      result.add(prefixManager.getShortForm(entity));
    }
    
    return result;
  }
  
  private static Map<String, Set<String>> convertToShortForm(final Map<OWLClass, Set<OWLNamedIndividual>> map, final DefaultPrefixManager prefixManager) {
    Map<String, Set<String>> result = new HashMap<>();
    
    for (Map.Entry<OWLClass, Set<OWLNamedIndividual>> entry : map.entrySet()) {
      OWLClass cls = entry.getKey();
      Set<OWLNamedIndividual> individuals = entry.getValue();
      
      result.put(prefixManager.getShortForm(cls), convertToShortForm(individuals, prefixManager));
    }
    
    return result;
  }
  
  private static void classify(String file_name) throws OWLOntologyCreationException {
    File file = new File(file_name);
    if (!file.exists() || file.isDirectory()) {
      throw new RuntimeException("Error: could not open file <" + file_name + ">");
    }
    
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    
    IRI documentIRI = IRI.create(file);
    OWLOntology ontology = manager.loadOntologyFromOntologyDocument(documentIRI);
    
    DefaultPrefixManager prefixManager = new DefaultPrefixManager();
    OWLOntologyFormat format = manager.getOntologyFormat(ontology); // works with OWLAPI 3.5.1
    if (format.isPrefixOWLOntologyFormat()) {
      Map<String, String> map = format.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap();
      for (String prefixName : map.keySet()) {
        prefixManager.setPrefix(prefixName, map.get(prefixName));
      }
    }
    
    Reasoner.ReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
    OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
    
    Set<OWLClass> classes = ontology.getClassesInSignature();
    
    Map<String, Set<String>> map_classification = convertToShortForm(getInstances(reasoner, classes), prefixManager);
    
    System.out.println(mapToString(map_classification, '='));
  }
  
  public static void main(String[] args) throws OWLOntologyCreationException {
    System.out.println("Ontology Classifier (v0.1)   DNV GL, IRM, 2019");
    classify("./configuration.owl");
  }
  
}
