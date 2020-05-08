package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class LinearMechanicalQuasiPortValidator extends Validator<LinearMechanicalQuasiPort> {
  private final VE_LinearMechanicalQuasiPort_1 ve_linearMechanicalQuasiPort_1 = new VE_LinearMechanicalQuasiPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_linearMechanicalQuasiPort_1);
  }
}
