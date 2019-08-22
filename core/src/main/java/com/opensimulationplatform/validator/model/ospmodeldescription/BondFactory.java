package com.opensimulationplatform.validator.model.ospmodeldescription;

import com.opensimulationplatform.json.model.modeldefinition.JsonBond;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BondFactory {
  static Bond create(JsonBond jsonBond, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    Bond bond = new Bond(jsonBond.getName());
    
    List<Plug> bondPlugs = new ArrayList<>();
    for (String plugNames : jsonBond.getPlugs()) {
      Plug plug = plugs.get(plugNames);
      bondPlugs.add(plug);
    }
    bondPlugs.forEach(bond::addPlug);
    
    List<Socket> bondSockets = new ArrayList<>();
    for (String socketNames : jsonBond.getSockets()) {
      Socket socket = sockets.get(socketNames);
      bondSockets.add(socket);
    }
    bondSockets.forEach(bond::addSocket);
    
    return bond;
  }
}
