package com.opensimulationplatform.validator;

import com.opensimulationplatform.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.util.resource.Resources;
import com.opensimulationplatform.validator.model.ospmodeldescription.*;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;


public class OspModelDescriptionValidator {
  private static final List<Function<OspModelDescription, Boolean>> validations = new ArrayList<>();
  private static final OntologyContent ontologyContent;
  
  static {
    ontologyContent = OntologyParser.parse(Resources.OSP_OWL.toFile());
    validations.add(OspModelDescriptionValidator::allPlugTypesAreContainedInOntology);
    validations.add(OspModelDescriptionValidator::allSocketTypesAreContainedInOntology);
    validations.add(OspModelDescriptionValidator::allPlugsAndSocketsInBondsAreDefinedInOspModelDescription);
    validations.add(OspModelDescriptionValidator::allPlugsHaveUniqueNames);
    validations.add(OspModelDescriptionValidator::allSocketsHaveUniqueNames);
    validations.add(OspModelDescriptionValidator::allBondsHaveUniqueNames);
    validations.add(OspModelDescriptionValidator::allVariablesInAllPlugsExistInFmiModelDescription);
    validations.add(OspModelDescriptionValidator::allVariablesInAllSocketsExistInFmiModelDescription);
  }
  
  public static Result validate(OspModelDescription modelDescription) {
    return new Result(validations.stream().allMatch(validation -> validation.apply(modelDescription)));
  }
  
  private static boolean allVariablesInAllSocketsExistInFmiModelDescription(OspModelDescription modelDescription) {
    FmiModelDescription fmiModelDescription = modelDescription.getFmiModelDescription();
    if (nonNull(fmiModelDescription)) {
      FmiModelDescription.ModelVariables modelVariables = fmiModelDescription.getModelVariables();
      List<FmiScalarVariable> fmiScalarVariables = modelVariables.getScalarVariable();
      return modelDescription.getSockets().stream().allMatch(socket -> {
        Stream<Variable> variables = socket.getVariables().values().stream();
        return variables.allMatch(variable -> {
          Stream<FmiScalarVariable> fmiVariables = fmiScalarVariables.stream();
          return fmiVariables.anyMatch(fmiVariable -> fmiVariable.getName().equals(variable.getName()));
        });
      });
    } else {
      return true;
    }
  }
  
  private static boolean allVariablesInAllPlugsExistInFmiModelDescription(OspModelDescription modelDescription) {
    FmiModelDescription fmiModelDescription = modelDescription.getFmiModelDescription();
    if (nonNull(fmiModelDescription)) {
      FmiModelDescription.ModelVariables modelVariables = fmiModelDescription.getModelVariables();
      List<FmiScalarVariable> fmiScalarVariables = modelVariables.getScalarVariable();
      return modelDescription.getPlugs().stream().allMatch(plug -> {
        Stream<Variable> variables = plug.getVariables().values().stream();
        return variables.allMatch(variable -> {
          Stream<FmiScalarVariable> fmiVariables = fmiScalarVariables.stream();
          return fmiVariables.anyMatch(fmiVariable -> fmiVariable.getName().equals(variable.getName()));
        });
      });
    } else {
      return true;
    }
  }
  
  private static boolean allBondsHaveUniqueNames(OspModelDescription modelDescription) {
    List<Bond> bonds = modelDescription.getBonds();
    for (int i = 0; i < bonds.size(); i++) {
      for (int j = i + 1; j < bonds.size(); j++) {
        if (bonds.get(i).getName().equals(bonds.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsHaveUniqueNames(OspModelDescription modelDescription) {
    List<Plug> plugs = modelDescription.getPlugs();
    for (int i = 0; i < plugs.size(); i++) {
      for (int j = i + 1; j < plugs.size(); j++) {
        if (plugs.get(i).getName().equals(plugs.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allSocketsHaveUniqueNames(OspModelDescription modelDescription) {
    List<Socket> sockets = modelDescription.getSockets();
    for (int i = 0; i < sockets.size(); i++) {
      for (int j = i + 1; j < sockets.size(); j++) {
        if (sockets.get(i).getName().equals(sockets.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsAndSocketsInBondsAreDefinedInOspModelDescription(OspModelDescription modelDescription) {
    return modelDescription.getBonds().stream().allMatch(bond -> allPlugsDefined(bond, modelDescription) && allSocketsDefined(bond, modelDescription));
  }
  
  private static boolean allPlugsDefined(Bond bond, OspModelDescription modelDescription) {
    return bond.getPlugs().stream().allMatch(plug -> plugIsDefinedInModelDescription(modelDescription, plug));
  }
  
  private static boolean allSocketsDefined(Bond bond, OspModelDescription modelDescription) {
    return bond.getSockets().stream().allMatch(socket -> socketIsDefinedInModelDescription(modelDescription, socket));
  }
  
  private static boolean plugIsDefinedInModelDescription(OspModelDescription modelDescription, Plug plug) {
    return modelDescription.getPlugs().stream().anyMatch(p -> p.getName().equals(plug.getName()));
  }
  
  private static boolean socketIsDefinedInModelDescription(OspModelDescription modelDescription, Socket socket) {
    return modelDescription.getSockets().stream().anyMatch(p -> p.getName().equals(socket.getName()));
  }
  
  private static boolean allPlugTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    return ospModelDescription.getPlugs().stream().allMatch(plug -> ontologyContent.getClasses().containsKey(plug.getType()));
  }
  
  private static boolean allSocketTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    return ospModelDescription.getSockets().stream().allMatch(socket -> ontologyContent.getClasses().containsKey(socket.getType()));
  }
  
  static class Result {
    private final boolean success;
    
    Result(boolean success) {
      this.success = success;
    }
    
    boolean isSuccess() {
      return success;
    }
  }
}
