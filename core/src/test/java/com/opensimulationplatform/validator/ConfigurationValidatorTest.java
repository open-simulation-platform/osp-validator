package com.opensimulationplatform.validator;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfigurationValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    ConfigurationValidator.Result result = ConfigurationValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    ConfigurationValidator.Result result = ConfigurationValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-invalid.json"));
    assertFalse(result.isSuccess());
    
    result = ConfigurationValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-invalid-B.json"));
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void canUseDefaultOwlFile() {
    ConfigurationValidator.Result result = ConfigurationValidator.validate(new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
}