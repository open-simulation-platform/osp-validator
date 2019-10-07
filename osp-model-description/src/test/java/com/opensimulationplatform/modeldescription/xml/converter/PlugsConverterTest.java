package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.model.Plugs;
import com.opensimulationplatform.modeldescription.xml.model.Variables;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlugsConverterTest {
  @Test
  public void canConvert() {
    OspModelDescription ospModelDescription = TestSetup.getTestOspModelDescription();
    Plugs plugs = ospModelDescription.getPlugs();
    
    List<OspPlug> ospPlugs = PlugsConverter.convert(plugs);
  
    for (int i = 0; i < ospPlugs.size(); i++) {
      Plugs.Plug plug = plugs.getPlug().get(i);
      OspPlug ospPlug = ospPlugs.get(i);
      
      assertEquals(plug.getName(), ospPlug.getName());
      assertEquals(plug.getType(), ospPlug.getType());
      List<Variables.Variable> variables = plug.getVariables().getVariable();
      Map<String, OspVariable> ospVariables = ospPlug.getVariables();
      assertEquals(variables.size(), ospVariables.size());
      for (Variables.Variable variable : variables) {
        assertEquals(variable.getName(), ospVariables.get(variable.getName()).getName());
      }
    }
  }
}