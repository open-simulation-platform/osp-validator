package com.opensimulationplatform.cseconfig.validator;

import com.opensimulationplatform.core.json.model.modeldefinition.JsonOspModelDescription;
import com.opensimulationplatform.core.json.model.parsing.OspModelDescriptionJsonFileParser;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.*;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonCseConfiguration;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonSimulator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorFactory {
  public static Map<String, Simulator> create(JsonCseConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = new HashMap<>();
    for (JsonSimulator jsonSimulator : jsonConfiguration.getSimulators()) {
      JsonOspModelDescription jsonOspModelDescription = OspModelDescriptionJsonFileParser.parse(new File(jsonSimulator.getModelDefinition()));
      Map<String, Plug> plugs = OspModelDescriptionFactory.createPlugs(jsonOspModelDescription);
      Map<String, Socket> sockets = OspModelDescriptionFactory.createSockets(jsonOspModelDescription);
      List<Bond> bonds = OspModelDescriptionFactory.createBonds(jsonOspModelDescription, plugs, sockets);
      
      Simulator s = new Simulator(jsonSimulator.getName(), jsonSimulator.getSource(), jsonSimulator.getModelDefinition());
      
      plugs.forEach((name, plug) -> s.addPlug(plug));
      sockets.forEach((name, socket) -> s.addSocket(socket));
      bonds.forEach(s::addBond);
      
      simulators.put(s.getName(), s);
    }
    return simulators;
  }
}
