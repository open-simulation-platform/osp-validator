package com.opensimulationplatform.core.validation.variablegroup.angulardisplacement;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class AngularDisplacementValidator extends Validator<AngularDisplacement> {
  private final VE_AngularDisplacement_1 ve_angularDisplacement_1 = new VE_AngularDisplacement_1();
  private final VE_AngularDisplacement_2 ve_angularDisplacement_2 = new VE_AngularDisplacement_2();
  private final VE_AngularDisplacement_3 ve_angularDisplacement_3 = new VE_AngularDisplacement_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_angularDisplacement_1, ve_angularDisplacement_2, ve_angularDisplacement_3);
  }
}
