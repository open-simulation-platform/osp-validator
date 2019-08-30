package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.JsonOspPlug;

import java.util.List;

public class OspPlugFactory {
  public static OspPlug create(JsonOspPlug jsonOspPlug) {
    OspPlug ospPlug = new OspPlug(jsonOspPlug.getType(), jsonOspPlug.getName());
    List<OspVariable> ospVariables = OspVariableFactory.create(jsonOspPlug.getVariables());
    ospVariables.forEach(ospPlug::addVariable);
    return ospPlug;
  }
}
