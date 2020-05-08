package com.opensimulationplatform.core.validation.variablegroup.angularvelocity;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class AngularVelocityValidator extends Validator<AngularVelocity> {
  private final VE_AngularVelocity_1 ve_angularVelocity_1 = new VE_AngularVelocity_1();
  private final VE_AngularVelocity_2 ve_angularVelocity_2 = new VE_AngularVelocity_2();
  private final VE_AngularVelocity_3 ve_angularVelocity_3 = new VE_AngularVelocity_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_angularVelocity_1, ve_angularVelocity_2, ve_angularVelocity_3);
  }
}
