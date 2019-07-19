package com.opensimulationplatform.msmivalidator;

import com.opensimulationplatform.validator.MsmiValidator;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MsmiValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    MsmiValidator.Result result = MsmiValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    MsmiValidator.Result result = MsmiValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-invalid.json"));
    assertFalse(result.isSuccess());
    
    result = MsmiValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/cse-config-invalid-B.json"));
    assertFalse(result.isSuccess());
  }
}