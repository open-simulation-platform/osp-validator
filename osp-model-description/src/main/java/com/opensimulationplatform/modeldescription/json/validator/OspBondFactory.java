package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.json.model.JsonOspBond;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OspBondFactory {
  public static OspBond create(JsonOspBond jsonOspBond, Map<String, OspPlug> plugs, Map<String, OspSocket> sockets) {
    OspBond ospBond = new OspBond(jsonOspBond.getName());
    
    List<OspPlug> ospBondPlugs = new ArrayList<>();
    for (String plugNames : jsonOspBond.getPlugs()) {
      OspPlug ospPlug = plugs.get(plugNames);
      ospBondPlugs.add(ospPlug);
    }
    ospBondPlugs.forEach(ospBond::addPlug);
    
    List<OspSocket> ospBondSockets = new ArrayList<>();
    for (String socketNames : jsonOspBond.getSockets()) {
      OspSocket ospSocket = sockets.get(socketNames);
      ospBondSockets.add(ospSocket);
    }
    ospBondSockets.forEach(ospBond::addSocket);
    
    return ospBond;
  }
}
