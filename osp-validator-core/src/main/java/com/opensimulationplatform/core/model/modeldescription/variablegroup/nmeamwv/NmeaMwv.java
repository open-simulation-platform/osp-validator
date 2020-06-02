/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeamwv;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection.NmeaWindDirection;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed.NmeaWindSpeed;

import java.util.Arrays;
import java.util.List;

public class NmeaMwv extends FixedSizeVariableGroup {

  private NmeaWindDirection nmeaWindDirection;
  private NmeaWindSpeed nmeaWindSpeed;
  private NmeaStatus nmeaStatus;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(nmeaWindDirection, nmeaWindSpeed, nmeaStatus);
  }

  public NmeaWindDirection getNmeaWindDirection() {
    return nmeaWindDirection;
  }

  public void setNmeaWindDirection(NmeaWindDirection nmeaWindDirection) {
    this.nmeaWindDirection = nmeaWindDirection;
  }

  public NmeaWindSpeed getNmeaWindSpeed() {
    return nmeaWindSpeed;
  }

  public void setNmeaWindSpeed(NmeaWindSpeed nmeaWindSpeed) {
    this.nmeaWindSpeed = nmeaWindSpeed;
  }

  public NmeaStatus getNmeaStatus() {
    return nmeaStatus;
  }

  public void setNmeaStatus(NmeaStatus nmeaStatus) {
    this.nmeaStatus = nmeaStatus;
  }
}
