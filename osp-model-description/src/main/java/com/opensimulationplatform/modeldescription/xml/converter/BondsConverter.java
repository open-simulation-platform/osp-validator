package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.xml.model.Bonds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BondsConverter {
  public static List<OspBond> convert(Bonds bonds, Map<String, OspPlug> ospPlugs, Map<String, OspSocket> ospSockets) {
    List<OspBond> ospBonds = new ArrayList<>();
    
    bonds.getBond().forEach(b -> {
      OspBond ospBond = new OspBond(b.getName());
      b.getBondPlugs().getBondPlug().forEach(p -> ospBond.addPlug(ospPlugs.get(p.getName())));
      b.getBondSockets().getBondSocket().forEach(s -> ospBond.addSocket(ospSockets.get(s.getName())));
      ospBonds.add(ospBond);
    });
    
    return ospBonds;
  }
}
