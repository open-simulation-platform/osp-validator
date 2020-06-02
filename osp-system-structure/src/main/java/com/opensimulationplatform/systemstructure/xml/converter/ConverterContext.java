/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.systemstructure.util.FmuLocator;
import com.opensimulationplatform.systemstructure.util.OspModelDescriptionLocator;

public class ConverterContext {

  public SystemStructure systemStructure;

  public FmuLocator fmuLocator;
  public OspModelDescriptionLocator ospModelDescriptionLocator;

  public SimulatorConverter simulatorConverter;
  public VariableConnectionConverter variableConnectionConverter;
  public VariableGroupConnectionConverter variableGroupConnectionConverter;

  public ConverterContext() {
    this.systemStructure = new SystemStructure();

    this.simulatorConverter = new SimulatorConverter(this);
    this.variableConnectionConverter = new VariableConnectionConverter(this);
    this.variableGroupConnectionConverter = new VariableGroupConnectionConverter(this);
  }
}
