package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlugConverterTest {
  @Test
  public void canConvert() {
    Plug plug = new Plug();
    plug.setName("name");
    plug.setType("type");
    plug.setVariables(Arrays.asList("variableA", "variableB"));
  
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setName("name");
    
    OspPlug ospPlug = PlugConverter.convert(plug);
    
    assertEquals(plug.getName(), ospPlug.getName());
    assertEquals(plug.getType(), ospPlug.getType());
    List<String> variables = plug.getVariables();
    Map<String, OspVariable> ospVariables = ospPlug.getVariables();
    assertEquals(variables.size(), ospVariables.size());
    for (String variable : variables) {
      assertEquals(variable, ospVariables.get(variable).getName());
    }
  }
}