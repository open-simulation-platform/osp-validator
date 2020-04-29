package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinearMechanicalQuasiPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Force force = new Force();
    force.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    LinearDisplacement linearDisplacement = new LinearDisplacement();
    linearDisplacement.setVariables(Arrays.asList(v2));

    LinearMechanicalQuasiPort linearMechanicalQuasiPort = new LinearMechanicalQuasiPort();
    linearMechanicalQuasiPort.setForce(force);
    linearMechanicalQuasiPort.setLinearDisplacement(linearDisplacement);

    modelDescription.getLinearMechanicalQuasiPorts().add(linearMechanicalQuasiPort);

    LinearMechanicalQuasiPortValidator linearMechanicalQuasiPortValidator = new LinearMechanicalQuasiPortValidator();
    List<ValidationDiagnostic<LinearMechanicalQuasiPort>> diagnostics = linearMechanicalQuasiPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(linearMechanicalQuasiPort, diagnostics.get(0).getValidatedObject());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Force force = new Force();
    force.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    LinearDisplacement linearDisplacement = new LinearDisplacement();
    linearDisplacement.setVariables(Arrays.asList(v2));

    LinearMechanicalQuasiPort linearMechanicalQuasiPort = new LinearMechanicalQuasiPort();
    linearMechanicalQuasiPort.setForce(force);
    linearMechanicalQuasiPort.setLinearDisplacement(linearDisplacement);

    modelDescription.getLinearMechanicalQuasiPorts().add(linearMechanicalQuasiPort);

    LinearMechanicalQuasiPortValidator linearMechanicalQuasiPortValidator = new LinearMechanicalQuasiPortValidator();
    List<ValidationDiagnostic<LinearMechanicalQuasiPort>> diagnostics = linearMechanicalQuasiPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
