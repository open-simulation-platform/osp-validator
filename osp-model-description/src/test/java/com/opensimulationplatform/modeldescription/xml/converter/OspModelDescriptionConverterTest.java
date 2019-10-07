package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.xml.model.Bonds;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.model.Plugs;
import com.opensimulationplatform.modeldescription.xml.model.Sockets;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspModelDescriptionConverterTest {
  @Test
  public void canConvert() {
    OspModelDescription ospModelDescription = TestSetup.getTestOspModelDescription();
    
    ModelDescription modelDescription = OspModelDescriptionConverter.convert(ospModelDescription);
  
    List<Plugs.Plug> plugs = ospModelDescription.getPlugs().getPlug();
    Map<String, OspPlug> ospPlugs = modelDescription.getOspPlugs();
    assertEquals(plugs.size(), ospPlugs.size());
    for (Plugs.Plug plug : plugs) {
      OspPlug ospPlug = ospPlugs.get(plug.getName());
      assertEquals(plug.getType(), ospPlug.getType());
      assertEquals(plug.getName(), ospPlug.getName());
      plug.getVariables().getVariable().forEach(v -> assertTrue(ospPlug.getVariables().containsKey(v.getName())));
    }
  
    List<Sockets.Socket> sockets = ospModelDescription.getSockets().getSocket();
    Map<String, OspSocket> ospSockets = modelDescription.getOspSockets();
    assertEquals(sockets.size(), ospSockets.size());
    for (Sockets.Socket socket : sockets) {
      OspSocket ospSocket = ospSockets.get(socket.getName());
      assertEquals(socket.getType(), ospSocket.getType());
      assertEquals(socket.getName(), ospSocket.getName());
      socket.getVariables().getVariable().forEach(v -> assertTrue(ospSocket.getVariables().containsKey(v.getName())));
    }
  
    List<Bonds.Bond> bonds = ospModelDescription.getBonds().getBond();
    Map<String, OspBond> ospBonds = modelDescription.getOspBonds();
    assertEquals(bonds.size(), ospBonds.size());
    for (Bonds.Bond bond : bonds) {
      OspBond ospBond = ospBonds.get(bond.getName());
      assertEquals(bond.getName(), ospBond.getName());
    
      List<Bonds.Bond.BondPlugs.BondPlug> bondPlugs = bond.getBondPlugs().getBondPlug();
      List<OspPlug> ospBondPlugs = ospBond.getOspPlugs();
      assertEquals(bondPlugs.size(), ospBondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        assertEquals(bondPlugs.get(j).getName(), ospBondPlugs.get(j).getName());
      }
    
      List<Bonds.Bond.BondSockets.BondSocket> bondSockets = bond.getBondSockets().getBondSocket();
      List<OspSocket> ospBondSockets = ospBond.getOspSockets();
      assertEquals(bondSockets.size(), ospBondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        assertEquals(bondSockets.get(j).getName(), ospBondSockets.get(j).getName());
      }
    }
  }
}