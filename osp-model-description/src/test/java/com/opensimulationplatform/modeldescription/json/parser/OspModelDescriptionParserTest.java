package com.opensimulationplatform.modeldescription.json.parser;

import com.opensimulationplatform.modeldescription.TestResources;
import com.opensimulationplatform.modeldescription.json.model.Bond;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import com.opensimulationplatform.modeldescription.json.model.Socket;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OspModelDescriptionParserTest {
  
  @Test
  public void canParse() {
    OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(TestResources.MODEL_DEFINITION_JSON.toFile());
    
    assertEquals("OspModelDescriptionName", ospModelDescription.getName());
    
    List<Plug> plugs = ospModelDescription.getPlugs();
    assertEquals(5, plugs.size());
    for (int i = 0; i < plugs.size(); i++) {
      Plug plug = plugs.get(i);
      assertEquals("plugType_" + (i + 1), plug.getType());
      assertEquals("plug_" + (i + 1), plug.getName());
      
      List<String> variables = plug.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j);
        assertEquals("p_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
    
    List<Socket> sockets = ospModelDescription.getSockets();
    assertEquals(5, sockets.size());
    for (int i = 0; i < sockets.size(); i++) {
      Socket socket = sockets.get(i);
      assertEquals("socketType_" + (i + 1), socket.getType());
      assertEquals("socket_" + (i + 1), socket.getName());
      
      List<String> variables = socket.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j);
        assertEquals("s_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
    
    List<Bond> bonds = ospModelDescription.getBonds();
    assertEquals(2, bonds.size());
    for (int i = 0; i < bonds.size(); i++) {
      Bond bond = bonds.get(i);
      assertEquals("bond_" + (i + 1), bond.getName());
      
      List<String> bondPlugs = bond.getPlugs();
      assertEquals(2, bondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        String plugName = bondPlugs.get(j);
        assertEquals("plug_" + ((j + 1) + i * bondPlugs.size()), plugName);
      }
      
      List<String> bondSockets = bond.getSockets();
      assertEquals(2, bondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        String socketName = bondSockets.get(j);
        assertEquals("socket_" + ((j + 1) + i * bondSockets.size()), socketName);
      }
    }
  }
}