/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.ID;

import java.util.ArrayList;
import java.util.List;

public class SystemStructure {
  private final ID id = new ID();
  private final List<Simulator> simulators = new ArrayList<>();
  private final List<Function> functions = new ArrayList<>();
  private final List<VariableConnection> variableConnections = new ArrayList<>();
  private final List<VariableGroupConnection> variableGroupConnections = new ArrayList<>();
  private final List<SignalConnection> signalConnections = new ArrayList<>();
  private final List<SignalGroupConnection> signalGroupConnections = new ArrayList<>();

  public ID getId() {
    return id;
  }

  public List<Simulator> getSimulators() {
    return simulators;
  }

  public List<Function> getFunctions() {
    return functions;
  }

  public List<VariableConnection> getVariableConnections() {
    return variableConnections;
  }

  public List<VariableGroupConnection> getVariableGroupConnections() {
    return variableGroupConnections;
  }

  public List<SignalConnection> getSignalConnections() {
    return signalConnections;
  }

  public List<SignalGroupConnection> getSignalGroupConnections() {
    return signalGroupConnections;
  }
}
