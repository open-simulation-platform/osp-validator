package com.opensimulationplatform.core.validation.variablegroup;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariableGroupsValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Force f1 = new Force();
    Variable v1 = new Variable();
    Unit u1 = new Unit();
    u1.setExponent(Unit.Exponent.KILOGRAM, 1);
    v1.setUnit(u1);
    v1.setName("v1");
    v1.setType(Variable.Type.REAL);
    v1.setCausality(Variable.Causality.OUTPUT);
    Variable v2 = new Variable();
    Unit u2 = new Unit();
    u2.setExponent(Unit.Exponent.KILOGRAM, 2);
    v2.setUnit(u2);
    v2.setName("v2");
    v2.setType(Variable.Type.REAL);
    v2.setCausality(Variable.Causality.OUTPUT);
    f1.setVariables(Arrays.asList(v1, v2));
    f1.setName("f1");

    Force f2 = new Force();
    Variable v3 = new Variable();
    v3.setUnit(u1);
    v3.setName("v3");
    v3.setType(Variable.Type.REAL);
    v3.setCausality(Variable.Causality.OUTPUT);
    Variable v4 = new Variable();
    v4.setUnit(u1);
    v4.setName("v4");
    v4.setType(Variable.Type.INTEGER);
    v4.setCausality(Variable.Causality.OUTPUT);
    f2.setVariables(Arrays.asList(v3, v4));
    f2.setName("f2");

    Force f3 = new Force();
    Variable v5 = new Variable();
    v5.setUnit(u1);
    v5.setName("v5");
    v5.setType(Variable.Type.REAL);
    v5.setCausality(Variable.Causality.OUTPUT);
    Variable v6 = new Variable();
    v6.setUnit(u1);
    v6.setName("v6");
    v6.setType(Variable.Type.REAL);
    v6.setCausality(Variable.Causality.INPUT);
    f3.setVariables(Arrays.asList(v5, v6));
    f3.setName("f3");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);
    modelDescription.getVariables().add(v3);
    modelDescription.getVariables().add(v4);
    modelDescription.getVariables().add(v5);
    modelDescription.getVariables().add(v6);

    modelDescription.getUnits().add(u1);
    modelDescription.getUnits().add(u2);

    modelDescription.getForces().add(f1);
    modelDescription.getForces().add(f2);
    modelDescription.getForces().add(f3);

    VariableGroupsValidator v = new VariableGroupsValidator();
    List<ValidationDiagnostic<VariableGroup>> diagnostics = v.validate(modelDescription);

    assertEquals(3, diagnostics.size());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Force f1 = new Force();
    Variable v1 = new Variable();
    Unit u1 = new Unit();
    u1.setExponent(Unit.Exponent.KILOGRAM, 1);
    v1.setUnit(u1);
    v1.setName("v1");
    v1.setType(Variable.Type.REAL);
    v1.setCausality(Variable.Causality.OUTPUT);
    Variable v2 = new Variable();
    v2.setUnit(u1);
    v2.setName("v2");
    v2.setType(Variable.Type.REAL);
    v2.setCausality(Variable.Causality.OUTPUT);
    f1.setVariables(Arrays.asList(v1, v2));
    f1.setName("f1");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    modelDescription.getUnits().add(u1);

    modelDescription.getForces().add(f1);

    VariableGroupsValidator v = new VariableGroupsValidator();
    List<ValidationDiagnostic<VariableGroup>> diagnostics = v.validate(modelDescription);

    assertEquals(0, diagnostics.size());
  }
}