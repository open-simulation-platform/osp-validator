package com.opensimulationplatform.modeldescription.cli.jcommander;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Arguments {
  @Parameter
  private List<String> parameters = new ArrayList<>();
  
  @Parameter(names = {"--osp-ontology"}, description = "Path to OSP ontology file", validateWith = FileExists.class)
  private File ospOntology;
  
  @Parameter(names = {"--osp-model-description"}, description = "Path to OSP model description file", required = true, validateWith = FileExists.class)
  private File ospModelDescription;
  
  @Parameter(names = {"--fmu"}, description = "Path to FMU defined by OSP model description file", required = true, validateWith = FileExists.class)
  private File fmu;
  
  public File getOspOntology() {
    return ospOntology;
  }
  
  public File getOspModelDescription() {
    return ospModelDescription;
  }
  
  public File getFmu() {
    return fmu;
  }
}
