package com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class ElectromagneticQuasiPortValidator extends Validator<ElectromagneticQuasiPort> {
  private final VE_ElectromagneticQuasiPort_1 ve_electromagneticQuasiPort_1 = new VE_ElectromagneticQuasiPort_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_electromagneticQuasiPort_1);
  }
}
