package com.opensimulationplatform.core.validation.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SystemStructureValidatorTest {
  @Test
  public void invalid() {
    Simulator s1 = new Simulator();
    s1.setName("s1");
    s1.setModelDescription(createInvalidModelDescription());

    Simulator s2 = new Simulator();
    s2.setName("s2");
    s2.setModelDescription(createInvalidModelDescription());

    SystemStructure systemStructure = new SystemStructure();
    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    VariableConnection vc1 = new VariableConnection();
    vc1.setSimulatorA(s1);
    vc1.setSimulatorB(s2);
    vc1.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc1.setVariableB(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v4"));

    VariableConnection vc2 = new VariableConnection();
    vc2.setSimulatorA(s1);
    vc2.setSimulatorB(s2);
    vc2.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc2.setVariableB(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v5"));

    VariableConnection vc3 = new VariableConnection();
    vc3.setSimulatorA(s1);
    vc3.setSimulatorB(s2);
    vc3.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc3.setVariableB(ModelDescriptionUtil.getVariableByName(s2.getModelDescription(), "v6"));

    systemStructure.getVariableConnections().add(vc1);
    systemStructure.getVariableConnections().add(vc2);
    systemStructure.getVariableConnections().add(vc3);

    VariableGroupConnection vgc1 = new VariableGroupConnection();
    vgc1.setSimulatorA(s1);
    vgc1.setSimulatorB(s2);
    vgc1.setVariableGroupA(ModelDescriptionUtil.getVariableGroupByName(s1.getModelDescription(), "f3"));
    vgc1.setVariableGroupB(ModelDescriptionUtil.getVariableGroupByName(s2.getModelDescription(), "lv1"));

    systemStructure.getVariableGroupConnections().add(vgc1);

    SystemStructureValidator v = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = v.validate(systemStructure);

    assertEquals(32, diagnostics.size());
  }

  @Test
  public void valid() {
    Simulator s1 = new Simulator();
    s1.setName("s1");
    s1.setModelDescription(createValidModelDescription());

    Simulator s2 = new Simulator();
    s2.setName("s2");
    s2.setModelDescription(createValidModelDescription());

    SystemStructure systemStructure = new SystemStructure();
    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    VariableConnection vc1 = new VariableConnection();
    vc1.setSimulatorA(s1);
    vc1.setSimulatorB(s2);
    vc1.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc1.setVariableB(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v4"));

    VariableConnection vc2 = new VariableConnection();
    vc2.setSimulatorA(s1);
    vc2.setSimulatorB(s2);
    vc2.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc2.setVariableB(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v5"));

    VariableConnection vc3 = new VariableConnection();
    vc3.setSimulatorA(s1);
    vc3.setSimulatorB(s2);
    vc3.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v3"));
    vc3.setVariableB(ModelDescriptionUtil.getVariableByName(s2.getModelDescription(), "v6"));

    systemStructure.getVariableConnections().add(vc1);
    systemStructure.getVariableConnections().add(vc2);
    systemStructure.getVariableConnections().add(vc3);

    VariableGroupConnection vgc1 = new VariableGroupConnection();
    vgc1.setSimulatorA(s1);
    vgc1.setSimulatorB(s2);
    vgc1.setVariableGroupA(ModelDescriptionUtil.getVariableGroupByName(s1.getModelDescription(), "f3"));
    vgc1.setVariableGroupB(ModelDescriptionUtil.getVariableGroupByName(s2.getModelDescription(), "f4"));

    systemStructure.getVariableGroupConnections().add(vgc1);

    SystemStructureValidator v = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = v.validate(systemStructure);

    assertEquals(0, diagnostics.size());
  }

  private ModelDescription createInvalidModelDescription() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.UNDEFINED);
    v1.setType(Variable.Type.REAL);
    v1.setName("not-unique");

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.INTEGER);
    v2.setName("not-unique");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Generic g = new Generic();
    g.setName("variable-container");
    g.setVariables(Arrays.asList(v1, v2));
    modelDescription.getGenerics().add(g);

    Force f1 = new Force();
    f1.setName("not-unique");
    Variable v3 = new Variable();
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.OUTPUT);
    Unit u1 = new Unit();
    u1.setExponent(Unit.Exponent.KILOGRAM, 1);
    v3.setUnit(u1);
    f1.setVariables(Arrays.asList(v3));

    modelDescription.getVariables().add(v3);

    Force f2 = new Force();
    f2.setName("not-unique");
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setType(Variable.Type.INTEGER);
    v4.setCausality(Variable.Causality.INPUT);
    f2.setVariables(Arrays.asList(v4));

    modelDescription.getVariables().add(v4);
    modelDescription.getForces().add(f1);
    modelDescription.getForces().add(f2);

    Variable v5 = new Variable();
    v5.setName("v5");
    v5.setCausality(Variable.Causality.OUTPUT);
    v5.setType(Variable.Type.REAL);
    Variable v6 = new Variable();
    v6.setName("v6");
    v6.setCausality(Variable.Causality.INPUT);
    v6.setType(Variable.Type.REAL);
    Unit u2 = new Unit();
    u2.setExponent(Unit.Exponent.KILOGRAM, 2);
    v6.setUnit(u2);
    Force f3 = new Force();
    f3.setName("f3");
    f3.setVariables(Arrays.asList(v5, v6));

    modelDescription.getVariables().add(v5);
    modelDescription.getVariables().add(v6);
    modelDescription.getForces().add(f3);

    Variable v7 = new Variable();
    v7.setName("v7");
    v7.setCausality(Variable.Causality.OUTPUT);
    v7.setType(Variable.Type.REAL);
    Variable v8 = new Variable();
    v8.setName("v8");
    v8.setCausality(Variable.Causality.OUTPUT);
    v8.setType(Variable.Type.REAL);
    Force f4 = new Force();
    f4.setName("f4");
    f4.setVariables(Arrays.asList(v7, v8));

    modelDescription.getVariables().add(v7);
    modelDescription.getVariables().add(v8);
    modelDescription.getForces().add(f4);

    Variable v9 = new Variable();
    v9.setName("v9");
    v9.setCausality(Variable.Causality.OUTPUT);
    v9.setType(Variable.Type.REAL);
    Variable v10 = new Variable();
    v10.setName("v10");
    v10.setCausality(Variable.Causality.OUTPUT);
    v10.setType(Variable.Type.REAL);
    LinearVelocity lv1 = new LinearVelocity();
    lv1.setName("lv1");
    lv1.setVariables(Arrays.asList(v9, v10));

    modelDescription.getVariables().add(v9);
    modelDescription.getVariables().add(v10);
    modelDescription.getLinearVelocities().add(lv1);

    LinearMechanicalPort lmp1 = new LinearMechanicalPort();
    lmp1.setName("lmp1");
    lmp1.setForce(f4);
    lmp1.setLinearVelocity(lv1);

    modelDescription.getLinearMechanicalPorts().add(lmp1);

    return modelDescription;
  }

  private ModelDescription createValidModelDescription() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setType(Variable.Type.REAL);
    v1.setName("v1");

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.INTEGER);
    v2.setName("v2");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Force f1 = new Force();
    f1.setName("f1");
    Variable v3 = new Variable();
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.OUTPUT);
    Unit u1 = new Unit();
    u1.setExponent(Unit.Exponent.KILOGRAM, 1);
    v3.setUnit(u1);
    f1.setVariables(Arrays.asList(v3));

    modelDescription.getUnits().add(u1);
    modelDescription.getVariables().add(v3);

    Force f2 = new Force();
    f2.setName("f2");
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setUnit(u1);
    v4.setType(Variable.Type.REAL);
    v4.setCausality(Variable.Causality.INPUT);
    f2.setVariables(Arrays.asList(v4));

    modelDescription.getVariables().add(v4);
    modelDescription.getForces().add(f1);
    modelDescription.getForces().add(f2);

    Variable v5 = new Variable();
    v5.setName("v5");
    v5.setCausality(Variable.Causality.INPUT);
    v5.setType(Variable.Type.REAL);
    v5.setUnit(u1);
    Variable v6 = new Variable();
    v6.setName("v6");
    v6.setCausality(Variable.Causality.INPUT);
    v6.setType(Variable.Type.REAL);
    v6.setUnit(u1);
    Force f3 = new Force();
    f3.setName("f3");
    f3.setVariables(Arrays.asList(v5, v6));

    modelDescription.getVariables().add(v5);
    modelDescription.getVariables().add(v6);
    modelDescription.getForces().add(f3);

    Variable v7 = new Variable();
    v7.setName("v7");
    v7.setCausality(Variable.Causality.OUTPUT);
    v7.setType(Variable.Type.REAL);
    v7.setUnit(u1);
    Variable v8 = new Variable();
    v8.setName("v8");
    v8.setCausality(Variable.Causality.OUTPUT);
    v8.setType(Variable.Type.REAL);
    v8.setUnit(u1);
    Force f4 = new Force();
    f4.setName("f4");
    f4.setVariables(Arrays.asList(v7, v8));

    modelDescription.getVariables().add(v7);
    modelDescription.getVariables().add(v8);
    modelDescription.getForces().add(f4);

    Variable v9 = new Variable();
    v9.setName("v9");
    v9.setCausality(Variable.Causality.INPUT);
    v9.setType(Variable.Type.REAL);
    v9.setUnit(u1);
    Variable v10 = new Variable();
    v10.setName("v10");
    v10.setCausality(Variable.Causality.INPUT);
    v10.setType(Variable.Type.REAL);
    v10.setUnit(u1);
    LinearVelocity lv1 = new LinearVelocity();
    lv1.setName("lv1");
    lv1.setVariables(Arrays.asList(v9, v10));

    modelDescription.getVariables().add(v9);
    modelDescription.getVariables().add(v10);
    modelDescription.getLinearVelocities().add(lv1);

    LinearMechanicalPort lmp1 = new LinearMechanicalPort();
    lmp1.setName("lmp1");
    lmp1.setForce(f4);
    lmp1.setLinearVelocity(lv1);

    modelDescription.getLinearMechanicalPorts().add(lmp1);

    return modelDescription;
  }

  @Test
  public void emptySystemStructure() {
    SystemStructure systemStructure = new SystemStructure();
    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);
    assertTrue(diagnostics.isEmpty());
  }
}