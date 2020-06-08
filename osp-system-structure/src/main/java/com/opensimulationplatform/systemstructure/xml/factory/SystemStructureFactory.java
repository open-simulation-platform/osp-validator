/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.factory;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.systemstructure.util.DefaultFmuLocator;
import com.opensimulationplatform.systemstructure.util.DefaultOspModelDescriptionLocator;
import com.opensimulationplatform.systemstructure.util.FmuLocator;
import com.opensimulationplatform.systemstructure.util.OspModelDescriptionLocator;
import com.opensimulationplatform.systemstructure.xml.converter.ConverterContext;
import com.opensimulationplatform.systemstructure.xml.converter.OspSystemStructureConverter;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.parser.OspSystemStructureParser;

import java.io.File;

public class SystemStructureFactory {

  private FmuLocator fmuLocator;
  private OspModelDescriptionLocator ospModelDescriptionLocator;

  public SystemStructure create(File ospSystemStructureFile) {
    OspSystemStructureParser parser = new OspSystemStructureParser();
    OspSystemStructure ospSystemStructure = parser.parse(ospSystemStructureFile);

    if (fmuLocator == null) {
      fmuLocator = new DefaultFmuLocator(ospSystemStructureFile);
    }

    if (ospModelDescriptionLocator == null) {
      ospModelDescriptionLocator = new DefaultOspModelDescriptionLocator(ospSystemStructureFile, fmuLocator);
    }

    ConverterContext context = new ConverterContext();
    context.ospModelDescriptionLocator = ospModelDescriptionLocator;
    context.fmuLocator = fmuLocator;
    OspSystemStructureConverter converter = new OspSystemStructureConverter(context);

    return converter.convert(ospSystemStructure);
  }

  public void setFmuLocator(FmuLocator fmuLocator) {
    this.fmuLocator = fmuLocator;
  }

  public void setOspModelDescriptionLocator(OspModelDescriptionLocator ospModelDescriptionLocator) {
    this.ospModelDescriptionLocator = ospModelDescriptionLocator;
  }
}
