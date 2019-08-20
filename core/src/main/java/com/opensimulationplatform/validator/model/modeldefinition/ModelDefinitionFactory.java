package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.json.model.modeldefinition.JsonBond;
import com.opensimulationplatform.json.model.modeldefinition.JsonModelDefinition;
import com.opensimulationplatform.json.model.modeldefinition.JsonPlug;
import com.opensimulationplatform.json.model.modeldefinition.JsonSocket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelDefinitionFactory {
  public static ModelDefinition create(JsonModelDefinition jsonModelDefinition) {
    ModelDefinition modelDefinition = new ModelDefinition();
    
    modelDefinition.setName(jsonModelDefinition.getName());
    
    Map<String, Plug> plugs = createPlugs(jsonModelDefinition);
    Map<String, Socket> sockets = createSockets(jsonModelDefinition);
    List<Bond> bonds = createBonds(jsonModelDefinition, plugs, sockets);
    
    modelDefinition.setPlugs(new ArrayList<>(plugs.values()));
    modelDefinition.setSockets(new ArrayList<>(sockets.values()));
    modelDefinition.setBonds(bonds);
    
    return modelDefinition;
  }
  
  static List<Bond> createBonds(JsonModelDefinition jsonModelDefinition, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    List<Bond> bonds = new ArrayList<>();
    for (JsonBond jsonBond : jsonModelDefinition.getBonds()) {
      Bond b = BondFactory.create(jsonBond, plugs, sockets);
      bonds.add(b);
    }
    return bonds;
  }
  
  static Map<String, Plug> createPlugs(JsonModelDefinition jsonModelDefinition) {
    Map<String, Plug> plugs = new LinkedHashMap<>();
    for (JsonPlug jsonPlug : jsonModelDefinition.getPlugs()) {
      Plug p = PlugFactory.create(jsonPlug);
      plugs.put(p.getName(), p);
    }
    return plugs;
  }
  
  static Map<String, Socket> createSockets(JsonModelDefinition jsonModelDefinition) {
    Map<String, Socket> sockets = new LinkedHashMap<>();
    for (JsonSocket jsonSocket : jsonModelDefinition.getSockets()) {
      Socket s = SocketFactory.create(jsonSocket);
      sockets.put(s.getName(), s);
    }
    return sockets;
  }
}
