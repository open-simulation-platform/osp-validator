package com.opensimulationplatform.core.validation.variablegroup.nmeaggafix;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class NmeaGgaFixValidator extends Validator<NmeaGgaFix> {
  private final VE_NmeaGgaFix_1 ve_nmeaGgaFix_1 = new VE_NmeaGgaFix_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_nmeaGgaFix_1);
  }
}
