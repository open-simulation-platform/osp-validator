/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.fmimodeldescription;


import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.util.FmiModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

import java.util.ArrayList;
import java.util.List;

public class Fmi1ModelDescriptionConverter extends Converter<no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription, FmiModelDescription> {

  public Fmi1ModelDescriptionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public FmiModelDescription convert(no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription fmiModelDescription) {
    FmiModelDescription fmd = new FmiModelDescription();

    List<Variable> variables = new ArrayList<>();
    List<FmiScalarVariable> scalarVariables = fmiModelDescription.getModelVariables().getScalarVariable();
    for (FmiScalarVariable scalarVariable : scalarVariables) {
      String causality = scalarVariable.getCausality();
      String variability = scalarVariable.getVariability();
      if ("continuous".equals(variability) && ("input".equals(causality) || "output".equals(causality))) {
        variables.add(context.fmi1ScalarVariableConverter.convert(scalarVariable));
      }
    }
    fmd.setVariables(variables);

    return fmd;
  }
}
