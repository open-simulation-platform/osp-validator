package com.opensimulationplatform.core.validator;

import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.core.validator.model.modeldescription.*;
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
      return modelDescription.getOspSockets().stream().allMatch(socket -> {
        Stream<OspVariable> variables = socket.getVariables().values().stream();
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
      return modelDescription.getOspPlugs().stream().allMatch(plug -> {
        Stream<OspVariable> variables = plug.getVariables().values().stream();
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
    List<OspBond> ospBonds = modelDescription.getOspBonds();
    for (int i = 0; i < ospBonds.size(); i++) {
      for (int j = i + 1; j < ospBonds.size(); j++) {
        if (ospBonds.get(i).getName().equals(ospBonds.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsHaveUniqueNames(OspModelDescription modelDescription) {
    List<OspPlug> ospPlugs = modelDescription.getOspPlugs();
    for (int i = 0; i < ospPlugs.size(); i++) {
      for (int j = i + 1; j < ospPlugs.size(); j++) {
        if (ospPlugs.get(i).getName().equals(ospPlugs.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allSocketsHaveUniqueNames(OspModelDescription modelDescription) {
    List<OspSocket> ospSockets = modelDescription.getOspSockets();
    for (int i = 0; i < ospSockets.size(); i++) {
      for (int j = i + 1; j < ospSockets.size(); j++) {
        if (ospSockets.get(i).getName().equals(ospSockets.get(j).getName())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsAndSocketsInBondsAreDefinedInOspModelDescription(OspModelDescription modelDescription) {
    return modelDescription.getOspBonds().stream().allMatch(bond -> allPlugsDefined(bond, modelDescription) && allSocketsDefined(bond, modelDescription));
  }
  
  private static boolean allPlugsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspPlugs().stream().allMatch(plug -> plugIsDefinedInModelDescription(modelDescription, plug));
  }
  
  private static boolean allSocketsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspSockets().stream().allMatch(socket -> socketIsDefinedInModelDescription(modelDescription, socket));
  }
  
  private static boolean plugIsDefinedInModelDescription(OspModelDescription modelDescription, OspPlug ospPlug) {
    return modelDescription.getOspPlugs().stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
  }
  
  private static boolean socketIsDefinedInModelDescription(OspModelDescription modelDescription, OspSocket ospSocket) {
    return modelDescription.getOspSockets().stream().anyMatch(p -> p.getName().equals(ospSocket.getName()));
  }
  
  private static boolean allPlugTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    return ospModelDescription.getOspPlugs().stream().allMatch(plug -> ontologyContent.getClasses().containsKey(plug.getType()));
  }
  
  private static boolean allSocketTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    return ospModelDescription.getOspSockets().stream().allMatch(socket -> ontologyContent.getClasses().containsKey(socket.getType()));
  }
  
  public static class Result {
    private final boolean success;
    
    Result(boolean success) {
      this.success = success;
    }
    
    public boolean isSuccess() {
      return success;
    }
  }
}
