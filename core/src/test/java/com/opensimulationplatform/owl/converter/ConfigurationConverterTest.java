package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.json.model.parsing.ConfigurationJsonFileParser;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import com.opensimulationplatform.validator.model.configuration.Configuration;
import com.opensimulationplatform.validator.model.configuration.ConfigurationFactory;
import com.opensimulationplatform.validator.model.modeldefinition.*;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ConfigurationConverterTest {
  @Test
  public void canConvert() {
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(new File("./src/test/resources/validator/cse-config-valid.json")));
    OwlConfiguration owlConfiguration = ConfigurationConverter.convert(config, new File("./src/main/resources/osp.owl"));
    
    owlConfiguration.getSimulators().forEach((individual, simulator) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      assertTrue(simulator.getName() + " not found in config", simulators.containsValue(simulator));
    });
    
    owlConfiguration.getVariables().forEach((individual, variable) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Variable> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getVariables();
      Map<String, Variable> craneController = simulators.get("CraneController").getVariables();
      assertTrue(variable.getName() + " not found in config", knuckleBoomCrane.containsValue(variable) || craneController.containsValue(variable));
    });
    
    owlConfiguration.getPlugs().forEach((individual, plug) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Plug> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getPlugs();
      Map<String, Plug> craneController = simulators.get("CraneController").getPlugs();
      assertTrue(plug.getName() + " not found in config", knuckleBoomCrane.containsValue(plug) || craneController.containsValue(plug));
    });
    
    owlConfiguration.getSockets().forEach((individual, socket) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Socket> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getSockets();
      Map<String, Socket> craneController = simulators.get("CraneController").getSockets();
      assertTrue(socket.getName() + " not found in config", knuckleBoomCrane.containsValue(socket) || craneController.containsValue(socket));
    });
    
    owlConfiguration.getBonds().forEach((individual, bond) -> {
      Map<String, Simulator> simulators = config.getSimulators();
      Map<String, Bond> knuckleBoomCrane = simulators.get("KnuckleBoomCrane").getBonds();
      Map<String, Bond> craneController = simulators.get("CraneController").getBonds();
      assertTrue(bond.getName() + " not found in config", knuckleBoomCrane.containsValue(bond) || craneController.containsValue(bond));
    });
  }
}