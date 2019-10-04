package com.opensimulationplatform.modeldescription.xml.parser;

import com.opensimulationplatform.modeldescription.TestResources;
import com.opensimulationplatform.modeldescription.xml.model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OspModelDescriptionParserTest {
  @Test
  public void canParse() {
    OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(TestResources.MODEL_DEFINITION_XML.toFile());
  
    List<Plugs.Plug> plugs = ospModelDescription.getPlugs().getPlug();
    assertEquals(3, plugs.size());
    for (int i = 0; i < plugs.size(); i++) {
      Plugs.Plug plug = plugs.get(i);
      assertEquals("plugType_" + (i + 1), plug.getType());
      assertEquals("plug_" + (i + 1), plug.getName());
    
      List<Variables.Variable> variables = plug.getVariables().getVariable();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j).getName();
        assertEquals("p_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
  
    List<Sockets.Socket> sockets = ospModelDescription.getSockets().getSocket();
    assertEquals(3, sockets.size());
    for (int i = 0; i < sockets.size(); i++) {
      Sockets.Socket socket = sockets.get(i);
      assertEquals("socketType_" + (i + 1), socket.getType());
      assertEquals("socket_" + (i + 1), socket.getName());
    
      List<Variables.Variable> variables = socket.getVariables().getVariable();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        String variableName = variables.get(j).getName();
        assertEquals("s_variable_" + ((j + 1) + i * variables.size()), variableName);
      }
    }
  
    List<Bonds.Bond> bonds = ospModelDescription.getBonds().getBond();
    assertEquals(2, bonds.size());
    for (int i = 0; i < bonds.size(); i++) {
      Bonds.Bond bond = bonds.get(i);
      assertEquals("bond_" + (i + 1), bond.getName());
    
      List<Bonds.Bond.BondPlugs.BondPlug> bondPlugs = bond.getBondPlugs().getBondPlug();
      assertEquals(1, bondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        String plugName = bondPlugs.get(j).getName();
        assertEquals("plug_" + ((j + 1) + i * bondPlugs.size()), plugName);
      }
    
      List<Bonds.Bond.BondSockets.BondSocket> bondSockets = bond.getBondSockets().getBondSocket();
      assertEquals(1, bondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        String socketName = bondSockets.get(j).getName();
        assertEquals("socket_" + ((j + 1) + i * bondSockets.size()), socketName);
      }
    }
  }
}