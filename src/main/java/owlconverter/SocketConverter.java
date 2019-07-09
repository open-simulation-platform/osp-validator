package owlconverter;

import datamodel.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.SOCKET;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_VARIABLE;

public class SocketConverter {
  public static OWLNamedIndividual convert(Socket socket, OWLOntology ontology) {
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, socket.getId());
    
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, SOCKET);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, socket.getType());
    
    socket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, HAS_VARIABLE, variableIndividual);
    });
    
    return socketIndividual;
  }
}
