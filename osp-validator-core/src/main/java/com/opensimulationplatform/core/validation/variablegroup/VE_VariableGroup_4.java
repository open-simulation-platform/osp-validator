/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class VE_VariableGroup_4 extends ValidationError<VariableGroup> {
  @Override
  protected List<VariableGroup> getInvalidObjects() {
    List<VariableGroup> invalidObjects = new ArrayList<>();

    for (VariableGroup vg : context.variableGroups.values()) {
      List<Variable.Axis> axes = vg.getVariables().stream()
          .map(Variable::getAxis)
          .filter(axis -> axis != Variable.Axis.UNDEFINED)
          .collect(Collectors.toList());

      if (axes.size() != axes.stream().distinct().count()) {
        invalidObjects.add(vg);
      }
    }

    return invalidObjects;
  }

  @Override
  protected String getErrorMessage(VariableGroup variableGroup) {
    return "VariableGroup '" + variableGroup.getName().getId().toString() + "' contains variables with identical axis properties";
  }
}
