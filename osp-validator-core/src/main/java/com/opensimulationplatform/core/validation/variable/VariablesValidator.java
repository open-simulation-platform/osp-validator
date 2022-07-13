/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variable;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VariablesValidator extends Validator<Variable> {
  private final VE_Variable_1 ve_variable_1 = new VE_Variable_1();
  private final VE_Variable_2 ve_variable_2 = new VE_Variable_2();

  private final VE_Variable_3 ve_variable_3 = new VE_Variable_3();

  private final VE_Variable_4 ve_variable_4 = new VE_Variable_4();

  private final VE_Variable_5 ve_variable_5 = new VE_Variable_5();

  private final VE_Variable_6 ve_variable_6 = new VE_Variable_6();


  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(ve_variable_1, ve_variable_2, ve_variable_3, ve_variable_4, ve_variable_5, ve_variable_6);
  }
}
