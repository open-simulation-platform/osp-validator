package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.xml.model.BondConnections;
import com.opensimulationplatform.cseconfig.xml.model.ConnectionEndpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BondConnectionsConverter {
  public static List<OspBondConnection> convert(BondConnections bondConnections, List<OspSimulator> ospSimulators) {
    List<OspBondConnection> ospBondConnections = new ArrayList<>();
  
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    for (BondConnections.BondConnection bondConnection : bondConnections.getBondConnection()) {
      OspBondConnection ospBondConnection = convertOneBondConnection(bondConnection, ospSimulatorMap);
      ospBondConnections.add(ospBondConnection);
    }
  
    return ospBondConnections;
  }
  
  private static OspBondConnection convertOneBondConnection(BondConnections.BondConnection bondConnection, Map<String, OspSimulator> simulators) {
    ConnectionEndpoint bondA = bondConnection.getBond().get(0);
    OspBond ospBondA = simulators.get(bondA.getSimulator()).getBonds().get(bondA.getEndpoint());
    OspSimulator ospSimulatorA = simulators.get(bondA.getSimulator());
    
    ConnectionEndpoint bondB = bondConnection.getBond().get(1);
    OspBond ospBondB = simulators.get(bondB.getSimulator()).getBonds().get(bondB.getEndpoint());
    OspSimulator ospSimulatorB = simulators.get(bondB.getSimulator());
    
    return new OspBondConnection(ospSimulatorA, ospBondA, ospSimulatorB, ospBondB);
  }
}
