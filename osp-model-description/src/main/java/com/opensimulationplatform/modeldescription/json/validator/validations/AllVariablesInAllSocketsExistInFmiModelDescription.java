package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class AllVariablesInAllSocketsExistInFmiModelDescription implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    List<String> messages = new ArrayList<>();
    FmiModelDescription fmiModelDescription = ospModelDescription.getFmiModelDescription();
    if (nonNull(fmiModelDescription)) {
      FmiModelDescription.ModelVariables modelVariables = fmiModelDescription.getModelVariables();
      List<FmiScalarVariable> fmiScalarVariables = modelVariables.getScalarVariable();
      boolean allMatch = ospModelDescription.getOspSockets().stream().allMatch(socket -> {
        Stream<OspVariable> variables = socket.getVariables().values().stream();
        return variables.allMatch(variable -> {
          Stream<FmiScalarVariable> fmiVariables = fmiScalarVariables.stream();
          boolean anyMatch = fmiVariables.anyMatch(fmiVariable -> fmiVariable.getName().equals(variable.getName()));
          if (!anyMatch) {
            messages.add("Variable '" + variable.getName() + "' in socket '" + socket.getName() + "' does not exists in fmiModelDescription '" + fmiModelDescription.getModelName() + "'");
          }
          return anyMatch;
        });
      });
      return new Result(allMatch, messages);
    } else {
      messages.add("fmiModelDescription is null");
      return new Result(true, messages);
    }
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
