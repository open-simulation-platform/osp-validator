package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.validator.model.modeldescription.OspVariable;

import java.util.ArrayList;
import java.util.List;

public class OspVariableFactory {
  public static List<OspVariable> create(List<String> variableList) {
    List<OspVariable> ospVariables = new ArrayList<>();
    for (String variableName : variableList) {
      OspVariable v = new OspVariable(variableName);
      ospVariables.add(v);
    }
    return ospVariables;
  }
}
