package com.opensimulationplatform.modeldescription.xml.converter;

import java.util.List;

public abstract class Converter<FROM, TO> {
  protected final ConverterContext converterContext;

  public Converter(ConverterContext converterContext) {
    this.converterContext = converterContext;
  }

  public Converter() {
    this.converterContext = new ConverterContext();
  }

  public abstract TO convert(FROM object);

  public abstract List<TO> convert(List<FROM> object);
}
