package com.opensimulationplatform.msmivalidator;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntology;

import java.io.File;

import static org.junit.Assert.*;

public class MsmiValidatorTest {
  @Test
  public void validConfigurationIsProperlyValidated() {
    MsmiValidator.Result result = MsmiValidator.validate(new File("./src/test/resources/validator/osp.owl"), new File("./src/test/resources/validator/cse-config-valid.json"));
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationIsProperlyValidated() {
    MsmiValidator.Result result = MsmiValidator.validate(new File("./src/test/resources/validator/osp.owl"), new File("./src/test/resources/validator/cse-config-invalid.json"));
    assertFalse(result.isSuccess());
  }
}