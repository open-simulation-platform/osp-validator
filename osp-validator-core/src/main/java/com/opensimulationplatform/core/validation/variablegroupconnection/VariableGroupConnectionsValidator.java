/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class VariableGroupConnectionsValidator extends Validator<VariableGroupConnection> {
  private final VE_VariableGroupConnection_1 ve_variableGroupConnection_1 = new VE_VariableGroupConnection_1();
  private final VE_VariableGroupConnection_2 ve_variableGroupConnection_2 = new VE_VariableGroupConnection_2();
  private final VE_VariableGroupConnection_3 ve_variableGroupConnection_3 = new VE_VariableGroupConnection_3();
  private final VE_VariableGroupConnection_4 ve_variableGroupConnection_4 = new VE_VariableGroupConnection_4();

  @Override
  protected List<Validator<?>> getValidators() {
    return null;
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(
        ve_variableGroupConnection_1,
        ve_variableGroupConnection_2,
        ve_variableGroupConnection_3,
        ve_variableGroupConnection_4
    );
  }
}
