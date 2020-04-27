package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;

import java.util.List;
import java.util.Optional;

public class SystemStructureUtil {
  public static Simulator getSimulatorByName(SystemStructure systemStructure, String name) {
    List<Simulator> simulators = systemStructure.getSimulators();
    Optional<Simulator> optional = simulators.stream().filter(simulator -> simulator.getName().get().equals(name)).findAny();
    return optional.orElse(null);
  }
}
