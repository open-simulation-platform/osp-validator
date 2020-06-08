/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.systemstructure;


import java.util.List;

public class Function {
  private String name;
  private List<Parameter> parameters;
  private List<Signal> signals;
  private List<SignalGroup> signalGroups;
}
