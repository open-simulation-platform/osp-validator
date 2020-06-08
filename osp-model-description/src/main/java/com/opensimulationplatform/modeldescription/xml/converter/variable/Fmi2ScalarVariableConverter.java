/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.variable;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2ScalarVariable;

import static com.opensimulationplatform.core.model.modeldescription.Variable.Causality.INPUT;
import static com.opensimulationplatform.core.model.modeldescription.Variable.Causality.OUTPUT;
import static com.opensimulationplatform.core.model.modeldescription.Variable.Type.*;

public class Fmi2ScalarVariableConverter extends Converter<Fmi2ScalarVariable, Variable> {

  public Fmi2ScalarVariableConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Variable convert(Fmi2ScalarVariable fmi2ScalarVariable) {
    Variable variable = new Variable();

    variable.setName(fmi2ScalarVariable.getName());

    String fmiCausality = fmi2ScalarVariable.getCausality();
    if (fmiCausality.equals("input")) {
      variable.setCausality(INPUT);
    } else if (fmiCausality.equals("output")) {
      variable.setCausality(OUTPUT);
    } else {
      variable.setCausality(Variable.Causality.UNDEFINED);
    }

    Fmi2ScalarVariable.Real real = fmi2ScalarVariable.getReal();
    Fmi2ScalarVariable.Integer integer = fmi2ScalarVariable.getInteger();
    Fmi2ScalarVariable.String string = fmi2ScalarVariable.getString();
    Fmi2ScalarVariable.Boolean bool = fmi2ScalarVariable.getBoolean();
    Fmi2ScalarVariable.Enumeration enumeration = fmi2ScalarVariable.getEnumeration();
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
