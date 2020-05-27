package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGgaLatitudeLongitudeType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class NmeaGgaLongitudeLatitudeTypeConverter extends Converter<NmeaGgaLatitudeLongitudeType, NmeaGgaLatitudeLongitude> {
  @Override
  public NmeaGgaLatitudeLongitude convert(NmeaGgaLatitudeLongitudeType nmeaGgaLatitudeLongitudeType) {
    NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude = new NmeaGgaLatitudeLongitude();

    nmeaGgaLatitudeLongitude.setName(nmeaGgaLatitudeLongitudeType.getName());
    List<VariableType> variableTypes = nmeaGgaLatitudeLongitudeType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    nmeaGgaLatitudeLongitude.setVariables(variables);

    return nmeaGgaLatitudeLongitude;

  }
}
