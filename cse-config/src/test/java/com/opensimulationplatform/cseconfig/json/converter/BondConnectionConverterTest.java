package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.json.model.BondConnection;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BondConnectionConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    BondConnection bondConnection = testSetup.bondConnection;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    ModelDescription modelDescriptionA = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    ModelDescription modelDescriptionB = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    OspSimulator ospSimulatorA = SimulatorConverter.convert(testSetup.simulatorA, ospSystemStructure, modelDescriptionA);
    OspSimulator ospSimulatorB = SimulatorConverter.convert(testSetup.simulatorB, ospSystemStructure, modelDescriptionB);
  
    List<OspSimulator> simulators = new ArrayList<>();
    simulators.add(ospSimulatorA);
    simulators.add(ospSimulatorB);
    
    OspBondConnection ospBondConnection = BondConnectionConverter.convert(bondConnection, simulators);
    
    assertEquals(bondConnection.getSimulatorA(), ospBondConnection.getOspSimulatorA().getName());
    assertEquals(bondConnection.getBondA(), ospBondConnection.getOspBondA().getName());
    assertEquals(bondConnection.getSimulatorB(), ospBondConnection.getOspSimulatorB().getName());
    assertEquals(bondConnection.getBondB(), ospBondConnection.getOspBondB().getName());
  }
}