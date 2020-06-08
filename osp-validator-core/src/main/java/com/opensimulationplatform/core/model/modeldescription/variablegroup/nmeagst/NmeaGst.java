/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagst;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstellipse.NmeaGstEllipse;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstpositionerror.NmeaGstPositionError;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstrms.NmeaGstRms;

import java.util.Arrays;
import java.util.List;

public class NmeaGst extends FixedSizeVariableGroup {

  private NmeaGstRms nmeaGstRms;
  private NmeaGstEllipse nmeaGstEllipse;
  private NmeaGstPositionError nmeaGstPositionError;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(nmeaGstRms, nmeaGstEllipse, nmeaGstPositionError);
  }

  public NmeaGstRms getNmeaGstRms() {
    return nmeaGstRms;
  }

  public void setNmeaGstRms(NmeaGstRms nmeaGstRms) {
    this.nmeaGstRms = nmeaGstRms;
  }

  public NmeaGstEllipse getNmeaGstEllipse() {
    return nmeaGstEllipse;
  }

  public void setNmeaGstEllipse(NmeaGstEllipse nmeaGstEllipse) {
    this.nmeaGstEllipse = nmeaGstEllipse;
  }

  public NmeaGstPositionError getNmeaGstPositionError() {
    return nmeaGstPositionError;
  }

  public void setNmeaGstPositionError(NmeaGstPositionError nmeaGstPositionError) {
    this.nmeaGstPositionError = nmeaGstPositionError;
  }
}
