/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.voltage;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VoltageValidator extends Validator<Voltage> {
  private final VE_Voltage_1 ve_voltage_1 = new VE_Voltage_1();
  private final VE_Voltage_2 ve_voltage_2 = new VE_Voltage_2();
  private final VE_Voltage_3 ve_voltage_3 = new VE_Voltage_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_voltage_1, ve_voltage_2, ve_voltage_3);
  }
}
