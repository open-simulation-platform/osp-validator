package com.opensimulationplatform.core.validator.model.ospmodeldescription;

import com.opensimulationplatform.core.json.model.modeldefinition.JsonBond;
import com.opensimulationplatform.core.json.model.modeldefinition.JsonOspModelDescription;
import com.opensimulationplatform.core.json.model.modeldefinition.JsonPlug;
import com.opensimulationplatform.core.json.model.modeldefinition.JsonSocket;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OspModelDescriptionFactory {
  public static OspModelDescription create(JsonOspModelDescription jsonOspModelDescription, FmiModelDescription fmiModelDescription) {
    OspModelDescription ospModelDescription = create(jsonOspModelDescription);
    ospModelDescription.setFmiModelDescription(fmiModelDescription);
    return ospModelDescription;
  }
  
  public static OspModelDescription create(JsonOspModelDescription jsonOspModelDescription) {
    OspModelDescription ospModelDescription = new OspModelDescription();
    
    ospModelDescription.setName(jsonOspModelDescription.getName());
    
    Map<String, Plug> plugs = createPlugs(jsonOspModelDescription);
    Map<String, Socket> sockets = createSockets(jsonOspModelDescription);
    List<Bond> bonds = createBonds(jsonOspModelDescription, plugs, sockets);
    
    ospModelDescription.setPlugs(new ArrayList<>(plugs.values()));
    ospModelDescription.setSockets(new ArrayList<>(sockets.values()));
    ospModelDescription.setBonds(bonds);
    
    return ospModelDescription;
  }
  
  public static List<Bond> createBonds(JsonOspModelDescription jsonOspModelDescription, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    List<Bond> bonds = new ArrayList<>();
    for (JsonBond jsonBond : jsonOspModelDescription.getBonds()) {
      Bond b = BondFactory.create(jsonBond, plugs, sockets);
      bonds.add(b);
    }
    return bonds;
  }
  
  public static Map<String, Plug> createPlugs(JsonOspModelDescription jsonOspModelDescription) {
    Map<String, Plug> plugs = new LinkedHashMap<>();
    for (JsonPlug jsonPlug : jsonOspModelDescription.getPlugs()) {
      Plug p = PlugFactory.create(jsonPlug);
      plugs.put(p.getName(), p);
    }
    return plugs;
  }
  
  public static Map<String, Socket> createSockets(JsonOspModelDescription jsonOspModelDescription) {
    Map<String, Socket> sockets = new LinkedHashMap<>();
    for (JsonSocket jsonSocket : jsonOspModelDescription.getSockets()) {
      Socket s = SocketFactory.create(jsonSocket);
      sockets.put(s.getName(), s);
    }
    return sockets;
  }
}
