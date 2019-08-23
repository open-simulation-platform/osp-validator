package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.json.model.JsonOspBond;
import com.opensimulationplatform.modeldescription.json.model.JsonOspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.JsonOspPlug;
import com.opensimulationplatform.modeldescription.json.model.JsonOspSocket;
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
    
    Map<String, OspPlug> plugs = createPlugs(jsonOspModelDescription);
    Map<String, OspSocket> sockets = createSockets(jsonOspModelDescription);
    List<OspBond> ospBonds = createBonds(jsonOspModelDescription, plugs, sockets);
    
    ospModelDescription.setOspPlugs(new ArrayList<>(plugs.values()));
    ospModelDescription.setOspSockets(new ArrayList<>(sockets.values()));
    ospModelDescription.setOspBonds(ospBonds);
    
    return ospModelDescription;
  }
  
  public static List<OspBond> createBonds(JsonOspModelDescription jsonOspModelDescription, Map<String, OspPlug> plugs, Map<String, OspSocket> sockets) {
    List<OspBond> ospBonds = new ArrayList<>();
    for (JsonOspBond jsonOspBond : jsonOspModelDescription.getBonds()) {
      OspBond b = OspBondFactory.create(jsonOspBond, plugs, sockets);
      ospBonds.add(b);
    }
    return ospBonds;
  }
  
  public static Map<String, OspPlug> createPlugs(JsonOspModelDescription jsonOspModelDescription) {
    Map<String, OspPlug> plugs = new LinkedHashMap<>();
    for (JsonOspPlug jsonOspPlug : jsonOspModelDescription.getPlugs()) {
      OspPlug p = OspPlugFactory.create(jsonOspPlug);
      plugs.put(p.getName(), p);
    }
    return plugs;
  }
  
  public static Map<String, OspSocket> createSockets(JsonOspModelDescription jsonOspModelDescription) {
    Map<String, OspSocket> sockets = new LinkedHashMap<>();
    for (JsonOspSocket jsonOspSocket : jsonOspModelDescription.getSockets()) {
      OspSocket s = OspSocketFactory.create(jsonOspSocket);
      sockets.put(s.getName(), s);
    }
    return sockets;
  }
}
