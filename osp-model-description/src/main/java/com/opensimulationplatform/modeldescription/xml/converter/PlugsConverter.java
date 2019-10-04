package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.Plugs;

import java.util.ArrayList;
import java.util.List;

public class PlugsConverter {
  public static List<OspPlug> convert(Plugs plugs) {
    List<OspPlug> ospPlugs = new ArrayList<>();
    
    plugs.getPlug().forEach(p -> {
      OspPlug ospPlug = new OspPlug(p.getType(), p.getName());
      List<OspVariable> ospVariables = VariablesConverter.convert(p.getVariables());
      ospVariables.forEach(ospPlug::addVariable);
      ospPlugs.add(ospPlug);
    });
    
    return ospPlugs;
  }
}
