package com.opensimulationplatform.validator.model.modeldefinition;

import java.util.ArrayList;
import java.util.List;

public class VariableFactory {
  public static List<Variable> create(List<String> variableList) {
    List<Variable> variables = new ArrayList<>();
    for (String variableName : variableList) {
      Variable v = new Variable(variableName);
      variables.add(v);
    }
    return variables;
  }
}
