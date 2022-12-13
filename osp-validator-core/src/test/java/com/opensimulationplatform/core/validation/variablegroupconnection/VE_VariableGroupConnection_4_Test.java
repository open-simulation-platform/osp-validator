package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableGroupConnection_4_Test {

  private final VE_VariableGroupConnection_4 validationError = new VE_VariableGroupConnection_4();
  private final VariableGroupConnectionOwlBuilder builder = new VariableGroupConnectionOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variableGroupConnections = builderContext.variableGroupConnections;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setType(Variable.Type.REAL);
    v1.setCausality(Variable.Causality.INPUT);
    v1.setAxis(Variable.Axis.X);
    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setType(Variable.Type.REAL);
    v2.setCausality(Variable.Causality.INPUT);
    v2.setAxis(Variable.Axis.Y);

    Generic va = new Generic();
    va.setName("va");
    va.setVariables(Arrays.asList(v1, v2));

    Variable v3 = new Variable();
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.INPUT);
    v3.setAxis(Variable.Axis.Y);
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setType(Variable.Type.REAL);
    v4.setCausality(Variable.Causality.INPUT);
    v4.setAxis(Variable.Axis.Z);

    Generic vb = new Generic();
    vb.setName("vb");
    vb.setVariables(Arrays.asList(v3, v4));

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
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setType(Variable.Type.REAL);
    v1.setCausality(Variable.Causality.INPUT);
    v1.setAxis(Variable.Axis.X);
    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setType(Variable.Type.REAL);
    v2.setCausality(Variable.Causality.INPUT);
    v2.setAxis(Variable.Axis.Y);

    Generic va = new Generic();
    va.setName("va");
    va.setVariables(Arrays.asList(v1, v2));

    Variable v3 = new Variable();
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.INPUT);
    v3.setAxis(Variable.Axis.Y);
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setType(Variable.Type.REAL);
    v4.setCausality(Variable.Causality.INPUT);
    v4.setAxis(Variable.Axis.X);

    Generic vb = new Generic();
    vb.setName("vb");
    vb.setVariables(Arrays.asList(v3, v4));

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