package com.opensimulationplatform.cseconfig.json.validator;

import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.cseconfig.TestResources;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonValidatorTest {
  @Test
  public void canValidate() {
    SystemStructureValidator.Result result = JsonValidator.validate(TestResources.CSE_CONFIG_VALID_JSON.toFile(), Resources.OSP_OWL.toFile());
    assertTrue(result.isSuccess());
  
    result = JsonValidator.validate(TestResources.CSE_CONFIG_INVALID_JSON.toFile(), Resources.OSP_OWL.toFile());
    assertFalse(result.isSuccess());
  }
}