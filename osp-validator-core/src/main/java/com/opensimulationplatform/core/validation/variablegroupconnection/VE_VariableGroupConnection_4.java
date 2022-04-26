package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class VE_VariableGroupConnection_4 extends ValidationError<VariableGroupConnection> {
  @Override
  protected List<VariableGroupConnection> getInvalidObjects() {
    List<VariableGroupConnection> invalidObjects = new ArrayList<>();

    for (VariableGroupConnection vgc : context.variableGroupConnections.values()) {
      VariableGroup vga = vgc.getVariableGroupA();
      VariableGroup vgb = vgc.getVariableGroupB();

      List<Variable.Axis> vgaAxes = vga.getVariables().stream().map(Variable::getAxis).sorted().collect(toList());
      List<Variable.Axis> vgbAxes = vgb.getVariables().stream().map(Variable::getAxis).sorted().collect(toList());

      if (!vgaAxes.equals(vgbAxes)) {
        invalidObjects.add(vgc);
      }
    }

    return invalidObjects;
  }

  @Override
  protected String getErrorMessage(VariableGroupConnection variableGroupConnection) {
    String variableGroupA = variableGroupConnection.getVariableGroupA().getName().getId().toString();
    String variableGroupB = variableGroupConnection.getVariableGroupB().getName().getId().toString();

    return "VariableGroupConnection [" + variableGroupA + ", " + variableGroupB + "] contains incompatible axis " +
        "properties";
  }
}
