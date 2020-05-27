package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeasxn.NmeaSxn;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaSxnType;

public class NmeaSxnTypeConverter extends Converter<NmeaSxnType, NmeaSxn> {
  public NmeaSxnTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaSxn convert(NmeaSxnType nmeaSxnType) {
    NmeaSxn nmeaSxn = new NmeaSxn();

    nmeaSxn.setName(nmeaSxnType.getName());

    AngularDisplacement angularDisplacement = context.angularDisplacementTypeConverter.convert(nmeaSxnType.getAngularDisplacement());
    NmeaStatus nmeaStatus = context.nmeaStatusTypeConverter.convert(nmeaSxnType.getNmeaStatus());

    context.modelDescription.getAngularDisplacements().add(angularDisplacement);
    context.modelDescription.getNmeaStatuses().add(nmeaStatus);

    nmeaSxn.setAngularDisplacement(angularDisplacement);
    nmeaSxn.setNmeaStatus(nmeaStatus);

    return nmeaSxn;
  }
}
