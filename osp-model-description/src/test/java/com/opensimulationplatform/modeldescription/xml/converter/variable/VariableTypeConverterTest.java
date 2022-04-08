package com.opensimulationplatform.modeldescription.xml.converter.variable;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AxisType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VariableTypeConverterTest {
  @Test
  public void canConvert() {
    ConverterContext converterContext = new ConverterContext();

    Variable v = new Variable();
    v.setName("variableName");
    converterContext.modelDescription.getVariables().add(v);

    Unit u = new Unit();
    u.setName("unitName");
    converterContext.modelDescription.getUnits().add(u);

    VariableType variableType = new VariableType();
    variableType.setRef(v.getName().get());
    variableType.setAxis(AxisType.X);
    variableType.setUnit(u.getName().get());

    VariableTypeConverter variableTypeConverter = new VariableTypeConverter(converterContext);
    Variable variable = variableTypeConverter.convert(variableType);

    assertEquals(v, variable);
    assertEquals(u, variable.getUnit());
    assertEquals(Variable.Axis.X, variable.getAxis());
  }
}