package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_LinearMechanicalQuasiPort_1_Test {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.OUTPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.OUTPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Force force = new Force();
    force.setVariables(Arrays.asList(v1, v2));

    LinearDisplacement linearDisplacement = new LinearDisplacement();
    linearDisplacement.setVariables(Arrays.asList(v3, v4));

    LinearMechanicalQuasiPort linearMechanicalQuasiPort = new LinearMechanicalQuasiPort();
    linearMechanicalQuasiPort.setForce(force);
    linearMechanicalQuasiPort.setLinearDisplacement(linearDisplacement);

    modelDescription.getLinearMechanicalQuasiPorts().add(linearMechanicalQuasiPort);

    VE_LinearMechanicalQuasiPort_1 v = new VE_LinearMechanicalQuasiPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<LinearMechanicalQuasiPort>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    LinearMechanicalQuasiPort invalidLinearMechanicalQuasiPort = diagnostics.get(0).getValidatedObject();
    assertEquals(linearMechanicalQuasiPort, invalidLinearMechanicalQuasiPort);
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.INPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.INPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Force force = new Force();
    force.setVariables(Arrays.asList(v1, v2));

    LinearDisplacement linearDisplacement = new LinearDisplacement();
    linearDisplacement.setVariables(Arrays.asList(v3, v4));

    LinearMechanicalQuasiPort linearMechanicalQuasiPort = new LinearMechanicalQuasiPort();
    linearMechanicalQuasiPort.setForce(force);
    linearMechanicalQuasiPort.setLinearDisplacement(linearDisplacement);

    modelDescription.getLinearMechanicalQuasiPorts().add(linearMechanicalQuasiPort);

    VE_LinearMechanicalQuasiPort_1 v = new VE_LinearMechanicalQuasiPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<LinearMechanicalQuasiPort>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
