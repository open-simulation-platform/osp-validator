/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.lineardisplacement;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class LinearDisplacementValidator extends Validator<LinearDisplacement> {
  private final VE_LinearDisplacement_1 ve_linearDisplacement_1 = new VE_LinearDisplacement_1();
  private final VE_LinearDisplacement_2 ve_linearDisplacement_2 = new VE_LinearDisplacement_2();
  private final VE_LinearDisplacement_3 ve_linearDisplacement_3 = new VE_LinearDisplacement_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_linearDisplacement_1, ve_linearDisplacement_2, ve_linearDisplacement_3);
  }
}
