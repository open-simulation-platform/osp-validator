/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaStatusType;

import java.util.Collections;

public class NmeaStatusTypeConverter extends Converter<NmeaStatusType, NmeaStatus> {
  public NmeaStatusTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaStatus convert(NmeaStatusType nmeaStatusType) {
    NmeaStatus nmeaStatus = new NmeaStatus();

    nmeaStatus.setName(nmeaStatusType.getName());
    Variable variable = context.variableTypeConverter.convert(nmeaStatusType.getVariable());
    nmeaStatus.setVariables(Collections.singletonList(variable));

    return nmeaStatus;
  }
}
