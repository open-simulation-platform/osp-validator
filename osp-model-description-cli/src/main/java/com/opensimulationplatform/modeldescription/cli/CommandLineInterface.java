package com.opensimulationplatform.modeldescription.cli;

import com.beust.jcommander.JCommander;
import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.util.loghelper.LogHelper;
import com.opensimulationplatform.core.util.terminator.Terminator;
import com.opensimulationplatform.modeldescription.cli.jcommander.Arguments;
import com.opensimulationplatform.modeldescription.json.model.JsonOspModelDescription;
import com.opensimulationplatform.modeldescription.json.parser.JsonOspModelDescriptionParser;
import com.opensimulationplatform.modeldescription.json.validator.OspModelDescriptionFactory;
import com.opensimulationplatform.modeldescription.json.validator.OspModelDescriptionValidator;
import no.ntnu.ihb.fmi4j.modeldescription.ModelDescriptionParser;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringReader;

public class CommandLineInterface {
  
  public static void main(String[] args) throws JAXBException {
    setLogLevel();
    Arguments arguments = getArguments(args);
    OspModelDescription ospModelDescription = getOspModelDescription(arguments);
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    evaluateResult(result);
  }
  
  private static void evaluateResult(OspModelDescriptionValidator.Result result) {
    if (result.isSuccess()) {
      Terminator.exit(ExitCodes.SUCCESS);
    } else {
      Terminator.exit(ExitCodes.INVALID_CONFIGURATION);
    }
  }
  
  private static void setLogLevel() {
    if (!LogHelper.setLogLevelFromOspSystemProperty()) {
      Terminator.exit(ExitCodes.INVALID_LOG_LEVEL);
    }
  }
  
  private static OspModelDescription getOspModelDescription(Arguments arguments) throws JAXBException {
    JsonOspModelDescription jsonOspModelDescription = getJsonOspModelDescription(arguments);
    FmiModelDescription fmiModelDescription = getFmiModelDescription(arguments.getFmu());
    return OspModelDescriptionFactory.create(jsonOspModelDescription, fmiModelDescription);
  }
  
  private static JsonOspModelDescription getJsonOspModelDescription(Arguments arguments) {
    return JsonOspModelDescriptionParser.parse(arguments.getOspModelDescription());
  }
  
  private static FmiModelDescription getFmiModelDescription(File fmu) throws JAXBException {
    String fmiModelDescriptionXml = ModelDescriptionParser.extractModelDescriptionXml(fmu);
    JAXBContext jaxbContext = JAXBContext.newInstance(FmiModelDescription.class);
    return (FmiModelDescription) jaxbContext.createUnmarshaller().unmarshal(new StringReader(fmiModelDescriptionXml));
  }
  
  private static Arguments getArguments(String[] args) {
    Arguments arguments = new Arguments();
    try {
      JCommander.newBuilder().addObject(arguments).build().parse(args);
    } catch (Exception e) {
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    return arguments;
  }
}
