/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.variable;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

public class VariableTypeConverter extends Converter<VariableType, Variable> {

  public VariableTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Variable convert(VariableType variableType) {
    Variable variable = ModelDescriptionUtil.getVariableByName(context.modelDescription, variableType.getRef());

    Unit unit = ModelDescriptionUtil.getUnitByName(context.modelDescription, variableType.getUnit());
    if (unit != null && variable != null) {
      variable.setUnit(unit);
    }

    if (variable != null) {
      if (variableType.getAxis() == null) {
        variable.setAxis(Variable.Axis.UNDEFINED);
      } else {
        switch (variableType.getAxis()) {
          case X:
            variable.setAxis(Variable.Axis.X);
            break;
          case Y:
            variable.setAxis(Variable.Axis.Y);
            break;
          case Z:
            variable.setAxis(Variable.Axis.Z);
            break;
          default:
            variable.setAxis(Variable.Axis.UNDEFINED);
            break;
        }
      }

    }

    return variable;
  }
}
