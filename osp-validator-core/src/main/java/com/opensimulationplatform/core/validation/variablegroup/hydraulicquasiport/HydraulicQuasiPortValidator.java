package com.opensimulationplatform.core.validation.variablegroup.hydraulicquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class HydraulicQuasiPortValidator extends Validator<HydraulicQuasiPort> {
  private final VE_HydraulicQuasiPort_1 ve_hydraulicQuasiPort_1 = new VE_HydraulicQuasiPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_hydraulicQuasiPort_1);
  }
}
