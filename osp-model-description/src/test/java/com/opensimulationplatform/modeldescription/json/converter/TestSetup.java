package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.modeldescription.json.model.Bond;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import com.opensimulationplatform.modeldescription.json.model.Socket;

import java.util.Arrays;
import java.util.Collections;

class TestSetup {
  static OspModelDescription getTestOspModelDescription() {
    OspModelDescription ospModelDescription = new OspModelDescription();
    ospModelDescription.setName("name");
    
    Plug plug1 = new Plug();
    plug1.setType("typeA");
    plug1.setName("plug1");
    plug1.setVariables(Arrays.asList("variable1", "variable2"));
    
    Plug plug2 = new Plug();
    plug2.setType("typeB");
    plug2.setName("plug2");
    plug2.setVariables(Arrays.asList("variable3", "variable4"));
    
    ospModelDescription.setPlugs(Arrays.asList(plug1, plug2));
    
    Socket socket1 = new Socket();
    socket1.setType("typeA");
    socket1.setName("socket1");
    socket1.setVariables(Arrays.asList("variable5", "variable6"));
    
    Socket socket2 = new Socket();
    socket2.setType("typeB");
    socket2.setName("socket2");
    socket2.setVariables(Arrays.asList("variable7", "variable8"));
    
    ospModelDescription.setSockets(Arrays.asList(socket1, socket2));
    
    Bond bond1 = new Bond();
    bond1.setName("bond1");
    bond1.setSockets(Collections.singletonList("socket1"));
    bond1.setPlugs(Collections.singletonList("plug1"));
    
    Bond bond2 = new Bond();
    bond2.setName("bond2");
    bond2.setSockets(Collections.singletonList("socket2"));
    bond2.setPlugs(Collections.singletonList("plug2"));
    
    ospModelDescription.setBonds(Arrays.asList(bond1, bond2));
    return ospModelDescription;
  }
}