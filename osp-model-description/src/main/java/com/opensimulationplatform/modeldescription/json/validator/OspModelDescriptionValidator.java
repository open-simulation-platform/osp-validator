package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.*;
import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.core.util.resource.Resources;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;


public class OspModelDescriptionValidator {
  private static final Logger LOG = LoggerFactory.getLogger(OspModelDescriptionValidator.class);
  
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
    LOG.debug("Validating osp model description: " + modelDescription.getName());
    return new Result(validations.stream().allMatch(validation -> validation.apply(modelDescription)));
  }
  
  private static boolean allVariablesInAllSocketsExistInFmiModelDescription(OspModelDescription modelDescription) {
    LOG.trace("allVariablesInAllSocketsExistInFmiModelDescription");
    FmiModelDescription fmiModelDescription = modelDescription.getFmiModelDescription();
    if (nonNull(fmiModelDescription)) {
      FmiModelDescription.ModelVariables modelVariables = fmiModelDescription.getModelVariables();
      List<FmiScalarVariable> fmiScalarVariables = modelVariables.getScalarVariable();
      return modelDescription.getOspSockets().stream().allMatch(socket -> {
        Stream<OspVariable> variables = socket.getVariables().values().stream();
        return variables.allMatch(variable -> {
          Stream<FmiScalarVariable> fmiVariables = fmiScalarVariables.stream();
          boolean anyMatch = fmiVariables.anyMatch(fmiVariable -> fmiVariable.getName().equals(variable.getName()));
          if (!anyMatch) {
            LOG.debug("Variable: " + socket.getName() + "." + variable.getName() + " does NOT exists in fmiModelDescription: " + fmiModelDescription.getModelName());
          }
          return anyMatch;
        });
      });
    } else {
      return true;
    }
  }
  
  private static boolean allVariablesInAllPlugsExistInFmiModelDescription(OspModelDescription modelDescription) {
    LOG.trace("allVariablesInAllPlugsExistInFmiModelDescription");
    FmiModelDescription fmiModelDescription = modelDescription.getFmiModelDescription();
    if (nonNull(fmiModelDescription)) {
      FmiModelDescription.ModelVariables modelVariables = fmiModelDescription.getModelVariables();
      List<FmiScalarVariable> fmiScalarVariables = modelVariables.getScalarVariable();
      return modelDescription.getOspPlugs().stream().allMatch(plug -> {
        Stream<OspVariable> variables = plug.getVariables().values().stream();
        return variables.allMatch(variable -> {
          LOG.debug("Checking if variable: " + plug.getName() + "." + variable.getName() + " exists in fmiModelDescription: " + fmiModelDescription.getModelName());
          Stream<FmiScalarVariable> fmiVariables = fmiScalarVariables.stream();
          boolean anyMatch = fmiVariables.anyMatch(fmiVariable -> fmiVariable.getName().equals(variable.getName()));
          if (!anyMatch) {
            LOG.debug("Variable: " + plug.getName() + "." + variable.getName() + " does NOT exists in fmiModelDescription: " + fmiModelDescription.getModelName());
          }
          return anyMatch;
        });
      });
    } else {
      return true;
    }
  }
  
  private static boolean allBondsHaveUniqueNames(OspModelDescription modelDescription) {
    LOG.trace("allBondsHaveUniqueNames");
    List<OspBond> ospBonds = modelDescription.getOspBonds();
    for (int i = 0; i < ospBonds.size(); i++) {
      for (int j = i + 1; j < ospBonds.size(); j++) {
        String nameA = ospBonds.get(i).getName();
        String nameB = ospBonds.get(j).getName();
        if (nameA.equals(nameB)) {
          LOG.debug("Bond " + nameA + " does not have a unique name");
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsHaveUniqueNames(OspModelDescription modelDescription) {
    LOG.trace("allPlugsHaveUniqueNames");
    List<OspPlug> ospPlugs = modelDescription.getOspPlugs();
    for (int i = 0; i < ospPlugs.size(); i++) {
      for (int j = i + 1; j < ospPlugs.size(); j++) {
        String nameA = ospPlugs.get(i).getName();
        String nameB = ospPlugs.get(j).getName();
        if (nameA.equals(nameB)) {
          LOG.debug("Plug " + nameA + " does not have a unique name");
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allSocketsHaveUniqueNames(OspModelDescription modelDescription) {
    LOG.trace("allSocketsHaveUniqueNames");
    List<OspSocket> ospSockets = modelDescription.getOspSockets();
    for (int i = 0; i < ospSockets.size(); i++) {
      for (int j = i + 1; j < ospSockets.size(); j++) {
        String nameA = ospSockets.get(i).getName();
        String nameB = ospSockets.get(j).getName();
        if (nameA.equals(nameB)) {
          LOG.debug("Socket " + nameA + " does not have a unique name");
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean allPlugsAndSocketsInBondsAreDefinedInOspModelDescription(OspModelDescription modelDescription) {
    LOG.trace("allPlugsAndSocketsInBondsAreDefinedInOspModelDescription");
    return modelDescription.getOspBonds().stream().allMatch(bond -> allPlugsDefined(bond, modelDescription) && allSocketsDefined(bond, modelDescription));
  }
  
  private static boolean allPlugsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    LOG.trace("allPlugsDefined");
    return ospBond.getOspPlugs().stream().allMatch(plug -> plugIsDefinedInModelDescription(modelDescription, plug));
  }
  
  private static boolean allSocketsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    LOG.trace("allSocketsDefined");
    return ospBond.getOspSockets().stream().allMatch(socket -> socketIsDefinedInModelDescription(modelDescription, socket));
  }
  
  private static boolean plugIsDefinedInModelDescription(OspModelDescription modelDescription, OspPlug ospPlug) {
    LOG.trace("plugIsDefinedInModelDescription");
    boolean anyMatch = modelDescription.getOspPlugs().stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
    if (!anyMatch) {
      LOG.debug("Plug " + ospPlug.getName() + " is not defined in osp model description " + modelDescription.getName());
    }
    return anyMatch;
  }
  
  private static boolean socketIsDefinedInModelDescription(OspModelDescription modelDescription, OspSocket ospSocket) {
    LOG.trace("socketIsDefinedInModelDescription");
    boolean anyMatch = modelDescription.getOspSockets().stream().anyMatch(p -> p.getName().equals(ospSocket.getName()));
    if (!anyMatch) {
      LOG.debug("Socket " + ospSocket.getName() + " is not defined in osp model description " + modelDescription.getName());
    }
    return anyMatch;
  }
  
  private static boolean allPlugTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    LOG.trace("allPlugTypesAreContainedInOntology");
    return ospModelDescription.getOspPlugs().stream().allMatch(plug -> {
      boolean containsKey = ontologyContent.getClasses().containsKey(plug.getType());
      if (!containsKey) {
        LOG.debug("Plug type " + plug.getType() + " in plug " + plug.getName() + " is not defined in osp model description " + ospModelDescription.getName());
      }
      return containsKey;
    });
  }
  
  private static boolean allSocketTypesAreContainedInOntology(OspModelDescription ospModelDescription) {
    LOG.trace("allSocketTypesAreContainedInOntology");
    return ospModelDescription.getOspSockets().stream().allMatch(socket -> {
      boolean containsKey = ontologyContent.getClasses().containsKey(socket.getType());
      if (!containsKey) {
        LOG.debug("Socket type " + socket.getType() + " in socket " + socket.getName() + " is not defined in osp model description " + ospModelDescription.getName());
      }
      return containsKey;
    });
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
