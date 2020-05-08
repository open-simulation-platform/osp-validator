package com.opensimulationplatform.core.validation.simulator;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulatorsValidatorTest {
  @Test
  public void invalid() {
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


    Force f1 = new Force();
    f1.setName("not-unique");
    Variable v3 = new Variable();
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.OUTPUT);
    f1.setVariables(Arrays.asList(v3));

    Force f2 = new Force();
    f2.setName("not-unique");
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setType(Variable.Type.INTEGER);
    v4.setCausality(Variable.Causality.INPUT);
    f2.setVariables(Arrays.asList(v4));

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
    Force f3 = new Force();
    f3.setName("f3");
    f3.setVariables(Arrays.asList(v5, v6));

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

    LinearMechanicalPort lmp1 = new LinearMechanicalPort();
    lmp1.setName("lmp1");
    lmp1.setForce(f4);
    lmp1.setLinearVelocity(lv1);

    modelDescription.getLinearMechanicalPorts().add(lmp1);

    Simulator s1 = new Simulator();
    s1.setName("not-unique");
    s1.setModelDescription(modelDescription);

    Simulator s2 = new Simulator();
    s2.setName("not-unique");
    s2.setModelDescription(modelDescription);

    SystemStructure systemStructure = new SystemStructure();
    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    SimulatorsValidator v = new SimulatorsValidator();
    List<ValidationDiagnostic<Object>> diagnostics = v.validate(systemStructure);

    assertEquals(12, diagnostics.size());
  }

  @Test
  public void valid() {
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
    f1.setVariables(Arrays.asList(v3));

    Force f2 = new Force();
    f2.setName("f2");
    Variable v4 = new Variable();
    v4.setName("v4");
    v4.setType(Variable.Type.INTEGER);
    v4.setCausality(Variable.Causality.INPUT);
    f2.setVariables(Arrays.asList(v4));

    modelDescription.getForces().add(f1);
    modelDescription.getForces().add(f2);


    Variable v5 = new Variable();
    v5.setName("v5");
    v5.setCausality(Variable.Causality.OUTPUT);
    v5.setType(Variable.Type.REAL);
    Variable v6 = new Variable();
    v6.setName("v6");
    v6.setCausality(Variable.Causality.OUTPUT);
    v6.setType(Variable.Type.REAL);
    Force f3 = new Force();
    f3.setName("f3");
    f3.setVariables(Arrays.asList(v5, v6));

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

    Variable v9 = new Variable();
    v9.setName("v9");
    v9.setCausality(Variable.Causality.INPUT);
    v9.setType(Variable.Type.REAL);
    Variable v10 = new Variable();
    v10.setName("v10");
    v10.setCausality(Variable.Causality.INPUT);
    v10.setType(Variable.Type.REAL);
    LinearVelocity lv1 = new LinearVelocity();
    lv1.setName("lv1");
    lv1.setVariables(Arrays.asList(v9, v10));

    LinearMechanicalPort lmp1 = new LinearMechanicalPort();
    lmp1.setName("lmp1");
    lmp1.setForce(f4);
    lmp1.setLinearVelocity(lv1);

    modelDescription.getLinearMechanicalPorts().add(lmp1);

    Simulator s1 = new Simulator();
    s1.setName("s1");
    s1.setModelDescription(modelDescription);

    Simulator s2 = new Simulator();
    s2.setName("s2");
    s2.setModelDescription(modelDescription);

    SystemStructure systemStructure = new SystemStructure();
    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    SimulatorsValidator v = new SimulatorsValidator();
    List<ValidationDiagnostic<Object>> diagnostics = v.validate(systemStructure);

    assertTrue(diagnostics.isEmpty());
  }
}