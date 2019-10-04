package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.json.model.Bond;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import com.opensimulationplatform.modeldescription.json.model.Socket;
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
  
    assertEquals(ospModelDescription.getName(), modelDescription.getName());
    
    List<Plug> plugs = ospModelDescription.getPlugs();
    Map<String, OspPlug> ospPlugs = modelDescription.getOspPlugs();
    assertEquals(plugs.size(), ospPlugs.size());
    for (Plug plug : plugs) {
      OspPlug ospPlug = ospPlugs.get(plug.getName());
      assertEquals(plug.getType(), ospPlug.getType());
      assertEquals(plug.getName(), ospPlug.getName());
      plug.getVariables().forEach(v -> assertTrue(ospPlug.getVariables().containsKey(v)));
    }
  
    List<Socket> sockets = ospModelDescription.getSockets();
    Map<String, OspSocket> ospSockets = modelDescription.getOspSockets();
    assertEquals(sockets.size(), ospSockets.size());
    for (Socket socket : sockets) {
      OspSocket ospSocket = ospSockets.get(socket.getName());
      assertEquals(socket.getType(), ospSocket.getType());
      assertEquals(socket.getName(), ospSocket.getName());
      socket.getVariables().forEach(v -> assertTrue(ospSocket.getVariables().containsKey(v)));
    }
  
    List<Bond> bonds = ospModelDescription.getBonds();
    Map<String, OspBond> ospBonds = modelDescription.getOspBonds();
    assertEquals(bonds.size(), ospBonds.size());
    for (Bond bond : bonds) {
      OspBond ospBond = ospBonds.get(bond.getName());
      assertEquals(bond.getName(), ospBond.getName());
    
      List<String> bondPlugs = bond.getPlugs();
      List<OspPlug> ospBondPlugs = ospBond.getOspPlugs();
      assertEquals(bondPlugs.size(), ospBondPlugs.size());
      for (int j = 0; j < bondPlugs.size(); j++) {
        assertEquals(bondPlugs.get(j), ospBondPlugs.get(j).getName());
      }
    
      List<String> bondSockets = bond.getSockets();
      List<OspSocket> ospBondSockets = ospBond.getOspSockets();
      assertEquals(bondSockets.size(), ospBondSockets.size());
      for (int j = 0; j < bondSockets.size(); j++) {
        assertEquals(bondSockets.get(j), ospBondSockets.get(j).getName());
      }
    }
  }
}