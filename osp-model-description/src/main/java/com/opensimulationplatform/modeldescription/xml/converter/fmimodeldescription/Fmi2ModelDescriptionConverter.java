/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.fmimodeldescription;


import java.util.ArrayList;
import java.util.List;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.util.FmiModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;

import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2Causality;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2ModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2ScalarVariable;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2Unit;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2Variability;

public class Fmi2ModelDescriptionConverter extends Converter<Fmi2ModelDescription, FmiModelDescription> {

  public Fmi2ModelDescriptionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public FmiModelDescription convert(Fmi2ModelDescription fmiModelDescription) {
    FmiModelDescription fmd = new FmiModelDescription();

    List<Variable> variables = new ArrayList<>();
    List<Fmi2ScalarVariable> scalarVariables = fmiModelDescription.getModelVariables().getScalarVariable();
    for (Fmi2ScalarVariable scalarVariable : scalarVariables) {
      Fmi2Causality causality = scalarVariable.getCausality();
      Fmi2Variability variability = scalarVariable.getVariability();
      if (variability == Fmi2Variability.continuous && (causality==Fmi2Causality.input || causality==Fmi2Causality.output)) {
        variables.add(context.fmi2ScalarVariableConverter.convert(scalarVariable));
      }
    }
    fmd.setVariables(variables);

    Fmi2ModelDescription.UnitDefinitions unitDefinitions = fmiModelDescription.getUnitDefinitions();
    if (unitDefinitions != null) {
      List<Fmi2Unit> fmi2Units = unitDefinitions.getUnit();
      List<Unit> units = context.fmi2UnitConverter.convert(fmi2Units);
      fmd.setUnits(units);
    }

    return fmd;
  }
}