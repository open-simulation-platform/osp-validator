package com.opensimulationplatform.modeldescription.xml.validator;

import com.opensimulationplatform.core.validator.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.TestResources;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XmlValidatorTest {
  @Test
  public void canValidate() {
    ModelDescriptionValidator.Result result = XmlValidator.validate(TestResources.CRANE_CONTROLLER_XML, TestResources.CRANE_CONTROLLER_FMU);
    assertTrue(String.valueOf(result.getMessages()), result.isValid());
  
    result = XmlValidator.validate(TestResources.KNUCKLE_BOOM_CRANE_XML, TestResources.KNUCKLE_BOOM_CRANE_FMU);
    assertTrue(String.valueOf(result.getMessages()), result.isValid());
  }
}