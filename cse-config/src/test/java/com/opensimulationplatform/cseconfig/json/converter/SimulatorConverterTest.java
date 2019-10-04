package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.json.model.Simulator;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulatorConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    
    Simulator simulator = testSetup.simulatorA;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    ModelDescription modelDescription = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    
    OspSimulator ospSimulator = SimulatorConverter.convert(simulator, ospSystemStructure, modelDescription);
    
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
    assertTrue("variable19", ospSimulator.getVariables().containsKey("variable19"));
  }
}