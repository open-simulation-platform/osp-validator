package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owl.converter.SystemStructureConverter;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import com.opensimulationplatform.core.owl.util.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.core.util.resource.Resources;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

public class SystemStructureValidator {
  private static final Logger LOG = LoggerFactory.getLogger(SystemStructureValidator.class);
  
  public static Result validate(SystemStructure systemStructure, File ospOwlFile) {
    OwlSystemStructure owlSystemStructure = SystemStructureConverter.convert(systemStructure, ospOwlFile);
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(owlSystemStructure.getOntology());
    
    if (!reasoner.isConsistent()) {
      LOG.error("Configuration is inconsistent!");
      return new Result(systemStructure, createDiagnostic(owlSystemStructure, reasoner));
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(systemStructure);
    }
  }
  
  public static Result validate(SystemStructure systemStructure) {
    return validate(systemStructure, Resources.OSP_OWL.toFile());
  }
  
  private static Diagnostic createDiagnostic(OwlSystemStructure owlSystemStructure, HermitReasonerWrapper reasoner) {
    Set<Set<OWLAxiom>> explanations = reasoner.getExplanations();
    OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
    Diagnostic.Input input = createDiagnosticInput(owlSystemStructure, explanations, renderer);
    
    return new Diagnostic(input);
  }
  
  private static Diagnostic.Input createDiagnosticInput(OwlSystemStructure owlSystemStructure, Set<Set<OWLAxiom>> explanations, OWLObjectRenderer renderer) {
    StringBuilder messageBuilder = new StringBuilder();
    Diagnostic.Input input = new Diagnostic.Input();
    explanations.forEach(axioms -> axioms.forEach(axiom -> {
      addInvalidIndividuals(owlSystemStructure, input, axiom);
      messageBuilder.append(renderer.render(axiom.getAxiomWithoutAnnotations())).append("\n");
    }));
    input.message = messageBuilder.toString();
    return input;
  }
  
  private static void addInvalidIndividuals(OwlSystemStructure owlSystemStructure, Diagnostic.Input input, OWLAxiom axiom) {
    Set<OWLNamedIndividual> individuals = axiom.getIndividualsInSignature();
    individuals.forEach(i -> {
      OspSimulator simulator = owlSystemStructure.getSimulator(i);
      OspVariable variable = owlSystemStructure.getVariable(i);
      OspPlug plug = owlSystemStructure.getPlug(i);
      OspSocket socket = owlSystemStructure.getSocket(i);
      OspBond bond = owlSystemStructure.getBond(i);
      if (nonNull(simulator)) {
        input.invalidSimulators.add(simulator);
      } else if (nonNull(variable)) {
        input.invalidVariables.add(variable);
      } else if (nonNull(plug)) {
        input.invalidPlugs.add(plug);
      } else if (nonNull(socket)) {
        input.invalidSockets.add(socket);
      } else if (nonNull(bond)) {
        input.invalidBonds.add(bond);
      } else {
        throw new RuntimeException("This should never happen");
      }
    });
  }
  
  public static class Result {
    private final SystemStructure systemStructure;
    private final Diagnostic diagnostic;
    private final boolean success;
    
    private Result(SystemStructure systemStructure, Diagnostic diagnostic, boolean success) {
      this.systemStructure = systemStructure;
      this.diagnostic = diagnostic;
      this.success = success;
    }
    
    private Result(SystemStructure systemStructure) {
      this(systemStructure, new Diagnostic(), true);
    }
    
    private Result(SystemStructure systemStructure, Diagnostic diagnostic) {
      this(systemStructure, diagnostic, false);
    }
    
    public SystemStructure getSystemStructure() {
      return systemStructure;
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public Diagnostic getDiagnostics() {
      return diagnostic;
    }
  }
  
  public static class Diagnostic {
    private Input input;
    
    public Diagnostic(Input input) {
      this.input = input;
    }
    
    public Diagnostic() {
      this.input = new Input();
    }
    
    public String getMessage() {
      return input.message;
    }
    
    public Set<OspSimulator> getInvalidSimulators() {
      return input.invalidSimulators;
    }
    
    public Set<OspVariable> getInvalidVariables() {
      return input.invalidVariables;
    }
    
    public Set<OspPlug> getInvalidPlugs() {
      return input.invalidPlugs;
    }
    
    public Set<OspSocket> getInvalidSockets() {
      return input.invalidSockets;
    }
    
    public Set<OspBond> getInvalidBonds() {
      return input.invalidBonds;
    }
    
    private static class Input {
      String message = "";
      Set<OspSimulator> invalidSimulators = new HashSet<>();
      Set<OspVariable> invalidVariables = new HashSet<>();
      Set<OspPlug> invalidPlugs = new HashSet<>();
      Set<OspSocket> invalidSockets = new HashSet<>();
      Set<OspBond> invalidBonds = new HashSet<>();
    }
  }
}
