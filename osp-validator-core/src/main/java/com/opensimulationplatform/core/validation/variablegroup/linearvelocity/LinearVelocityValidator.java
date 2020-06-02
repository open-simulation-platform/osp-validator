/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.linearvelocity;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class LinearVelocityValidator extends Validator<LinearVelocity> {
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
