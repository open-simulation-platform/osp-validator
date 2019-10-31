package com.opensimulationplatform.core.validation.simulator;

import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;

import java.util.Arrays;
import java.util.List;

public class SimulatorsValidator extends Validator<Object> {
  private final ModelDescriptionValidator modelDescriptionValidator = new ModelDescriptionValidator();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(modelDescriptionValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return null;
  }
}
