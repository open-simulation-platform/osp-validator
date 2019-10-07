package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.cseconfig.xml.model.*;
import com.opensimulationplatform.modeldescription.xml.model.*;

class TestSetup {
  VariableConnections variableConnections;
  PlugSocketConnections plugSocketConnections;
  BondConnections bondConnections;
  Plugs.Plug plugA;
  Plugs.Plug plugB;
  Plugs.Plug plugC;
  Plugs.Plug plugD;
  Sockets.Socket socketA;
  Sockets.Socket socketB;
  Sockets.Socket socketC;
  Sockets.Socket socketD;
  Bonds.Bond bondA;
  Bonds.Bond bondB;
  Simulators.Simulator simulatorA;
  Simulators.Simulator simulatorB;
  BondConnections.BondConnection bondConnection;
  PlugSocketConnections.PlugSocketConnection plugSocketConnectionA;
  PlugSocketConnections.PlugSocketConnection plugSocketConnectionB;
  VariableConnections.ScalarConnection variableConnectionA;
  VariableConnections.ScalarConnection variableConnectionB;
  OspSystemStructure ospSystemStructure;
  OspModelDescription ospModelDescription;
  Simulators simulators;
  Plugs plugs;
  Sockets sockets;
  Bonds bonds;
  Variables variables;
  
  TestSetup() {
    ospModelDescription = new OspModelDescription();
    ospSystemStructure = new OspSystemStructure();
    setupPlugs();
    setupSockets();
    setupBonds();
    setupSimulators();
    setupBondConnections();
    setupPlugSocketConnections();
    setupVariableConnections();
  }
  
