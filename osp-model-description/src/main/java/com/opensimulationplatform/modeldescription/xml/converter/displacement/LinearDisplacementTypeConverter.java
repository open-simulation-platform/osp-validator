/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearDisplacementType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearDisplacementTypeConverter extends Converter<LinearDisplacementType, LinearDisplacement> {

  public LinearDisplacementTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearDisplacement convert(LinearDisplacementType linearDisplacementType) {
    LinearDisplacement linearDisplacement = new LinearDisplacement();

    linearDisplacement.setName(linearDisplacementType.getName());

    List<VariableType> variableTypes = linearDisplacementType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    linearDisplacement.setVariables(variables);

    return linearDisplacement;
  }
}
