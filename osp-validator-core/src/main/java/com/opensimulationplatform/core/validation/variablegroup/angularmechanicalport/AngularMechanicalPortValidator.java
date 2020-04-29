package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class AngularMechanicalPortValidator extends Validator<AngularMechanicalPort> {
  private final VE_AngularMechanicalPort_1 ve_angularMechanicalPort_1 = new VE_AngularMechanicalPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_angularMechanicalPort_1);
  }
}
