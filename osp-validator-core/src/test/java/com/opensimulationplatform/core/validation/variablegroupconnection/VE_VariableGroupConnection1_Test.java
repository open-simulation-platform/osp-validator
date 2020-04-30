package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableGroupConnectionOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableGroupConnection1_Test {

  private final VE_VariableGroupConnection_1 validationError = new VE_VariableGroupConnection_1();
  private final VariableGroupConnectionOwlBuilder builder = new VariableGroupConnectionOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variableGroupConnections = builderContext.variableGroupConnections;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Force va = new Force();
    va.setName("va");

    LinearVelocity vb = new LinearVelocity();
    vb.setName("vb");

    VariableGroupConnection vgc = new VariableGroupConnection();
    vgc.setVariableGroupA(va);
    vgc.setVariableGroupB(vb);
    vgc.setSimulatorA(new Simulator());
    vgc.setSimulatorB(new Simulator());

    builder.build(vgc);
    builder.complete();

    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    VariableGroupConnection invalidVariableConnection = diagnostics.get(0).getValidatedObject();
    assertEquals(vgc, invalidVariableConnection);
    assertEquals(va, invalidVariableConnection.getVariableGroupA());
    assertEquals(vb, invalidVariableConnection.getVariableGroupB());
  }

  @Test
  public void valid() {
    VariableGroup va = new Force();
    va.setName("va");

    VariableGroup vb = new Force();
    vb.setName("vb");

    VariableGroupConnection vgc = new VariableGroupConnection();
    vgc.setVariableGroupA(va);
    vgc.setVariableGroupB(vb);
    vgc.setSimulatorA(new Simulator());
    vgc.setSimulatorB(new Simulator());

    builder.build(vgc);
    builder.complete();

    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}