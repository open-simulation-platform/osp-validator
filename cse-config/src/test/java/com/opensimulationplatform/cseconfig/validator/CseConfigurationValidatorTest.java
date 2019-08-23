package com.opensimulationplatform.cseconfig.validator;

import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.cseconfig.TestResources;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CseConfigurationValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_VALID_JSON.toFile());
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_INVALID_JSON.toFile());
    assertFalse(result.isSuccess());
    
    result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_INVALID_B_JSON.toFile());
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void canUseDefaultOwlFile() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(TestResources.CSE_CONFIG_VALID_JSON.toFile());
    assertTrue(result.isSuccess());
  }
}