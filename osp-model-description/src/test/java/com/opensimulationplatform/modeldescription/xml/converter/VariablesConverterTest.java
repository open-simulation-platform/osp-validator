package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.model.Variables;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariablesConverterTest {
  @Test
  public void canConvert() {
    OspModelDescription ospModelDescription = TestSetup.getTestOspModelDescription();
    Variables variables = ospModelDescription.getPlugs().getPlug().get(0).getVariables();
  
    List<OspVariable> ospVariables = VariablesConverter.convert(variables);
  
    assertEquals(variables.getVariable().size(), ospVariables.size());
    for (int i = 0; i < ospVariables.size(); i++) {
      Variables.Variable variable = variables.getVariable().get(i);
      OspVariable ospVariable = ospVariables.get(i);
      assertEquals(variable.getName(), ospVariable.getName());
    }
  }
}