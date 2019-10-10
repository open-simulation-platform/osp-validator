package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.OspObject;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public class SystemStructureValidator {
  private static final Logger LOG = LoggerFactory.getLogger(SystemStructureValidator.class);
  
  public static Result validate(SystemStructure systemStructure, File ospOwlFile) {
    OwlSystemStructure owlSystemStructure = SystemStructureConverter.convert(systemStructure, ospOwlFile);
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(owlSystemStructure.getOntology());
    
    if (!reasoner.isConsistent()) {
      LOG.error("Configuration is inconsistent!");
      return new Result(systemStructure, createDiagnostics(owlSystemStructure, reasoner.getExplanations()));
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(systemStructure);
    }
  }
  
  public static Result validate(SystemStructure systemStructure) {
    return validate(systemStructure, Resources.OSP_OWL.toFile());
  }
  
  private static Set<Diagnostic> createDiagnostics(OwlSystemStructure owlSystemStructure, Set<Set<OWLAxiom>> explanations) {
    Set<Diagnostic> diagnostics = new HashSet<>();
    explanations.forEach(explanation -> {
      diagnostics.add(createDiagnostic(owlSystemStructure, explanation));
    });
    
    return diagnostics;
  }
  
  private static Diagnostic createDiagnostic(OwlSystemStructure owlSystemStructure, Set<OWLAxiom> explanation) {
    Set<OspObject> invalidObjects = new HashSet<>();
    StringBuilder messageBuilder = new StringBuilder();
    explanation.forEach(axiom -> {
      invalidObjects.addAll(getInvalidObjects(owlSystemStructure, axiom));
      messageBuilder.append(getMessage(axiom));
      messageBuilder.append("\n");
    });
    
    return new Diagnostic(messageBuilder.toString(), invalidObjects);
  }
  
  private static String getMessage(OWLAxiom axiom) {
    OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
    return renderer.render(axiom.getAxiomWithoutAnnotations());
  }
  
  private static Set<OspObject> getInvalidObjects(OwlSystemStructure owlSystemStructure, OWLAxiom axiom) {
    Set<OspObject> invalidObjects = new HashSet<>();
    Set<OWLNamedIndividual> individuals = axiom.getIndividualsInSignature();
    individuals.forEach(i -> {
      OspSimulator simulator = owlSystemStructure.getSimulator(i);
      OspVariable variable = owlSystemStructure.getVariable(i);
      OspPlug plug = owlSystemStructure.getPlug(i);
      OspSocket socket = owlSystemStructure.getSocket(i);
      OspBond bond = owlSystemStructure.getBond(i);
      if (nonNull(simulator)) {
        invalidObjects.add(simulator);
      } else if (nonNull(variable)) {
        invalidObjects.add(variable);
      } else if (nonNull(plug)) {
        invalidObjects.add(plug);
      } else if (nonNull(socket)) {
        invalidObjects.add(socket);
      } else if (nonNull(bond)) {
        invalidObjects.add(bond);
      } else {
        throw new RuntimeException("This should never happen");
      }
    });
    
    return invalidObjects;
  }
  
  public static class Result {
    private final SystemStructure systemStructure;
    private final Set<Diagnostic> diagnostics;
    private final boolean success;
    
    private Result(SystemStructure systemStructure, Set<Diagnostic> diagnostics, boolean success) {
      this.systemStructure = systemStructure;
      this.diagnostics = diagnostics;
      this.success = success;
    }
    
    private Result(SystemStructure systemStructure) {
      this(systemStructure, new HashSet<>(), true);
    }
    
    private Result(SystemStructure systemStructure, Set<Diagnostic> diagnostics) {
      this(systemStructure, diagnostics, false);
    }
    
    public SystemStructure getSystemStructure() {
      return systemStructure;
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public List<Diagnostic> getDiagnostics() {
      return new ArrayList<>(diagnostics);
    }
  }
  
  public static class Diagnostic {
    private final String message;
    private final Set<OspObject> ospObjects;
    
    private Diagnostic(String message, Set<OspObject> ospObjects) {
      this.message = message;
      this.ospObjects = ospObjects;
    }
  
    public String getMessage() {
      return message;
    }
  
    public List<OspObject> getOspObjects() {
      return new ArrayList<>(ospObjects);
    }
  }
}
