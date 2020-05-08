package com.opensimulationplatform.modeldescription.util;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;

import java.util.ArrayList;
import java.util.List;

public class FmiModelDescription {
  private List<Variable> variables = new ArrayList<>();
  private List<Unit> units = new ArrayList<>();

  public List<Variable> getVariables() {
    return variables;
  }

  public void setVariables(List<Variable> variables) {
    this.variables = variables;
  }

  public List<Unit> getUnits() {
    return units;
  }

  public void setUnits(List<Unit> units) {
    this.units = units;
  }
}
