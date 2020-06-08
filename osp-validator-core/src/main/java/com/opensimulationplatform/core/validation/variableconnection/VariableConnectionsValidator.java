/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VariableConnectionsValidator extends Validator<VariableConnection> {
  private final VE_VariableConnection_1 ve_variableConnection_1 = new VE_VariableConnection_1();
  private final VE_VariableConnection_2 ve_variableConnection_2 = new VE_VariableConnection_2();
  private final VE_VariableConnection_3 ve_variableConnection_3 = new VE_VariableConnection_3();
  private final VE_VariableConnection_4 ve_variableConnection_4 = new VE_VariableConnection_4();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(
        ve_variableConnection_1,
        ve_variableConnection_2,
        ve_variableConnection_3,
        ve_variableConnection_4
    );
  }
}
