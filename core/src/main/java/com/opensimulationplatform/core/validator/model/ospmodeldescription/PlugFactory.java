package com.opensimulationplatform.core.validator.model.ospmodeldescription;

import com.opensimulationplatform.core.json.model.modeldefinition.JsonPlug;

import java.util.List;

public class PlugFactory {
  public static Plug create(JsonPlug jsonPlug) {
    Plug plug = new Plug(jsonPlug.getType(), jsonPlug.getName());
    List<Variable> variables = VariableFactory.create(jsonPlug.getVariables());
    variables.forEach(plug::addVariable);
    return plug;
  }
}
