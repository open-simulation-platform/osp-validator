/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class DefaultFmuLocator implements FmuLocator {
  private final File ospSystemStructureFile;

  public DefaultFmuLocator(File ospSystemStructureFile) {
    this.ospSystemStructureFile = ospSystemStructureFile;
  }

  @Override
  public URI get(Simulators.Simulator simulator) {
    try {
      URI sourceURI = new URI(simulator.getSource());
      if ("proxyfmu".equals(sourceURI.getScheme())) {
        sourceURI = getProxyFmuFileLocationURI(sourceURI);
      }

      if (sourceURI.isAbsolute()) {
        return sourceURI;
      } else {
        URI baseURI = ospSystemStructureFile.toURI();
        return baseURI.resolve(sourceURI);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  private URI getProxyFmuFileLocationURI(URI sourceURI) throws Exception {
    Map<String, List<String>> queryParams = splitQuery(sourceURI);
    if (queryParams.containsKey("file")) {
      return new URI(queryParams.get("file").get(0));
    }
    throw new RuntimeException("Could not locate FMU from URI: " + sourceURI);
  }

  private static Map<String, List<String>> splitQuery(URI uri) throws UnsupportedEncodingException {
    final Map<String, List<String>> queryPairs = new LinkedHashMap<>();
    final String[] pairs = uri.getQuery().split("&");
    for (String pair : pairs) {
      final int idx = pair.indexOf("=");
      final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
      if (!queryPairs.containsKey(key)) {
        queryPairs.put(key, new LinkedList<String>());
      }
      final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
      queryPairs.get(key).add(value);
    }
    return queryPairs;
  }

}
