package com.opensimulationplatform.api.examples;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;

import java.util.Arrays;
import java.util.List;

public class ValidateModelDescriptionDataModel {
  public static void main(String[] args) {
    ModelDescription modelDescription = new ModelDescription();

    Unit unit = new Unit();
    unit.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setType(Variable.Type.REAL);
    v1.setName("v1");
    v1.setUnit(unit);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setType(Variable.Type.REAL);
    v2.setName("v2");
    v2.setUnit(unit);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.INPUT);
    v3.setType(Variable.Type.REAL);
    v3.setName("v3");
    v3.setUnit(unit);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.INPUT);
    v4.setType(Variable.Type.REAL);
    v4.setName("v4");
    v4.setUnit(unit);

    Force force = new Force();
    force.setName("force");
    force.setVariables(Arrays.asList(v1, v2));

    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setName("linearVelocity");
    linearVelocity.setVariables(Arrays.asList(v3, v4));

    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();
    linearMechanicalPort.setName("linearMechanicalPort");
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    modelDescription.getUnits().add(unit);
    modelDescription.getVariables().addAll(Arrays.asList(v1, v2, v3, v4));
    modelDescription.getForces().add(force);
    modelDescription.getLinearVelocities().add(linearVelocity);
    modelDescription.getLinearMechanicalPorts().add(linearMechanicalPort);


    ModelDescriptionValidator validator = new ModelDescriptionValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(modelDescription);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }
}
