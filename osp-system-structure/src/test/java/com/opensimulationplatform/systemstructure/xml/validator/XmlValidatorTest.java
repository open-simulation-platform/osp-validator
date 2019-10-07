package com.opensimulationplatform.systemstructure.xml.validator;

import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.TestResources;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XmlValidatorTest {
  @Test
  public void canValidate() {
    SystemStructureValidator.Result result = XmlValidator.validate(TestResources.SYSTEM_STRUCTURE_VALID_XML.toFile(), Resources.OSP_OWL.toFile());
    assertTrue(result.isSuccess());
  
    result = XmlValidator.validate(TestResources.SYSTEM_STRUCTURE_INVALID_XML.toFile(), Resources.OSP_OWL.toFile());
    assertFalse(result.isSuccess());
  }
}