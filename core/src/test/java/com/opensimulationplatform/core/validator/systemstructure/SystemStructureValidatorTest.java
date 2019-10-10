package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SystemStructureValidatorTest {
  
  @Test
  public void validConfigurationIsProperlyValidated() {
    SystemStructureValidator.Result result = SystemStructureValidator.validate(TestSetup.getValidSystemStructure());
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationIsProperlyValidated() {
    SystemStructureValidator.Result result = SystemStructureValidator.validate(TestSetup.getInvalidSystemStructure());
    
    assertFalse(result.isSuccess());
    
    SystemStructureValidator.Diagnostic diagnostic = result.getDiagnostics();
    
    assertEquals(
        "position SubClassOf has_signal_connector_mate only position\n" +
        "has_plug_mate SubPropertyOf has_signal_connector_mate\n" +
        "simulator_simulatorB_position_socket_socketA Type position\n" +
        "simulator_simulatorA_force_plug_plugA Type force\n" +
        "force DisjointWith position\n" +
        "simulator_simulatorB_position_socket_socketA has_plug_mate simulator_simulatorA_force_plug_plugA\n", diagnostic.getMessage());
    
    Set<OspSimulator> invalidSimulators = diagnostic.getInvalidSimulators();
    Set<OspVariable> invalidVariables = diagnostic.getInvalidVariables();
    Set<OspPlug> invalidPlugs = diagnostic.getInvalidPlugs();
    Set<OspSocket> invalidSockets = diagnostic.getInvalidSockets();
    Set<OspBond> invalidBonds = diagnostic.getInvalidBonds();
    
    assertEquals(0, invalidSimulators.size());
    assertEquals(0, invalidVariables.size());
    assertEquals(1, invalidPlugs.size());
    assertEquals(1, invalidSockets.size());
    assertEquals(0, invalidBonds.size());
    
    assertTrue(invalidPlugs.stream().anyMatch(p -> "plugA".equals(p.getName())));
    assertTrue(invalidSockets.stream().anyMatch(s -> "socketA".equals(s.getName())));
  }
}