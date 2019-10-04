package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.json.model.Bond;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BondConverterTest {
  @Test
  public void canConvert() {
    Map<String, OspPlug> ospPlugsMap = new HashMap<>();
    OspPlug p1 = new OspPlug("type", "plugA");
    ospPlugsMap.put(p1.getName(), p1);
    OspPlug p2 = new OspPlug("type", "plugB");
    ospPlugsMap.put(p2.getName(), p2);
    
    Map<String, OspSocket> ospSocketsMap = new HashMap<>();
    OspSocket s1 = new OspSocket("type", "socketA");
    ospSocketsMap.put(s1.getName(), s1);
    OspSocket s2 = new OspSocket("type", "socketB");
    ospSocketsMap.put(s2.getName(), s2);
    
    Bond bond = new Bond();
    bond.setName("name");
    bond.setPlugs(Arrays.asList(p1.getName(), p2.getName()));
    bond.setSockets(Arrays.asList(s1.getName(), s2.getName()));
    
    OspBond ospBond = BondConverter.convert(bond, ospPlugsMap, ospSocketsMap);
    
    assertEquals(bond.getName(), ospBond.getName());
    
    List<String> plugs = bond.getPlugs();
    List<OspPlug> ospPlugs = ospBond.getOspPlugs();
    assertEquals(plugs.size(), ospPlugs.size());
    for (int i = 0; i < plugs.size(); i++) {
      assertEquals(plugs.get(i), ospPlugs.get(i).getName());
    }
    
    List<String> sockets = bond.getSockets();
    List<OspSocket> ospSockets = ospBond.getOspSockets();
    assertEquals(sockets.size(), ospSockets.size());
    for (int i = 0; i < sockets.size(); i++) {
      assertEquals(sockets.get(i), ospSockets.get(i).getName());
    }
  }
}