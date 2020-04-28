package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.ID;

public class Name {
  private String name = "";
  private ID id;

  public Name() {
  }

  public Name(String value) {
    this.name = value;
  }

  public ID getId() {
    if (id != null) {
      return id;
    } else {
      return () -> name;
    }
  }

  public void setId(ID id) {
    this.id = id;
  }

  public String get() {
    return name;
  }

  public void set(String name) {
    this.name = name;
  }
}
