/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.util;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.util.FmiModelDescriptionUtil;

public class FmuHelper_Fmi1 {
	
	private static final Logger LOG = LoggerFactory.getLogger(FmuHelper_Fmi1.class);

	public static FmiModelDescription getFmiModelDescription(URI fmu) {
		try {
			String fmiModelDescriptionXml = FmiModelDescriptionUtil.extractModelDescriptionXml(fmu.toURL());
			return FmiModelDescription.fromXml(fmiModelDescriptionXml);
		} catch (Exception e) {
			String message = "Error trying to extract modelDescription.xml from " + fmu;
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
}
