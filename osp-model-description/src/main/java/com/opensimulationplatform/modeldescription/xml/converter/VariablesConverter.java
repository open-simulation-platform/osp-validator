package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.Variables;

import java.util.ArrayList;
import java.util.List;

public class VariablesConverter {
  public static List<OspVariable> convert(Variables variables) {
    List<OspVariable> ospVariables = new ArrayList<>();
    variables.getVariable().forEach(v -> ospVariables.add(new OspVariable(v.getName())));
    
    return ospVariables;
  }
}
