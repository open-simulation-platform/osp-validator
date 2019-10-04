package com.opensimulationplatform.modeldescription.xml.converter;


import com.opensimulationplatform.modeldescription.xml.model.*;

public class TestSetup {
  
  static OspModelDescription getTestOspModelDescription() {
    OspModelDescription ospModelDescription = new OspModelDescription();
  
    Plugs plugs = new Plugs();
    
    Plugs.Plug p1 = new Plugs.Plug();
    p1.setType("typeA");
    p1.setName("plug1");
    Variables variables = new Variables();
    Variables.Variable v = new Variables.Variable();
    v.setName("variable1");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable2");
    variables.getVariable().add(v);
    p1.setVariables(variables);
    plugs.getPlug().add(p1);
  
    Plugs.Plug p2 = new Plugs.Plug();
    p2.setType("typeB");
    p2.setName("plug2");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable3");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable4");
    variables.getVariable().add(v);
    p2.setVariables(variables);
    plugs.getPlug().add(p2);
    
    ospModelDescription.setPlugs(plugs);
  
    Sockets sockets = new Sockets();
  
    Sockets.Socket s1 = new Sockets.Socket();
    s1.setType("typeA");
    s1.setName("socket1");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable5");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable6");
    variables.getVariable().add(v);
    s1.setVariables(variables);
    sockets.getSocket().add(s1);
  
    Sockets.Socket s2 = new Sockets.Socket();
    s2.setType("typeB");
    s2.setName("socket2");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable7");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable8");
    variables.getVariable().add(v);
    s2.setVariables(variables);
    sockets.getSocket().add(s2);
    
    ospModelDescription.setSockets(sockets);
  
    Bonds bonds = new Bonds();
    
    Bonds.Bond bond1 = new Bonds.Bond();
    bond1.setName("bond1");
    Bonds.Bond.BondPlugs bondPlugs = new Bonds.Bond.BondPlugs();
    Bonds.Bond.BondPlugs.BondPlug bondPlug = new Bonds.Bond.BondPlugs.BondPlug();
    bondPlug.setName(p1.getName());
    bondPlugs.getBondPlug().add(bondPlug);
    bond1.setBondPlugs(bondPlugs);
    
    Bonds.Bond.BondSockets bondSockets = new Bonds.Bond.BondSockets();
    Bonds.Bond.BondSockets.BondSocket bondSocket = new Bonds.Bond.BondSockets.BondSocket();
    bondSocket.setName(s1.getName());
    bondSockets.getBondSocket().add(bondSocket);
    bond1.setBondSockets(bondSockets);
    
    bonds.getBond().add(bond1);
  
    Bonds.Bond bond2 = new Bonds.Bond();
    bond2.setName("bond2");
    bondPlugs = new Bonds.Bond.BondPlugs();
    bondPlug = new Bonds.Bond.BondPlugs.BondPlug();
    bondPlug.setName(p2.getName());
    bondPlugs.getBondPlug().add(bondPlug);
    bond2.setBondPlugs(bondPlugs);
  
    bondSockets = new Bonds.Bond.BondSockets();
    bondSocket = new Bonds.Bond.BondSockets.BondSocket();
    bondSocket.setName(s2.getName());
    bondSockets.getBondSocket().add(bondSocket);
    bond2.setBondSockets(bondSockets);
  
    bonds.getBond().add(bond2);
    
    ospModelDescription.setBonds(bonds);
    
    return ospModelDescription;
  }
}
