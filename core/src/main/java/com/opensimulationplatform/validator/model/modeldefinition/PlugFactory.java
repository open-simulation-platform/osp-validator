package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.json.model.modeldefinition.JsonPlug;

import java.util.List;

public class PlugFactory {
  public static Plug create(JsonPlug jsonPlug) {
    Plug plug = new Plug(jsonPlug.getType(), jsonPlug.getName());
    List<Variable> variables = VariableFactory.create(jsonPlug.getVariables());
    variables.forEach(plug::addVariable);
    return plug;
  }
}
