package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.json.model.parsing.ModelDefinitionJsonFileParser;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelDefinitionFactoryTest {
  @Test
  public void canCreate() {
    ModelDefinition modelDefinition = ModelDefinitionFactory.create(ModelDefinitionJsonFileParser.parse(new File("./src/test/resources/parsing/model-definition.json")));
    
    assertEquals("ModelDefinitionName", modelDefinition.getName());
    
    List<Plug> plugs = modelDefinition.getPlugs();
    assertEquals(5, plugs.size());
    for (int i = 0; i < plugs.size(); i++) {
      Plug plug = plugs.get(i);
      assertEquals("plugType_" + (i + 1), plug.getType());
      assertEquals("plug_" + (i + 1), plug.getName());
      
      Map<String, Variable> variables = plug.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        assertTrue(variables.containsKey("p_variable_" + ((j + 1) + i * variables.size())));
      }
    }
    
    List<Socket> sockets = modelDefinition.getSockets();
    assertEquals(5, sockets.size());
    for (int i = 0; i < sockets.size(); i++) {
      Socket socket = sockets.get(i);
      assertEquals("socketType_" + (i + 1), socket.getType());
      assertEquals("socket_" + (i + 1), socket.getName());
      
      Map<String, Variable> variables = socket.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        assertTrue(variables.containsKey("s_variable_" + ((j + 1) + i * variables.size())));
      }
    }
    
    List<Bond> bonds = modelDefinition.getBonds();
    assertEquals(2, bonds.size());
    for (int i = 0; i < bonds.size(); i++) {
      Bond bond = bonds.get(i);
      assertEquals("bond_" + (i + 1), bond.getName());
      
      List<Plug> bondPlugs = bond.getPlugs();
      assertEquals(2, bondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        String plugName = bondPlugs.get(j).getName();
        assertEquals("plug_" + ((j + 1) + i * bondPlugs.size()), plugName);
      }
      
      List<Socket> bondSockets = bond.getSockets();
      assertEquals(2, bondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        String socketName = bondSockets.get(j).getName();
        assertEquals("socket_" + ((j + 1) + i * bondSockets.size()), socketName);
      }
    }
  }
}
