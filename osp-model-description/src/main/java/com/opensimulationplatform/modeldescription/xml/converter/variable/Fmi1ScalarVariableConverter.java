/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.variable;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiCausality;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

import static com.opensimulationplatform.core.model.modeldescription.Variable.Causality.INPUT;
import static com.opensimulationplatform.core.model.modeldescription.Variable.Causality.OUTPUT;
import static com.opensimulationplatform.core.model.modeldescription.Variable.Type.*;

public class Fmi1ScalarVariableConverter extends Converter<FmiScalarVariable, Variable> {

  public Fmi1ScalarVariableConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Variable convert(FmiScalarVariable fmiScalarVariable) {
    Variable variable = new Variable();

    variable.setName(fmiScalarVariable.getName());

    FmiCausality fmiCausality = fmiScalarVariable.getCausality();
    if (fmiCausality == FmiCausality.input) {
      variable.setCausality(INPUT);
    } else if (fmiCausality == FmiCausality.output) {
      variable.setCausality(OUTPUT);
    } else {
      variable.setCausality(Variable.Causality.UNDEFINED);
    }

    FmiScalarVariable.Real real = fmiScalarVariable.getReal();
    FmiScalarVariable.Integer integer = fmiScalarVariable.getInteger();
    FmiScalarVariable.String string = fmiScalarVariable.getString();
    FmiScalarVariable.Boolean bool = fmiScalarVariable.getBoolean();
    FmiScalarVariable.Enumeration enumeration = fmiScalarVariable.getEnumeration();
    if (real != null) {
      variable.setType(REAL);
    } else if (integer != null) {
      variable.setType(INTEGER);
    } else if (string != null) {
      variable.setType(STRING);
    } else if (bool != null) {
      variable.setType(BOOLEAN);
    } else if (enumeration != null) {
      variable.setType(ENUM);
    } else {
      variable.setType(Variable.Type.UNDEFINED);
    }

    return variable;
  }
}
