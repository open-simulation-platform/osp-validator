/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.charge;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class ChargeValidator extends Validator<Charge> {
  private final VE_Charge_1 ve_charge_1 = new VE_Charge_1();
  private final VE_Charge_2 ve_charge_2 = new VE_Charge_2();
  private final VE_Charge_3 ve_charge_3 = new VE_Charge_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_charge_1, ve_charge_2, ve_charge_3);
  }
}
