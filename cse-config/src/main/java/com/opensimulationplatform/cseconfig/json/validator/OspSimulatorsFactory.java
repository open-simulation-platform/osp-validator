package com.opensimulationplatform.cseconfig.json.validator;

import com.opensimulationplatform.core.validator.model.modeldescription.OspBond;
import com.opensimulationplatform.core.validator.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.validator.model.modeldescription.OspSimulator;
import com.opensimulationplatform.core.validator.model.modeldescription.OspSocket;
import com.opensimulationplatform.cseconfig.json.model.JsonCseConfiguration;
import com.opensimulationplatform.cseconfig.json.model.JsonCseSimulator;
import com.opensimulationplatform.modeldescription.json.model.JsonOspModelDescription;
import com.opensimulationplatform.modeldescription.json.parser.JsonOspModelDescriptionParser;
import com.opensimulationplatform.modeldescription.json.validator.OspModelDescriptionFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OspSimulatorsFactory {
  public static Map<String, OspSimulator> create(JsonCseConfiguration jsonConfiguration) {
    Map<String, OspSimulator> simulators = new HashMap<>();
    for (JsonCseSimulator jsonCseSimulator : jsonConfiguration.getSimulators()) {
      JsonOspModelDescription jsonOspModelDescription = JsonOspModelDescriptionParser.parse(new File(jsonCseSimulator.getModelDefinition()));
      Map<String, OspPlug> plugs = OspModelDescriptionFactory.createPlugs(jsonOspModelDescription);
      Map<String, OspSocket> sockets = OspModelDescriptionFactory.createSockets(jsonOspModelDescription);
      List<OspBond> ospBonds = OspModelDescriptionFactory.createBonds(jsonOspModelDescription, plugs, sockets);
      
      OspSimulator s = new OspSimulator(jsonCseSimulator.getName(), jsonCseSimulator.getSource(), jsonCseSimulator.getModelDefinition());
      
      plugs.forEach((name, plug) -> s.addPlug(plug));
      sockets.forEach((name, socket) -> s.addSocket(socket));
      ospBonds.forEach(s::addBond);
      
      simulators.put(s.getName(), s);
    }
    return simulators;
  }
}
