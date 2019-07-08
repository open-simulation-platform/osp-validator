package owlconverter;

import datamodel.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.SOCKET;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_VARIABLE;

public class SocketConverter {
  public static OWLNamedIndividual convert(Socket socket, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, socket.getId(), prefixManager);
    
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, SOCKET, prefixManager);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, socket.getType(), prefixManager);
    
    socket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, HAS_VARIABLE, variableIndividual, prefixManager);
    });
    
    return socketIndividual;
  }
}
