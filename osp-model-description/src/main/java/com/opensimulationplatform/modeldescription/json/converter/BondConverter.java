package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.json.model.Bond;

import java.util.Map;

public class BondConverter {
  public static OspBond convert(Bond bond, Map<String, OspPlug> ospPlugs, Map<String, OspSocket> ospSockets) {
    OspBond ospBond = new OspBond(bond.getName());
    
    bond.getPlugs().forEach(p -> ospBond.addPlug(ospPlugs.get(p)));
    bond.getSockets().forEach(s -> ospBond.addSocket(ospSockets.get(s)));
    
    return ospBond;
  }
}
