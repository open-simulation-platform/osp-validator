package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagga;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NmeaGga extends FixedSizeVariableGroup {

  private NmeaTime nmeaTime;
  private NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude;
  private NmeaGgaFix nmeaGgaFix;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(nmeaTime, nmeaGgaLatitudeLongitude, nmeaGgaFix));
  }

  public NmeaTime getNmeaTime() {
    return nmeaTime;
  }

  public void setNmeaTime(NmeaTime nmeaTime) {
    this.nmeaTime = nmeaTime;
  }

  public NmeaGgaLatitudeLongitude getNmeaGgaLatitudeLongitude() {
    return nmeaGgaLatitudeLongitude;
  }

  public void setNmeaGgaLatitudeLongitude(NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude) {
    this.nmeaGgaLatitudeLongitude = nmeaGgaLatitudeLongitude;
  }

  public NmeaGgaFix getNmeaGgaFix() {
    return nmeaGgaFix;
  }

  public void setNmeaGgaFix(NmeaGgaFix nmeaGgaFix) {
    this.nmeaGgaFix = nmeaGgaFix;
  }
}
