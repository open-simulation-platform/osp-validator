package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaStatusType;

import java.util.Collections;

public class NmeaStatusTypeConverter extends Converter<NmeaStatusType, NmeaStatus> {
  @Override
  public NmeaStatus convert(NmeaStatusType nmeaStatusType) {
    NmeaStatus nmeaStatus = new NmeaStatus();

    nmeaStatus.setName(nmeaStatusType.getName());
    Variable variable = context.variableTypeConverter.convert(nmeaStatusType.getVariable());
    nmeaStatus.setVariables(Collections.singletonList(variable));

    return nmeaStatus;
  }
}
