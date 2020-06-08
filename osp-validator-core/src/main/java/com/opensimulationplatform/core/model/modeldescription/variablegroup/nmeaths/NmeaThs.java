/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaths;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatrueheading.NmeaTrueHeading;

import java.util.Arrays;
import java.util.List;

public class NmeaThs extends FixedSizeVariableGroup {

  private NmeaTrueHeading nmeaTrueHeading;
  private NmeaStatus nmeaStatus;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(nmeaTrueHeading, nmeaStatus);
  }

  public NmeaTrueHeading getNmeaTrueHeading() {
    return nmeaTrueHeading;
  }

  public void setNmeaTrueHeading(NmeaTrueHeading nmeaTrueHeading) {
    this.nmeaTrueHeading = nmeaTrueHeading;
  }

  public NmeaStatus getNmeaStatus() {
    return nmeaStatus;
  }

  public void setNmeaStatus(NmeaStatus nmeaStatus) {
    this.nmeaStatus = nmeaStatus;
  }
}
