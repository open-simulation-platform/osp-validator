package com.opensimulationplatform.cli;

import com.opensimulationplatform.cli.terminator.ExitCode;
import com.opensimulationplatform.cli.terminator.Terminator;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Options options = new Options();

    Option ospSystemStructureOption = Option.builder("osp_system_structure")
        .longOpt("osp_system_structure")
        .argName("osp_system_structure")
        .desc("Path to OspSystemStructure.xml")
        .required()
        .hasArg()
        .build();

    options.addOption(ospSystemStructureOption);

    CommandLineParser parser = new DefaultParser();

    try {
      CommandLine commandLine = parser.parse(options, args);
      if (commandLine.hasOption(ospSystemStructureOption.getOpt())) {
        String optionValue = commandLine.getOptionValue(ospSystemStructureOption.getOpt());
        File ospSystemStructureFile = new File(optionValue);
        Validator v = new Validator();
        List<String> errorMessages = v.validate(ospSystemStructureFile);
        for (String errorMessage : errorMessages) {
          System.out.println(errorMessage);
        }
      }

      Terminator.exit(new ExitCode(0, ""));
    } catch (ParseException e) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.setWidth(150);
      formatter.printHelp("java -jar msmi-cli.jar", options);

      Terminator.exit(new ExitCode(-1, e.getMessage()));
    }
  }
}
