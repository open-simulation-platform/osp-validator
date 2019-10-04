package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.json.model.BondConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BondConnectionConverter {
  public static OspBondConnection convert(BondConnection bondConnection, List<OspSimulator> ospSimulators) {
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    OspSimulator ospSimulatorA = ospSimulatorMap.get(bondConnection.getSimulatorA());
    OspBond ospBondA = ospSimulatorA.getBonds().get(bondConnection.getBondA());
    OspSimulator ospSimulatorB = ospSimulatorMap.get(bondConnection.getSimulatorB());
    OspBond ospBondB = ospSimulatorB.getBonds().get(bondConnection.getBondB());
  
    return new OspBondConnection(ospSimulatorA, ospBondA, ospSimulatorB, ospBondB);
  }
}
