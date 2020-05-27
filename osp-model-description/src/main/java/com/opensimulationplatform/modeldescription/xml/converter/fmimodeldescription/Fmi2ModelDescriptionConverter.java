package com.opensimulationplatform.modeldescription.xml.converter.fmimodeldescription;


import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.util.FmiModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2ScalarVariable;
import no.ntnu.ihb.fmi4j.modeldescription.fmi2.Fmi2Unit;

import java.util.ArrayList;
import java.util.List;

public class Fmi2ModelDescriptionConverter extends Converter<no.ntnu.ihb.fmi4j.modeldescription.fmi2.FmiModelDescription, FmiModelDescription> {

  public Fmi2ModelDescriptionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public FmiModelDescription convert(no.ntnu.ihb.fmi4j.modeldescription.fmi2.FmiModelDescription fmiModelDescription) {
    FmiModelDescription fmd = new FmiModelDescription();

    List<Variable> variables = new ArrayList<>();
    List<Fmi2ScalarVariable> scalarVariables = fmiModelDescription.getModelVariables().getScalarVariable();
    for (Fmi2ScalarVariable scalarVariable : scalarVariables) {
      String causality = scalarVariable.getCausality();
      String variability = scalarVariable.getVariability();
      if ("continuous".equals(variability) && ("input".equals(causality) || "output".equals(causality))) {
        variables.add(context.fmi2ScalarVariableConverter.convert(scalarVariable));
      }
    }
    fmd.setVariables(variables);

    no.ntnu.ihb.fmi4j.modeldescription.fmi2.FmiModelDescription.UnitDefinitions unitDefinitions = fmiModelDescription.getUnitDefinitions();
    if (unitDefinitions != null) {
      List<Fmi2Unit> fmi2Units = unitDefinitions.getUnit();
      List<Unit> units = context.fmi2UnitConverter.convert(fmi2Units);
      fmd.setUnits(units);
    }

    return fmd;
  }
}