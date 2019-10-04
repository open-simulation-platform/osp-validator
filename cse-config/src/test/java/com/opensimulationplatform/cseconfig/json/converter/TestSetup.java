package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.cseconfig.json.model.*;
import com.opensimulationplatform.modeldescription.json.model.Bond;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import com.opensimulationplatform.modeldescription.json.model.Socket;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

class TestSetup {
  Plug plugA;
  Plug plugB;
  Plug plugC;
  Plug plugD;
  OspModelDescription ospModelDescription;
  Socket socketA;
  Socket socketB;
  Socket socketC;
  Socket socketD;
  Bond bondA;
  Bond bondB;
  OspSystemStructure ospSystemStructure;
  Simulator simulatorA;
  Simulator simulatorB;
  BondConnection bondConnection;
  PlugSocketConnection plugSocketConnectionA;
  PlugSocketConnection plugSocketConnectionB;
  VariableConnection variableConnectionA;
  VariableConnection variableConnectionB;
  
  TestSetup() {
    ospModelDescription = new OspModelDescription();
    ospModelDescription.setName("name");
    
    plugA = new Plug();
    plugA.setType("typeA");
    plugA.setName("plugA");
    plugA.setVariables(asList("variable1", "variable2"));
    
    plugB = new Plug();
    plugB.setType("typeB");
    plugB.setName("plugB");
    plugB.setVariables(asList("variable3", "variable4"));
  
    plugC = new Plug();
    plugC.setType("typeC");
    plugC.setName("plugC");
    plugC.setVariables(asList("variable5", "variable6"));
  
    plugD = new Plug();
    plugD.setType("typeD");
    plugD.setName("plugD");
    plugD.setVariables(asList("variable7", "variable8"));
    
    ospModelDescription.setPlugs(asList(plugA, plugB, plugC, plugD));
    
    socketA = new Socket();
    socketA.setType("typeA");
    socketA.setName("socketA");
    socketA.setVariables(asList("variable9", "variable10"));
    
    socketB = new Socket();
    socketB.setType("typeB");
    socketB.setName("socketB");
    socketB.setVariables(asList("variable11", "variable12"));
  
    socketC = new Socket();
    socketC.setType("typeC");
    socketC.setName("socketC");
    socketC.setVariables(asList("variable13", "variable14"));
  
    socketD = new Socket();
    socketD.setType("typeD");
    socketD.setName("socketD");
    socketD.setVariables(asList("variable15", "variable16"));
    
    ospModelDescription.setSockets(asList(socketA, socketB, socketC, socketD));
    
    bondA = new Bond();
    bondA.setName("bondA");
    bondA.setPlugs(singletonList(plugA.getName()));
    bondA.setSockets(singletonList(socketB.getName()));
    
    bondB = new Bond();
    bondB.setName("bondB");
    bondB.setPlugs(singletonList(plugB.getName()));
    bondB.setSockets(singletonList(socketA.getName()));
    
    ospModelDescription.setBonds(asList(bondA, bondB));
    
    ospSystemStructure = new OspSystemStructure();
    
    simulatorA = new Simulator();
    simulatorA.setName("simulatorA");
    simulatorA.setSource("source");
    simulatorA.setModelDefinition("modelDefinition");
    
    simulatorB = new Simulator();
    simulatorB.setName("simulatorB");
    simulatorB.setSource("source");
    simulatorB.setModelDefinition("modelDefinition");
    
    ospSystemStructure.setSimulators(asList(simulatorA, simulatorB));
    
    bondConnection = new BondConnection();
    bondConnection.setBondA(bondA.getName());
    bondConnection.setBondB(bondB.getName());
    bondConnection.setSimulatorA(simulatorA.getName());
    bondConnection.setSimulatorB(simulatorB.getName());
    
    ospSystemStructure.setBondConnections(singletonList(bondConnection));
    
    plugSocketConnectionA = new PlugSocketConnection();
    plugSocketConnectionA.setPlug(plugC.getName());
    plugSocketConnectionA.setSocket(socketC.getName());
    plugSocketConnectionA.setSourceSimulator(simulatorA.getName());
    plugSocketConnectionA.setTargetSimulator(simulatorB.getName());
    
    plugSocketConnectionB = new PlugSocketConnection();
    plugSocketConnectionB.setPlug(plugD.getName());
    plugSocketConnectionB.setSocket(socketD.getName());
    plugSocketConnectionB.setSourceSimulator(simulatorB.getName());
    plugSocketConnectionB.setTargetSimulator(simulatorA.getName());
    
    ospSystemStructure.setPlugSocketConnections(asList(plugSocketConnectionA, plugSocketConnectionB));
    
    variableConnectionA = new VariableConnection();
    variableConnectionA.setTargetVariable("variable17");
    variableConnectionA.setTargetSimulator(simulatorA.getName());
    variableConnectionA.setSourceVariable("variable18");
    variableConnectionA.setSourceSimulator(simulatorB.getName());
    
    variableConnectionB = new VariableConnection();
    variableConnectionB.setTargetVariable("variable19");
    variableConnectionB.setTargetSimulator(simulatorA.getName());
    variableConnectionB.setSourceVariable("variable20");
    variableConnectionB.setSourceSimulator(simulatorB.getName());
    
    ospSystemStructure.setVariableConnections(asList(variableConnectionA, variableConnectionB));
  }
}