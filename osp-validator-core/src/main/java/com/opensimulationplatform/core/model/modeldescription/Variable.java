/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.ID;

public class Variable {
  private final ID id = new ID();
  private final Name name = new Name();
  private Causality causality = Causality.UNDEFINED;
  private Type type = Type.UNDEFINED;
  private Axis axis = Axis.UNDEFINED;
  private Unit unit = new Unit();

  public ID getId() {
    return id;
  }

  public Name getName() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public Causality getCausality() {
    return causality;
  }

  public void setCausality(Causality causality) {
    this.causality = causality;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public void setAxis(Axis axis) {
    this.axis = axis;
  }

  public Axis getAxis() {
    return axis;
  }

  public enum Causality {INPUT, OUTPUT, UNDEFINED}

  public enum Type {BOOLEAN, ENUM, INTEGER, REAL, STRING, UNDEFINED}

  public enum Axis {UNDEFINED, X, Y, Z}
}
