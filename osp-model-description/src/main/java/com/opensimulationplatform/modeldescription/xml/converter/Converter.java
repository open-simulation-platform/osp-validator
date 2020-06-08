/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<FROM, TO> {
  protected final ConverterContext context;

  public Converter(ConverterContext context) {
    this.context = context;
  }

  public Converter() {
    this.context = new ConverterContext();
  }

  public abstract TO convert(FROM object);

  public List<TO> convert(List<FROM> object) {
    return object.stream().map(this::convert).collect(Collectors.toList());
  }
}
