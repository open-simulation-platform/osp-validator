package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.json.model.configuration.JsonConfiguration;
import com.opensimulationplatform.json.model.configuration.JsonSimulator;
import com.opensimulationplatform.json.model.modeldefinition.JsonModelDefinition;
import com.opensimulationplatform.json.model.parsing.ModelDefinitionJsonFileParser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorFactory {
  public static Map<String, Simulator> create(JsonConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = new HashMap<>();
    for (JsonSimulator jsonSimulator : jsonConfiguration.getSimulators()) {
      JsonModelDefinition jsonModelDefinition = ModelDefinitionJsonFileParser.parse(new File(jsonSimulator.getModelDefinition()));
      Map<String, Plug> plugs = ModelDefinitionFactory.createPlugs(jsonModelDefinition);
      Map<String, Socket> sockets = ModelDefinitionFactory.createSockets(jsonModelDefinition);
      List<Bond> bonds = ModelDefinitionFactory.createBonds(jsonModelDefinition, plugs, sockets);
      
      Simulator s = new Simulator(jsonSimulator.getName(), jsonSimulator.getSource(), jsonSimulator.getModelDefinition());
      
      plugs.forEach((name, plug) -> s.addPlug(plug));
      sockets.forEach((name, socket) -> s.addSocket(socket));
      bonds.forEach(s::addBond);
      
      simulators.put(s.getName(), s);
    }
    return simulators;
  }
}
