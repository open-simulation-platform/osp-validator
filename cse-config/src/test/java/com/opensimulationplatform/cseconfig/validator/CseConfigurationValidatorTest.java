package com.opensimulationplatform.cseconfig.validator;

import com.opensimulationplatform.core.util.resource.Resources;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CseConfigurationValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), new File("./src/test/resources/validator/cse-config-invalid.json"));
    assertFalse(result.isSuccess());
    
    result = CseConfigurationValidator.validate(Resources.OSP_OWL.toFile(), new File("./src/test/resources/validator/cse-config-invalid-B.json"));
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void canUseDefaultOwlFile() {
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
}