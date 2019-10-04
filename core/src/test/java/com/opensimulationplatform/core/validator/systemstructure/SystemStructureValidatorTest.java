package com.opensimulationplatform.core.validator.systemstructure;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  }
}