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
  }

  public ID getId() {
    return () -> {
      List<Unit.Exponent> exponents = getExponents();
      StringBuilder stringBuilder = new StringBuilder();
      for (Unit.Exponent exponent : exponents) {
        stringBuilder
            .append(exponent.getSymbol())
            .append("_")
            .append(getExponent(exponent))
            .append("_");
      }
      return stringBuilder.delete(stringBuilder.lastIndexOf("_"), stringBuilder.length()).toString();
    };
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
    RADIAN("rad");

    private final String symbol;

    Exponent(String symbol) {
      this.symbol = symbol;
    }

    public String getSymbol() {
      return symbol;
    }
  }
}
