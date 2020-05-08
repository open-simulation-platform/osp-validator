package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableConnectionOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableConnection_1_Test {

  private final VE_VariableConnection_1 validationError = new VE_VariableConnection_1();
  private final VariableConnectionOwlBuilder builder = new VariableConnectionOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variableConnections = builderContext.variableConnections;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable va = new Variable();
    va.setName("va");
    va.setType(Variable.Type.REAL);
    va.setCausality(Variable.Causality.INPUT);

    Variable vb = new Variable();
    vb.setName("vb");
    vb.setType(Variable.Type.INTEGER);
    vb.setCausality(Variable.Causality.INPUT);

    VariableConnection vc = new VariableConnection();
    vc.setVariableA(va);
    vc.setVariableB(vb);
    vc.setSimulatorA(new Simulator());
    vc.setSimulatorB(new Simulator());

    builder.build(vc);
    builder.complete();

    List<ValidationDiagnostic<VariableConnection>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    VariableConnection invalidVariableConnection = diagnostics.get(0).getValidatedObject();
    assertEquals(vc, invalidVariableConnection);
    assertEquals(va, invalidVariableConnection.getVariableA());
    assertEquals(vb, invalidVariableConnection.getVariableB());
  }

  @Test
  public void valid() {
    Variable va = new Variable();
    va.setName("va");
    va.setType(Variable.Type.REAL);
    va.setCausality(Variable.Causality.INPUT);

    Variable vb = new Variable();
    vb.setName("vb");
    vb.setType(Variable.Type.REAL);
    vb.setCausality(Variable.Causality.INPUT);

    VariableConnection vc = new VariableConnection();
    vc.setVariableA(va);
    vc.setVariableB(vb);
    vc.setSimulatorA(new Simulator());
    vc.setSimulatorB(new Simulator());

    builder.build(vc);
    builder.complete();

    List<ValidationDiagnostic<VariableConnection>> diagnostics = validationError.validate();
    assertTrue(diagnostics.isEmpty());
  }
}