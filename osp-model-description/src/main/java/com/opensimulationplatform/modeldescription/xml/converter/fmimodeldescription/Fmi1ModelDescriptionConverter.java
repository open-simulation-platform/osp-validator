package com.opensimulationplatform.modeldescription.xml.converter.fmimodeldescription;


import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.util.FmiModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

import java.util.List;
import java.util.stream.Collectors;

public class Fmi1ModelDescriptionConverter extends Converter<no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription, FmiModelDescription> {

  public Fmi1ModelDescriptionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public FmiModelDescription convert(no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription fmiModelDescription) {
    FmiModelDescription fmd = new FmiModelDescription();

    List<FmiScalarVariable> scalarVariables = fmiModelDescription.getModelVariables().getScalarVariable();
    List<Variable> variables = converterContext.fmi1ScalarVariableConverter.convert(scalarVariables);
    fmd.setVariables(variables);

    return fmd;
  }

  @Override
  public List<FmiModelDescription> convert(List<no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription> fmiModelDescriptions) {
    return fmiModelDescriptions.stream().map(this::convert).collect(Collectors.toList());
  }
}
