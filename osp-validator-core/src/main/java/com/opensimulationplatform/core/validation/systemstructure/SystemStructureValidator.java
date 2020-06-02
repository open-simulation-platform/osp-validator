/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.systemstructure;

import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.simulator.SimulatorsValidator;
import com.opensimulationplatform.core.validation.variableconnection.VariableConnectionsValidator;
import com.opensimulationplatform.core.validation.variablegroupconnection.VariableGroupConnectionsValidator;

import java.util.Arrays;
import java.util.List;

public class SystemStructureValidator extends Validator<Object> {
  private final SimulatorsValidator simulatorsValidator = new SimulatorsValidator();
  private final VariableConnectionsValidator variableConnectionsValidator = new VariableConnectionsValidator();
  private final VariableGroupConnectionsValidator variableGroupConnectionsValidator = new VariableGroupConnectionsValidator();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(simulatorsValidator, variableConnectionsValidator, variableGroupConnectionsValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return null;
  }
}
