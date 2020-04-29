package com.opensimulationplatform.core.validation.variablegroup.force;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class ForceValidator extends Validator<Force> {
  private final VE_Force_1 ve_force_1 = new VE_Force_1();
  private final VE_Force_2 ve_force_2 = new VE_Force_2();
  private final VE_Force_3 ve_force_3 = new VE_Force_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_force_1, ve_force_2, ve_force_3);
  }
}
