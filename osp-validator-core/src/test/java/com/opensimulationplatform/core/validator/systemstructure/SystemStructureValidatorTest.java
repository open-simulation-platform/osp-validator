package com.opensimulationplatform.core.validator.systemstructure;

import org.junit.Test;

import java.util.List;

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
    
    List<SystemStructureValidator.Diagnostic> diagnostics = result.getDiagnostics();
  
    SystemStructureValidator.Diagnostic diagnostic = diagnostics.get(0);
  
    assertEquals(
        "position SubClassOf has_signal_connector_mate only position\n" +
        "has_plug_mate SubPropertyOf has_signal_connector_mate\n" +
        "simulator_simulatorB_position_socket_socketA Type position\n" +
        "simulator_simulatorA_force_plug_plugA Type force\n" +
        "force DisjointWith position\n" +
        "simulator_simulatorB_position_socket_socketA has_plug_mate simulator_simulatorA_force_plug_plugA\n", diagnostic.getMessage());
    
    assertEquals(2, diagnostic.getOspObjects().size());
  }
}