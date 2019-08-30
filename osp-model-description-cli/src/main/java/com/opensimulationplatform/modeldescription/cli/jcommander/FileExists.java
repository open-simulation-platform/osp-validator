package com.opensimulationplatform.modeldescription.cli.jcommander;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class FileExists implements IParameterValidator {
  @Override
  public void validate(String name, String value) throws ParameterException {
    File file = new File(value);
    if (!file.exists()) {
      throw new ParameterException("File " + name + " does not exist");
    }
  }
}