  private void setupVariableConnections() {
    variableConnections = new VariableConnections();
    
    variableConnectionA = new VariableConnections.ScalarConnection();
    ConnectionEndpoint ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorA.getName());
    ce.setEndpoint("variable17");
    variableConnectionA.setSource(ce);
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorB.getName());
    ce.setEndpoint("variable17");
    variableConnectionA.setTarget(ce);
    variableConnections.getScalarConnection().add(variableConnectionA);
    
    variableConnectionB = new VariableConnections.ScalarConnection();
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorA.getName());
    ce.setEndpoint("variable18");
    variableConnectionB.setSource(ce);
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorB.getName());
    ce.setEndpoint("variable18");
    variableConnectionB.setTarget(ce);
    variableConnections.getScalarConnection().add(variableConnectionB);
    
    ospSystemStructure.setVariableConnections(variableConnections);
  }
  
  private void setupPlugSocketConnections() {
    plugSocketConnections = new PlugSocketConnections();
    
    plugSocketConnectionA = new PlugSocketConnections.PlugSocketConnection();
    
    ConnectionEndpoint ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorA.getName());
    ce.setEndpoint(plugC.getName());
    plugSocketConnectionA.setSource(ce);
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorB.getName());
    ce.setEndpoint(socketC.getName());
    plugSocketConnectionA.setTarget(ce);
    
    plugSocketConnections.getPlugSocketConnection().add(plugSocketConnectionA);
    
    plugSocketConnectionB = new PlugSocketConnections.PlugSocketConnection();
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorA.getName());
    ce.setEndpoint(plugD.getName());
    plugSocketConnectionB.setSource(ce);
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorB.getName());
    ce.setEndpoint(socketD.getName());
    plugSocketConnectionB.setTarget(ce);
    
    plugSocketConnections.getPlugSocketConnection().add(plugSocketConnectionB);
    
    ospSystemStructure.setPlugSocketConnections(plugSocketConnections);
  }
  
  private void setupBondConnections() {
    bondConnections = new BondConnections();
    
    bondConnection = new BondConnections.BondConnection();
    ConnectionEndpoint ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorA.getName());
    ce.setEndpoint(bondA.getName());
    bondConnection.getBond().add(ce);
    
    ce = new ConnectionEndpoint();
    ce.setSimulator(simulatorB.getName());
    ce.setEndpoint(bondB.getName());
    bondConnection.getBond().add(ce);
    
    ospSystemStructure.setBondConnections(bondConnections);
  }
  
  private void setupSimulators() {
    simulators = new Simulators();
    
    simulatorA = new Simulators.Simulator();
    simulatorA.setName("simulatorA");
    simulatorA.setSource("source");
    simulators.getSimulator().add(simulatorA);
    
    simulatorB = new Simulators.Simulator();
    simulatorB.setName("simulatorB");
    simulatorB.setSource("source");
    simulators.getSimulator().add(simulatorB);
    
    ospSystemStructure.setSimulators(simulators);
  }
  
  private void setupBonds() {
    bonds = new Bonds();
    
    bondA = new Bonds.Bond();
    bondA.setName("bondA");
    Bonds.Bond.BondPlugs bps = new Bonds.Bond.BondPlugs();
    Bonds.Bond.BondPlugs.BondPlug bp = new Bonds.Bond.BondPlugs.BondPlug();
    bp.setName(plugA.getName());
    bps.getBondPlug().add(bp);
    bondA.setBondPlugs(bps);
    Bonds.Bond.BondSockets bss = new Bonds.Bond.BondSockets();
    Bonds.Bond.BondSockets.BondSocket bs = new Bonds.Bond.BondSockets.BondSocket();
    bs.setName(socketA.getName());
    bss.getBondSocket().add(bs);
    bondA.setBondSockets(bss);
    bonds.getBond().add(bondA);
    
    bondB = new Bonds.Bond();
    bondB.setName("bondB");
    bps = new Bonds.Bond.BondPlugs();
    bp = new Bonds.Bond.BondPlugs.BondPlug();
    bp.setName(plugB.getName());
    bps.getBondPlug().add(bp);
    bondB.setBondPlugs(bps);
    bss = new Bonds.Bond.BondSockets();
    bs = new Bonds.Bond.BondSockets.BondSocket();
    bs.setName(socketB.getName());
    bss.getBondSocket().add(bs);
    bondB.setBondSockets(bss);
    bonds.getBond().add(bondB);
    
    ospModelDescription.setBonds(bonds);
  }
  
  private void setupSockets() {
    Variables variables;
    Variables.Variable v;
    
    sockets = new Sockets();
    
    socketA = new Sockets.Socket();
    socketA.setType("typeA");
    socketA.setName("socketA");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable9");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable10");
    variables.getVariable().add(v);
    socketA.setVariables(variables);
    sockets.getSocket().add(socketA);
    
    socketB = new Sockets.Socket();
    socketB.setType("typeB");
    socketB.setName("socketB");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable11");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable12");
    variables.getVariable().add(v);
    socketB.setVariables(variables);
    sockets.getSocket().add(socketB);
    
    socketC = new Sockets.Socket();
    socketC.setType("typeC");
    socketC.setName("socketC");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable13");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable14");
    variables.getVariable().add(v);
    socketC.setVariables(variables);
    sockets.getSocket().add(socketC);
    
    socketD = new Sockets.Socket();
    socketD.setType("typeD");
    socketD.setName("socketD");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable15");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable16");
    variables.getVariable().add(v);
    socketD.setVariables(variables);
    sockets.getSocket().add(socketD);
    
    ospModelDescription.setSockets(sockets);
  }
  
  private void setupPlugs() {
    plugs = new Plugs();
    
    plugA = new Plugs.Plug();
    plugA.setType("typeA");
    plugA.setName("plugA");
    Variables variables = new Variables();
    Variables.Variable v = new Variables.Variable();
    v.setName("variable1");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable2");
    variables.getVariable().add(v);
    plugA.setVariables(variables);
    plugs.getPlug().add(plugA);
    
    plugB = new Plugs.Plug();
    plugB.setType("typeB");
    plugB.setName("plugB");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable3");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable4");
    variables.getVariable().add(v);
    plugB.setVariables(variables);
    plugs.getPlug().add(plugB);
    
    plugC = new Plugs.Plug();
    plugC.setType("typeC");
    plugC.setName("plugC");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable5");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable6");
    variables.getVariable().add(v);
    plugC.setVariables(variables);
    plugs.getPlug().add(plugC);
    
    plugD = new Plugs.Plug();
    plugD.setType("typeD");
    plugD.setName("plugD");
    variables = new Variables();
    v = new Variables.Variable();
    v.setName("variable7");
    variables.getVariable().add(v);
    v = new Variables.Variable();
    v.setName("variable8");
    variables.getVariable().add(v);
    plugD.setVariables(variables);
    plugs.getPlug().add(plugD);
    
    ospModelDescription.setPlugs(plugs);
  }
}