package com.opensimulationplatform.core.model.modeldescription.variablegroup;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class VariableGroup {
  private final String id = UUID.randomUUID().toString();
  private final Name name = new Name();
  protected List<Variable> variables = new ArrayList<>();

  public ID getId() {
    return () -> id;
  }

  public Name getName() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public List<Variable> getVariables() {
    return Collections.unmodifiableList(variables);
  }

  public abstract void setVariables(List<Variable> variables);

  public abstract List<VariableGroup> getVariableGroups();
}
