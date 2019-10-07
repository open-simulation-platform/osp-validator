package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.xml.model.Bonds;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BondsConverterTest {
  @Test
  public void canConvert() {
    OspModelDescription ospModelDescription = TestSetup.getTestOspModelDescription();
    Bonds bonds = ospModelDescription.getBonds();
    Map<String, OspPlug> ospPlugsMap = new HashMap<>();
    Map<String, OspSocket> ospSocketsMap = new HashMap<>();
    PlugsConverter.convert(ospModelDescription.getPlugs()).forEach(p -> ospPlugsMap.put(p.getName(), p));
    SocketsConverter.convert(ospModelDescription.getSockets()).forEach(s -> ospSocketsMap.put(s.getName(), s));
    
    List<OspBond> ospBonds = BondsConverter.convert(bonds, ospPlugsMap, ospSocketsMap);
    
    for (int i = 0; i < ospBonds.size(); i++) {
      Bonds.Bond bond = bonds.getBond().get(i);
      OspBond ospBond = ospBonds.get(i);
      
      List<Bonds.Bond.BondPlugs.BondPlug> plugs = bond.getBondPlugs().getBondPlug();
      List<OspPlug> ospPlugs = ospBond.getOspPlugs();
      assertEquals(plugs.size(), ospPlugs.size());
      for (int j = 0; j < plugs.size(); j++) {
        assertEquals(plugs.get(j).getName(), ospPlugs.get(j).getName());
      }
      
      List<Bonds.Bond.BondSockets.BondSocket> sockets = bond.getBondSockets().getBondSocket();
      List<OspSocket> ospSockets = ospBond.getOspSockets();
      assertEquals(sockets.size(), ospSockets.size());
      for (int j = 0; j < sockets.size(); j++) {
        assertEquals(sockets.get(j).getName(), ospSockets.get(j).getName());
      }
    }
  }
}