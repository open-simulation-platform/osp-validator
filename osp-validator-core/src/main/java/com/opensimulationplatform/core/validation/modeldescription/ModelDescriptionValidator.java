/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.modeldescription;

import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.name.NamesValidator;
import com.opensimulationplatform.core.validation.variable.VariablesValidator;
import com.opensimulationplatform.core.validation.variablegroup.VariableGroupsValidator;

import java.util.Arrays;
import java.util.List;

public class ModelDescriptionValidator extends Validator<Object> {

  private final NamesValidator namesValidator = new NamesValidator();
  private final VariablesValidator variablesValidator = new VariablesValidator();
  private final VariableGroupsValidator variableGroupsValidator = new VariableGroupsValidator();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(namesValidator, variablesValidator, variableGroupsValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return null;
  }
}
