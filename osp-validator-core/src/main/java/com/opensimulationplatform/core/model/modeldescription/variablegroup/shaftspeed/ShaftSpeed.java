/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;

import java.util.List;

public class ShaftSpeed extends AngularVelocity {
  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 1) {
      throw new RuntimeException(this.getClass().getSimpleName() + " variable group must contain exactly " + 1 + " variable");
    } else {
      this.variables = variables;
    }
  }
}
