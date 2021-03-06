/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.volume;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VolumeValidator extends Validator<Volume> {
  private final VE_Volume_1 ve_volume_1 = new VE_Volume_1();
  private final VE_Volume_2 ve_volume_2 = new VE_Volume_2();
  private final VE_Volume_3 ve_volume_3 = new VE_Volume_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_volume_1, ve_volume_2, ve_volume_3);
  }
}
