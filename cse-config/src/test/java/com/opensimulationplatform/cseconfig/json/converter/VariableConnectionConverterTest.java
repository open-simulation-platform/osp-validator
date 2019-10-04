package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.OspVariableConnection;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.json.model.VariableConnection;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariableConnectionConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    
    VariableConnection variableConnection = testSetup.variableConnectionA;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    ModelDescription modelDescriptionA = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    ModelDescription modelDescriptionB = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    OspSimulator ospSimulatorA = SimulatorConverter.convert(testSetup.simulatorA, ospSystemStructure, modelDescriptionA);
    OspSimulator ospSimulatorB = SimulatorConverter.convert(testSetup.simulatorB, ospSystemStructure, modelDescriptionB);
  
    List<OspSimulator> simulators = new ArrayList<>();
    simulators.add(ospSimulatorA);
    simulators.add(ospSimulatorB);
    
    OspVariableConnection ospVariableConnection = VariableConnectionConverter.convert(variableConnection, simulators);
    
    assertEquals(variableConnection.getSourceSimulator(), ospVariableConnection.getSourceOspSimulator().getName());
    assertEquals(variableConnection.getSourceVariable(), ospVariableConnection.getSourceOspVariable().getName());
    assertEquals(variableConnection.getTargetSimulator(), ospVariableConnection.getTargetOspSimulator().getName());
    assertEquals(variableConnection.getTargetVariable(), ospVariableConnection.getTargetOspVariable().getName());
  }
}