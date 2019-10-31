package com.opensimulationplatform.core.model.modeldescription.variablegroup;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;

import java.util.List;
import java.util.UUID;

public abstract class VariableGroup {
  private final String id = UUID.randomUUID().toString();
  private final Name name = new Name();

  public ID getId() {
    return () -> id;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public Name getName() {
    return name;
  }

  public abstract List<Variable> getVariables();

  public abstract List<VariableGroup> getVariableGroups();
}
