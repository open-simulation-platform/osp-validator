package com.opensimulationplatform.cseconfig.validator;

import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.cseconfig.TestResources;
import com.opensimulationplatform.cseconfig.json.validator.JsonCseConfigurationValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonCseConfigurationValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    JsonCseConfigurationValidator.Result result = JsonCseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_VALID_JSON.toFile());
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    JsonCseConfigurationValidator.Result result = JsonCseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_INVALID_JSON.toFile());
    assertFalse(result.isSuccess());
    
    result = JsonCseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), TestResources.CSE_CONFIG_INVALID_B_JSON.toFile());
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void canUseDefaultOwlFile() {
    JsonCseConfigurationValidator.Result result = JsonCseConfigurationValidator.validate(TestResources.CSE_CONFIG_VALID_JSON.toFile());
    assertTrue(result.isSuccess());
  }
}