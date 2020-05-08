package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class HydraulicPortValidator extends Validator<HydraulicPort> {
  private final VE_HydraulicPort_1 ve_hydraulicPort_1 = new VE_HydraulicPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_hydraulicPort_1);
  }
}
