package com.opensimulationplatform.modeldescription.json.parser;

import com.opensimulationplatform.modeldescription.json.model.JsonOspBond;
import com.opensimulationplatform.modeldescription.json.model.JsonOspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.JsonOspPlug;
import com.opensimulationplatform.modeldescription.json.model.JsonOspSocket;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonOspModelDescriptionParserTest {
  
  @Test
  public void canParse() {
    JsonOspModelDescription modelDefinition = JsonOspModelDescriptionParser.parse(new File("./src/test/resources/parsing/model-definition.json"));
    
    assertEquals("ModelDefinitionName", modelDefinition.getName());
    
    List<JsonOspPlug> plugs = modelDefinition.getPlugs();
    assertEquals(5, plugs.size());
    for (int i = 0; i < plugs.size(); i++) {
      JsonOspPlug plug = plugs.get(i);
      assertEquals("plugType_" + (i + 1), plug.getType());
      assertEquals("plug_" + (i + 1), plug.getName());
      
      List<String> variables = plug.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j);
        assertEquals("p_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
    
    List<JsonOspSocket> sockets = modelDefinition.getSockets();
    assertEquals(5, sockets.size());
    for (int i = 0; i < sockets.size(); i++) {
      JsonOspSocket socket = sockets.get(i);
      assertEquals("socketType_" + (i + 1), socket.getType());
      assertEquals("socket_" + (i + 1), socket.getName());
      
      List<String> variables = socket.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j);
        assertEquals("s_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
    
    List<JsonOspBond> bonds = modelDefinition.getBonds();
    assertEquals(2, bonds.size());
    for (int i = 0; i < bonds.size(); i++) {
      JsonOspBond bond = bonds.get(i);
      assertEquals("bond_" + (i + 1), bond.getName());
      
      List<String> bondPlugs = bond.getPlugs();
      assertEquals(2, bondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        String variableName = bondPlugs.get(j);
        assertEquals("plug_" + ((j + 1) + i * bondPlugs.size()), variableName);
      }
      
      List<String> bondSockets = bond.getSockets();
      assertEquals(2, bondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        String variableName = bondSockets.get(j);
        assertEquals("socket_" + ((j + 1) + i * bondSockets.size()), variableName);
      }
    }
  }
}