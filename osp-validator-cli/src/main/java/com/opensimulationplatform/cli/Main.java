package com.opensimulationplatform.cli;

import com.beust.jcommander.*;
import com.beust.jcommander.converters.FileConverter;
import com.opensimulationplatform.cli.terminator.ExitCode;
import com.opensimulationplatform.cli.terminator.Terminator;
import com.opensimulationplatform.cli.version.Version;

import java.io.File;
import java.util.List;

public class Main {


  public static class MainCommand {
    @Parameter(names = {"--version", "-v"}, description = "Print version")
    public Boolean version;

    @Parameter(names = {"--help", "-h"}, description = "Print help")
    public Boolean help;
  }

  @Parameters(commandDescription = "Validate OspSystemStructure.xml")
  public static class OspSystemStructureCommand {
    @Parameter(names = "-file", description = "Path to OspSystemStructure.xml", validateWith = FileExistsValidator.class)
    public String ospSystemStructure;
  }

  @Parameters(commandDescription = "Validate OspModelDsecription")
  public static class OspModelDescriptionCommand {
    @Parameter(names = "-file", description = "Path to OspModelDescription.xml", validateWith = FileExistsValidator.class)
    public String ospModelDescription;

    @Parameter(names = "-fmu", description = "Path to fmu", validateWith = FileExistsValidator.class)
    public String fmu;
  }

  public static class FileExistsValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
      File file = getFile(value);
      if (!file.exists()) {
        throw new ParameterException(value + " does not exist");
      }
    }

    File getFile(String value) {
      return new FileConverter().convert(value);
    }
  }

  public static void main(String[] args) {
    MainCommand mc = new MainCommand();
    OspSystemStructureCommand ospSystemStructureCommand = new OspSystemStructureCommand();
    OspModelDescriptionCommand ospModelDescriptionCommand = new OspModelDescriptionCommand();

    JCommander jc = JCommander.newBuilder()
        .addObject(mc)
        .addCommand("osp-system-structure", ospSystemStructureCommand)
        .addCommand("osp-model-description", ospModelDescriptionCommand)
        .build();
    jc.setProgramName("msmi-cli.jar");

    try {
      jc.parse(args);

      String parsedCommand = jc.getParsedCommand();
      if ("osp-system-structure".equals(parsedCommand)) {
        Validator v = new Validator();
        List<String> errorMessages = v.validate(new File(ospSystemStructureCommand.ospSystemStructure));
        for (String errorMessage : errorMessages) {
          System.out.println(errorMessage);
        }
      } else if ("osp-model-description".equals(parsedCommand)) {
        Validator v = new Validator();
        List<String> errorMessages = v.validate(new File(ospModelDescriptionCommand.ospModelDescription), new File(ospModelDescriptionCommand.fmu));
        for (String errorMessage : errorMessages) {
          System.out.println(errorMessage);
        }
      }

      if (mc.version != null) {
        System.out.println(Version.getVersionInfo());
      }

      if (mc.help != null) {
        jc.usage();
      }

      Terminator.exit(new ExitCode(0, ""));
    } catch (ParameterException e) {
      System.out.println(e.getMessage());
      jc.usage();
      Terminator.exit(new ExitCode(-1, "Error during parsing of command line arguments"));
    }
  }
}
