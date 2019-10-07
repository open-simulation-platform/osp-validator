package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.xml.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.xml.model.Simulators;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulatorsConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
  
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
  
    Simulators.Simulator simulatorA = testSetup.simulators.getSimulator().get(0);
    Simulators.Simulator simulatorB = testSetup.simulators.getSimulator().get(1);
  
    ModelDescription modelDescriptionA = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    ModelDescription modelDescriptionB = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
  
    Map<Simulators.Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    modelDescriptions.put(simulatorA, modelDescriptionA);
    modelDescriptions.put(simulatorB, modelDescriptionB);
  
    List<OspSimulator> ospSimulators = SimulatorsConverter.convert(testSetup.simulators, ospSystemStructure, modelDescriptions);
  
    for (int i = 0; i < ospSimulators.size(); i++) {
      OspSimulator ospSimulator = ospSimulators.get(i);
      Simulators.Simulator simulator = testSetup.simulators.getSimulator().get(i);
  
      assertEquals(simulator.getName(), ospSimulator.getName());
    
      assertTrue(ospSimulator.getPlugs().containsKey(testSetup.plugA.getName()));
      assertTrue(ospSimulator.getPlugs().containsKey(testSetup.plugB.getName()));
      assertTrue(ospSimulator.getPlugs().containsKey(testSetup.plugC.getName()));
      assertTrue(ospSimulator.getPlugs().containsKey(testSetup.plugD.getName()));
    
      assertTrue(ospSimulator.getSockets().containsKey(testSetup.socketA.getName()));
      assertTrue(ospSimulator.getSockets().containsKey(testSetup.socketB.getName()));
      assertTrue(ospSimulator.getSockets().containsKey(testSetup.socketC.getName()));
      assertTrue(ospSimulator.getSockets().containsKey(testSetup.socketD.getName()));
    
      assertTrue(ospSimulator.getBonds().containsKey(testSetup.bondA.getName()));
      assertTrue(ospSimulator.getBonds().containsKey(testSetup.bondB.getName()));
    
      assertTrue("variable1", ospSimulator.getVariables().containsKey("variable1"));
      assertTrue("variable2", ospSimulator.getVariables().containsKey("variable2"));
      assertTrue("variable3", ospSimulator.getVariables().containsKey("variable3"));
      assertTrue("variable4", ospSimulator.getVariables().containsKey("variable4"));
      assertTrue("variable5", ospSimulator.getVariables().containsKey("variable5"));
      assertTrue("variable6", ospSimulator.getVariables().containsKey("variable6"));
      assertTrue("variable7", ospSimulator.getVariables().containsKey("variable7"));
      assertTrue("variable8", ospSimulator.getVariables().containsKey("variable8"));
      assertTrue("variable9", ospSimulator.getVariables().containsKey("variable9"));
      assertTrue("variable10", ospSimulator.getVariables().containsKey("variable10"));
      assertTrue("variable11", ospSimulator.getVariables().containsKey("variable11"));
      assertTrue("variable12", ospSimulator.getVariables().containsKey("variable12"));
      assertTrue("variable13", ospSimulator.getVariables().containsKey("variable13"));
      assertTrue("variable14", ospSimulator.getVariables().containsKey("variable14"));
      assertTrue("variable15", ospSimulator.getVariables().containsKey("variable15"));
      assertTrue("variable16", ospSimulator.getVariables().containsKey("variable16"));
      assertTrue("variable17", ospSimulator.getVariables().containsKey("variable17"));
      assertTrue("variable18", ospSimulator.getVariables().containsKey("variable18"));
    }
  }
}