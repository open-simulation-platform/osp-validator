package ontologymodelgenerator.ontologyparser;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ClassParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(ClassParser.class);
  
  public static Map<String, OWLClass> parse(OWLOntology ospOntology) {
    Map<String, OWLClass> map = new HashMap<>();
    ospOntology.getClassesInSignature().forEach(owlClass -> {
      IRI iri = owlClass.getIRI();
      Optional<String> remainder = iri.getRemainder();
      if (remainder.isPresent()) {
        LOG.debug("Adding class: " + iri.toString());
        map.put(remainder.get(), owlClass);
      } else {
        LOG.warn("IRI of class: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }
}