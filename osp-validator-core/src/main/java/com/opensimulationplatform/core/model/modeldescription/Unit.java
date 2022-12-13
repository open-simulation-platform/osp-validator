/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.ID;

import java.util.*;

import static com.opensimulationplatform.core.model.modeldescription.Unit.Exponent.*;

public class Unit {
  private final Name name = new Name();
  private final Map<Exponent, Integer> exponents = new LinkedHashMap<>();
  private double factor;
  private double offset;

  public Unit() {
    exponents.put(KILOGRAM, 0);
    exponents.put(METER, 0);
    exponents.put(SECOND, 0);
    exponents.put(AMPERE, 0);
    exponents.put(KELVIN, 0);
    exponents.put(MOL, 0);
    exponents.put(CANDELA, 0);
    exponents.put(RADIAN, 0);
    exponents.put(PERCENT, 0);
  }

  public ID getId() {
    StringBuilder stringBuilder = new StringBuilder();

    for (Unit.Exponent exponent : getExponents()) {
      stringBuilder
          .append(exponent.getSymbol())
          .append("_")
          .append(getExponent(exponent))
          .append("_");
    }

    String id = stringBuilder.delete(stringBuilder.lastIndexOf("_"), stringBuilder.length()).toString();

    return new ID(id);
  }

  public Name getName() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public int getExponent(Exponent exponent) {
    return exponents.get(exponent);
  }

  public void setExponent(Exponent exponent, int value) {
    exponents.put(exponent, value);
  }

  public List<Exponent> getExponents() {
    return Collections.unmodifiableList(new ArrayList<>(exponents.keySet()));
  }

  public double getFactor() {
    return factor;
  }

  public void setFactor(double factor) {
    this.factor = factor;
  }

  public double getOffset() {
    return offset;
  }

  public void setOffset(double offset) {
    this.offset = offset;
  }

  public enum Exponent {
    KILOGRAM("kg"),
    METER("m"),
    SECOND("s"),
    AMPERE("A"),
    KELVIN("K"),
    MOL("mol"),
    CANDELA("cd"),
    RADIAN("rad"),
    PERCENT("percent");

    private final String symbol;

    Exponent(String symbol) {
      this.symbol = symbol;
    }

    public String getSymbol() {
      return symbol;
    }
  }
}
