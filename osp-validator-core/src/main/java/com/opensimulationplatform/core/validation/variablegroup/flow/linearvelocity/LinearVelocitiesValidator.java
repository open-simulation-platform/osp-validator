package com.opensimulationplatform.core.validation.variablegroup.flow.linearvelocity;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.variablegroup.effort.force.VE_LinearVelocity_3;

import java.util.Arrays;
import java.util.List;

public class LinearVelocitiesValidator extends Validator<LinearVelocity> {
  private final VE_LinearVelocity_1 ve_linearVelocity_1 = new VE_LinearVelocity_1();
  private final VE_LinearVelocity_2 ve_linearVelocity_2 = new VE_LinearVelocity_2();
  private final VE_LinearVelocity_3 ve_linearVelocity_3 = new VE_LinearVelocity_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_linearVelocity_1, ve_linearVelocity_2, ve_linearVelocity_3);
  }
}
