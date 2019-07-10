package com.opensimulationplatform.msmivalidator;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntology;

import java.io.File;

import static org.junit.Assert.*;

public class MsmiValidatorTest {
  @Test
  public void canValidate() {
    MsmiValidator.Result result = MsmiValidator.validate(new File("./src/test/resources/validator/osp.owl"), new File("./src/test/resources/validator/cse-config.json"));
    assertTrue(result.isSuccess());
  }
}