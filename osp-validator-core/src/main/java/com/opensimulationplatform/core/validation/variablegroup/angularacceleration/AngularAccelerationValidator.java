package com.opensimulationplatform.core.validation.variablegroup.angularacceleration;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularacceleration.AngularAcceleration;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class AngularAccelerationValidator extends Validator<AngularAcceleration> {
  private final VE_AngularAcceleration_1 ve_angularAcceleration_1 = new VE_AngularAcceleration_1();
  private final VE_AngularAcceleration_2 ve_angularAcceleration_2 = new VE_AngularAcceleration_2();
  private final VE_AngularAcceleration_3 ve_angularAcceleration_3 = new VE_AngularAcceleration_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_angularAcceleration_1, ve_angularAcceleration_2, ve_angularAcceleration_3);
  }
}
