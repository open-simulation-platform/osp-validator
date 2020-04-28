package com.opensimulationplatform.systemstructure.xml.converter;

import java.util.List;

public abstract class Converter<FROM, TO> {
  protected ConverterContext context;

  public Converter(ConverterContext context) {
    this.context = context;
  }

  public Converter() {
    this.context = new ConverterContext();
  }

  public abstract TO convert(FROM object);

  public abstract List<TO> convert(List<FROM> object);
}
