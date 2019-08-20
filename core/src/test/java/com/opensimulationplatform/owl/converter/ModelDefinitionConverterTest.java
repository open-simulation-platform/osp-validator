package com.opensimulationplatform.owl.converter;

import org.junit.Test;

public class ModelDefinitionConverterTest {
  @Test
  public void canConvert() {
/*    ModelDefinition config = ModelDefinitionFactory.create(ModelDefinitionJsonFileParser.parse(new File("./src/test/resources/validator/cse-config-valid.json")));
    OwlModelDefinition owlModelDefinition = ModelDefinitionConverter.convert(config, new File("./src/main/resources/osp.owl"));
    
    owlModelDefinition.getSimulators().forEach((individual, simulator) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      assertTrue(simulator.getName() + " not found in config", simulators.containsValue(simulator));
    });
    
    owlModelDefinition.getVariables().forEach((individual, variable) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Variable> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getVariables();
      Map<String, Variable> craneController = simulators.get("CraneController").getVariables();
      assertTrue(variable.getName() + " not found in config", knuckleBoomCrane.containsValue(variable) || craneController.containsValue(variable));
    });
    
    owlModelDefinition.getPlugs().forEach((individual, plug) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Plug> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getPlugs();
      Map<String, Plug> craneController = simulators.get("CraneController").getPlugs();
      assertTrue(plug.getName() + " not found in config", knuckleBoomCrane.containsValue(plug) || craneController.containsValue(plug));
    });
    
    owlModelDefinition.getSockets().forEach((individual, socket) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Socket> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getSockets();
      Map<String, Socket> craneController = simulators.get("CraneController").getSockets();
      assertTrue(socket.getName() + " not found in config", knuckleBoomCrane.containsValue(socket) || craneController.containsValue(socket));
    });
    
    owlModelDefinition.getBonds().forEach((individual, bond) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Bond> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getBonds();
      Map<String, Bond> craneController = simulators.get("CraneController").getBonds();
      assertTrue(bond.getName() + " not found in config", knuckleBoomCrane.containsValue(bond) || craneController.containsValue(bond));
    });*/
  }
  
}
