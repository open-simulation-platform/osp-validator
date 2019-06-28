package ontologymodelgenerator.ontologyparser;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ObjectPropertiesParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(ObjectPropertiesParser.class);
  
  public static Map<String, OWLObjectProperty> parse(OWLOntology ontology) {
    Map<String, OWLObjectProperty> map = new HashMap<>();
    ontology.getObjectPropertiesInSignature().forEach(objectProperty -> {
      IRI iri = objectProperty.getIRI();
      Optional<String> remainder = iri.getRemainder();
      if (remainder.isPresent()) {
        LOG.debug("Adding object property: " + iri.toString());
        map.put(remainder.get(), objectProperty);
      } else {
        LOG.warn("IRI of object property: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }

}