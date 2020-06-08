/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.simulator;

import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;

import java.util.Arrays;
import java.util.List;

public class SimulatorsValidator extends Validator<Object> {
  private final ModelDescriptionValidator modelDescriptionValidator = new ModelDescriptionValidator();

  private final VE_Simulator_1 ve_simulator_1 = new VE_Simulator_1();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(modelDescriptionValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_simulator_1);
  }
}
