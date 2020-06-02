/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.volumeflowrate;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VolumeFlowRateValidator extends Validator<VolumeFlowRate> {
  private final VE_VolumeFlowRate_1 ve_volumeFlowRate_1 = new VE_VolumeFlowRate_1();
  private final VE_VolumeFlowRate_2 ve_volumeFlowRate_2 = new VE_VolumeFlowRate_2();
  private final VE_VolumeFlowRate_3 ve_volumeFlowRate_3 = new VE_VolumeFlowRate_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_volumeFlowRate_1, ve_volumeFlowRate_2, ve_volumeFlowRate_3);
  }
}
