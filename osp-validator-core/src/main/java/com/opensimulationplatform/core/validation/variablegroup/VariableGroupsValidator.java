package com.opensimulationplatform.core.validation.variablegroup;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.variablegroup.effort.force.ForcesValidator;
import com.opensimulationplatform.core.validation.variablegroup.flow.linearvelocity.LinearVelocitiesValidator;
import com.opensimulationplatform.core.validation.variablegroup.port.linearmechanicalport.LinearMechanicalPortsValidator;

import java.util.Arrays;
import java.util.List;

public class VariableGroupsValidator extends Validator<VariableGroup> {

  private final ForcesValidator forcesValidator = new ForcesValidator();
  private final LinearVelocitiesValidator linearVelocitiesValidator = new LinearVelocitiesValidator();
  private final LinearMechanicalPortsValidator linearMechanicalPortsValidator = new LinearMechanicalPortsValidator();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(forcesValidator, linearVelocitiesValidator, linearMechanicalPortsValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return null;
  }
}
