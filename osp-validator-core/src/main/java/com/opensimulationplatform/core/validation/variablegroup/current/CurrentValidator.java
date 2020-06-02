/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.current;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class CurrentValidator extends Validator<Current> {
  private final VE_Current_1 ve_current_1 = new VE_Current_1();
  private final VE_Current_2 ve_current_2 = new VE_Current_2();
  private final VE_Current_3 ve_current_3 = new VE_Current_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_current_1, ve_current_2, ve_current_3);
  }
}
