package com.opensimulationplatform.core.validation.variablegroup.port.linearmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class LinearMechanicalPortsValidator extends Validator<LinearMechanicalPort> {
  private final VE_LinearMechanicalPort_1 ve_linearMechanicalPort_1 = new VE_LinearMechanicalPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_linearMechanicalPort_1);
  }
}
