package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class AngularMechanicalQuasiPortValidator extends Validator<AngularMechanicalQuasiPort> {
  private final VE_AngularMechanicalQuasiPort_1 ve_angularMechanicalQuasiPort_1 = new VE_AngularMechanicalQuasiPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_angularMechanicalQuasiPort_1);
  }
}
