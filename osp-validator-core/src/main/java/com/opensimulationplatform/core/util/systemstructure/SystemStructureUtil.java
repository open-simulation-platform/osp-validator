/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.util.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;

import java.util.List;
import java.util.Optional;

public class SystemStructureUtil {
  public static Optional<VariableGroup> getParentVariableGroup(SystemStructure systemStructure, Variable variable) {
    List<Simulator> simulators = systemStructure.getSimulators();
    for (Simulator simulator : simulators) {
      for (VariableGroup variableGroup : simulator.getModelDescription().getVariableGroups()) {
        if (variableGroup.getVariables().contains(variable)) {
          return Optional.of(variableGroup);
        }
      }
    }
    return Optional.empty();
  }

  public static Optional<VariableGroup> getParentVariableGroup(SystemStructure systemStructure, VariableGroup variableGroup) {
    List<Simulator> simulators = systemStructure.getSimulators();
    for (Simulator simulator : simulators) {
      for (VariableGroup vg : simulator.getModelDescription().getVariableGroups()) {
        if (vg.getVariableGroups().contains(variableGroup)) {
          return Optional.of(vg);
        }
      }
    }
    return Optional.empty();
  }

  public static Optional<Simulator> getParentSimulator(SystemStructure systemStructure, Variable variable) {
    List<Simulator> simulators = systemStructure.getSimulators();
    for (Simulator simulator : simulators) {
      if (simulator.getModelDescription().getVariables().contains(variable)) {
        return Optional.of(simulator);
      }
    }
    return Optional.empty();
  }

  public static Optional<Simulator> getParentSimulator(SystemStructure systemStructure, VariableGroup variableGroup) {
    List<Simulator> simulators = systemStructure.getSimulators();
    for (Simulator simulator : simulators) {
      if (simulator.getModelDescription().getVariableGroups().contains(variableGroup)) {
        return Optional.of(simulator);
      }
    }
    return Optional.empty();
  }

  public static Optional<VariableGroupConnection> getParentVariableGroupConnection(SystemStructure systemStructure, VariableConnection variableConnection) {
    for (VariableGroupConnection variableGroupConnection : systemStructure.getVariableGroupConnections()) {
      List<Variable> variablesA = variableGroupConnection.getVariableGroupA().getVariables();
      List<Variable> variablesB = variableGroupConnection.getVariableGroupB().getVariables();

      Variable vcVarA = variableConnection.getVariableA();
      Variable vcVarB = variableConnection.getVariableB();

      for (int i = 0; i < variablesA.size(); i++) {
        Variable vgcVarA = variablesA.get(i);
        Variable vgcVarB = variablesB.get(i);

        if (vcVarA.equals(vgcVarA) && vcVarB.equals(vgcVarB)) {
          return Optional.of(variableGroupConnection);
        }
      }
    }

    return Optional.empty();
  }

  public static Optional<Simulator> getSimulatorByName(SystemStructure systemStructure, String name) {
    List<Simulator> simulators = systemStructure.getSimulators();
    return simulators.stream().filter(simulator -> simulator.getName().get().equals(name)).findAny();
  }

  public static Optional<VariableConnection> getVariableConnectionByPartNames(SystemStructure systemStructure, String simulatorAName, String variableAName, String simulatorBName, String variableBName) {
    List<VariableConnection> variableConnections = systemStructure.getVariableConnections();

    for (VariableConnection variableConnection : variableConnections) {
      Simulator simulatorA = variableConnection.getSimulatorA();
      Variable variableA = variableConnection.getVariableA();
      Simulator simulatorB = variableConnection.getSimulatorB();
      Variable variableB = variableConnection.getVariableB();

      if (simulatorAName.equals(simulatorA.getName().get()) &&
          variableAName.equals(variableA.getName().get()) &&
          simulatorBName.equals(simulatorB.getName().get()) &&
          variableBName.equals(variableB.getName().get())) {
        return Optional.of(variableConnection);
      }
    }

    return Optional.empty();
  }

  public static Optional<VariableGroupConnection> getVariableGroupConnectionByPartNames(SystemStructure systemStructure, String simulatorAName, String variableGroupAName, String simulatorBName, String variableGroupBName) {
    List<VariableGroupConnection> variableGroupConnections = systemStructure.getVariableGroupConnections();

    for (VariableGroupConnection variableGroupConnection : variableGroupConnections) {
      Simulator simulatorA = variableGroupConnection.getSimulatorA();
      VariableGroup variableA = variableGroupConnection.getVariableGroupA();
      Simulator simulatorB = variableGroupConnection.getSimulatorB();
      VariableGroup variableB = variableGroupConnection.getVariableGroupB();

      if (simulatorAName.equals(simulatorA.getName().get()) &&
          variableGroupAName.equals(variableA.getName().get()) &&
          simulatorBName.equals(simulatorB.getName().get()) &&
          variableGroupBName.equals(variableB.getName().get())) {
        return Optional.of(variableGroupConnection);
      }
    }

    return Optional.empty();
  }
}
