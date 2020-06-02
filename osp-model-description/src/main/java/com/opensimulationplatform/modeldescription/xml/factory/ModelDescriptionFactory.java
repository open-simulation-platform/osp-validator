/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.ospmodeldescriptiontype.OspModelDescriptionTypeConverter;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;

import java.io.File;
import java.net.URI;

public class ModelDescriptionFactory {
  public ModelDescription create(File ospModelDescription, URI fmu) {
    OspModelDescriptionParser parser = new OspModelDescriptionParser();
    OspModelDescriptionType ospModelDescriptionType = parser.parse(ospModelDescription);
    OspModelDescriptionTypeConverter converter = new OspModelDescriptionTypeConverter(fmu);
    return converter.convert(ospModelDescriptionType);
  }

  public ModelDescription create(URI fmu) {
    OspModelDescriptionType ospModelDescriptionType = new OspModelDescriptionType();
    OspModelDescriptionTypeConverter converter = new OspModelDescriptionTypeConverter(fmu);
    return converter.convert(ospModelDescriptionType);
  }
}
