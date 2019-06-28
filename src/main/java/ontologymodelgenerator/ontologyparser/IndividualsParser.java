package ontologymodelgenerator.ontologyparser;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class IndividualsParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(IndividualsParser.class);
  
  public static Map<String, OWLIndividual> parse(OWLOntology ontology) {
    Map<String, OWLIndividual> map = new HashMap<>();
    ontology.getIndividualsInSignature().forEach(individual -> {
      IRI iri = individual.getIRI();
      Optional<String> remainder = iri.getRemainder();
      if (remainder.isPresent()) {
        LOG.debug("Adding individual: " + iri.toString());
        map.put(remainder.get(), individual);
      } else {
        LOG.warn("IRI of individual: " + iri.toString() + " has no remainder. Not adding");
      }
    });
    return map;
  }

}