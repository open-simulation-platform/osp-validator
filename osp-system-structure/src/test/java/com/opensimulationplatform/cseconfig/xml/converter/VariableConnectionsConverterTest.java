package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.OspVariableConnection;
import com.opensimulationplatform.cseconfig.xml.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.xml.model.Simulators;
import com.opensimulationplatform.cseconfig.xml.model.VariableConnections;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class VariableConnectionsConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    VariableConnections variableConnections = testSetup.variableConnections;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    Map<Simulators.Simulator, ModelDescription> modelDescriptionMap = new HashMap<>();
    List<Simulators.Simulator> simulator = testSetup.simulators.getSimulator();
    for (Simulators.Simulator s : simulator) {
      modelDescriptionMap.put(s, OspModelDescriptionConverter.convert(testSetup.ospModelDescription));
    }
    List<OspSimulator> ospSimulators = SimulatorsConverter.convert(testSetup.simulators, ospSystemStructure, modelDescriptionMap);
    
    List<OspVariableConnection> ospVariableConnections = VariableConnectionsConverter.convert(variableConnections, ospSimulators);
    
    assertEquals(variableConnections.getScalarConnection().size(), ospVariableConnections.size());
    for (int i = 0; i < ospVariableConnections.size(); i++) {
      VariableConnections.ScalarConnection variableConnection = variableConnections.getScalarConnection().get(i);
      OspVariableConnection ospVariableConnection = ospVariableConnections.get(i);
      
      assertEquals(variableConnection.getSource().getSimulator(), ospVariableConnection.getSourceOspSimulator().getName());
      assertEquals(variableConnection.getSource().getEndpoint(), ospVariableConnection.getSourceOspVariable().getName());
      assertEquals(variableConnection.getTarget().getSimulator(), ospVariableConnection.getTargetOspSimulator().getName());
      assertEquals(variableConnection.getTarget().getEndpoint(), ospVariableConnection.getTargetOspVariable().getName());
    }
  }
}