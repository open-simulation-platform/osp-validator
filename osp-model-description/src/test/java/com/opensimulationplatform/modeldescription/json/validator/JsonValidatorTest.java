package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.validator.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.TestResources;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JsonValidatorTest {
  @Test
  public void canValidate() {
    ModelDescriptionValidator.Result result = JsonValidator.validate(TestResources.CRANE_CONTROLLER_JSON.toFile(), TestResources.CRANE_CONTROLLER_FMU.toFile());
    assertTrue(String.valueOf(result.getMessages()), result.isValid());
  
    result = JsonValidator.validate(TestResources.KNUCKLE_BOOM_CRANE_JSON.toFile(), TestResources.KNUCKLE_BOOM_CRANE_FMU.toFile());
    assertTrue(String.valueOf(result.getMessages()), result.isValid());
  }
}