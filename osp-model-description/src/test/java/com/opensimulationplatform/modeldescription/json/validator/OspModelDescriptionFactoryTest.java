package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.*;
import com.opensimulationplatform.modeldescription.json.TestSetup;
import com.opensimulationplatform.modeldescription.json.model.JsonOspModelDescription;
import com.opensimulationplatform.modeldescription.json.parser.JsonOspModelDescriptionParser;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspModelDescriptionFactoryTest {
  @Test
  public void canCreateWithoutFmiModelDescription() {
    OspModelDescription ospModelDescription = OspModelDescriptionFactory.create(JsonOspModelDescriptionParser.parse(new File("./src/test/resources/parsing/model-definition.json")));
    
    assertEquals("ModelDefinitionName", ospModelDescription.getName());
    assertPlugs(ospModelDescription);
    assertSockets(ospModelDescription);
    assertBonds(ospModelDescription);
  }

  @Test
  public void canCreateWithFmiModelDescription() {
    JsonOspModelDescription jsonOspModelDescription = JsonOspModelDescriptionParser.parse(new File("./src/test/resources/parsing/model-definition.json"));
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    OspModelDescription ospModelDescription = OspModelDescriptionFactory.create(jsonOspModelDescription, fmiModelDescription);
    
    assertEquals("ModelDefinitionName", ospModelDescription.getName());
    assertEquals(fmiModelDescription, ospModelDescription.getFmiModelDescription());
    assertPlugs(ospModelDescription);
    assertSockets(ospModelDescription);
    assertBonds(ospModelDescription);
  }
  
  private void assertBonds(OspModelDescription ospModelDescription) {
    List<OspBond> ospBonds = ospModelDescription.getOspBonds();
    assertEquals(2, ospBonds.size());
    for (int i = 0; i < ospBonds.size(); i++) {
      OspBond ospBond = ospBonds.get(i);
      assertEquals("bond_" + (i + 1), ospBond.getName());
      
      List<OspPlug> bondOspPlugs = ospBond.getOspPlugs();
      assertEquals(2, bondOspPlugs.size());
      for (int j = 0; j < bondOspPlugs.size(); j++) {
        String plugName = bondOspPlugs.get(j).getName();
        assertEquals("plug_" + ((j + 1) + i * bondOspPlugs.size()), plugName);
      }
      
      List<OspSocket> bondOspSockets = ospBond.getOspSockets();
      assertEquals(2, bondOspSockets.size());
      for (int j = 0; j < bondOspSockets.size(); j++) {
        String socketName = bondOspSockets.get(j).getName();
        assertEquals("socket_" + ((j + 1) + i * bondOspSockets.size()), socketName);
      }
    }
  }
  
  private void assertSockets(OspModelDescription ospModelDescription) {
    List<OspSocket> ospSockets = ospModelDescription.getOspSockets();
    assertEquals(5, ospSockets.size());
    for (int i = 0; i < ospSockets.size(); i++) {
      OspSocket ospSocket = ospSockets.get(i);
      assertEquals("socketType_" + (i + 1), ospSocket.getType());
      assertEquals("socket_" + (i + 1), ospSocket.getName());
      
      Map<String, OspVariable> variables = ospSocket.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        assertTrue(variables.containsKey("s_variable_" + ((j + 1) + i * variables.size())));
      }
    }
  }
  
  private void assertPlugs(OspModelDescription ospModelDescription) {
    List<OspPlug> ospPlugs = ospModelDescription.getOspPlugs();
    assertEquals(5, ospPlugs.size());
    for (int i = 0; i < ospPlugs.size(); i++) {
      OspPlug ospPlug = ospPlugs.get(i);
      assertEquals("plugType_" + (i + 1), ospPlug.getType());
      assertEquals("plug_" + (i + 1), ospPlug.getName());
      
      Map<String, OspVariable> variables = ospPlug.getVariables();
      assertEquals(3, variables.size());
      for (int j = 0; j < variables.size(); j++) {
        assertTrue(variables.containsKey("p_variable_" + ((j + 1) + i * variables.size())));
      }
    }
  }
}
