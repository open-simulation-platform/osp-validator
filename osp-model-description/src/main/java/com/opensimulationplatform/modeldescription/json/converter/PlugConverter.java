package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.Plug;

public class PlugConverter {
  public static OspPlug convert(Plug plug) {
    OspPlug ospPlug = new OspPlug(plug.getType(), plug.getName());
    plug.getVariables().forEach(v -> ospPlug.addVariable(new OspVariable(v)));
    
    return ospPlug;
  }
}
