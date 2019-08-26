package com.opensimulationplatform.cseconfig.json.validator;

import com.opensimulationplatform.core.model.configuration.OspConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OspConfigurationValidatorTest {
  @Test
  public void emptyConfigurationIsValid() {
    OspConfiguration configuration = new OspConfiguration();
    
    OspConfigurationValidator.Result result = OspConfigurationValidator.validate(configuration);
  
    assertTrue(result.isSuccess());
  }
}