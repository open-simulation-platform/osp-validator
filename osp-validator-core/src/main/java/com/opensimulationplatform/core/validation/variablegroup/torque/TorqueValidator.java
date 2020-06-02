/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.torque;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class TorqueValidator extends Validator<Torque> {
  private final VE_Torque_1 ve_torque_1 = new VE_Torque_1();
  private final VE_Torque_2 ve_torque_2 = new VE_Torque_2();
  private final VE_Torque_3 ve_torque_3 = new VE_Torque_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_torque_1, ve_torque_2, ve_torque_3);
  }
}
