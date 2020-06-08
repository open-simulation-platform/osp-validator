/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.api.examples;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.systemstructure.SystemStructureValidator;

import java.util.Arrays;
import java.util.List;

public class ValidateSystemStructureDataModel {
  public static void main(String[] args) {
    SystemStructure systemStructure = new SystemStructure();

    Simulator s1 = new Simulator();
    s1.setName("s1");
    s1.setModelDescription(getModelDescription(Variable.Causality.INPUT, Variable.Causality.OUTPUT));

    Simulator s2 = new Simulator();
    s2.setName("s2");
    s2.setModelDescription(getModelDescription(Variable.Causality.OUTPUT, Variable.Causality.INPUT));

    VariableConnection vc = new VariableConnection();
    vc.setSimulatorA(s1);
    vc.setVariableA(ModelDescriptionUtil.getVariableByName(s1.getModelDescription(), "v5"));
    vc.setSimulatorB(s2);
    vc.setVariableB(ModelDescriptionUtil.getVariableByName(s2.getModelDescription(), "v5"));

    VariableGroupConnection vgc = new VariableGroupConnection();
    vgc.setSimulatorA(s1);
    vgc.setVariableGroupA(ModelDescriptionUtil.getVariableGroupByName(s1.getModelDescription(), "linearMechanicalPort"));
    vgc.setSimulatorB(s2);
    vgc.setVariableGroupB(ModelDescriptionUtil.getVariableGroupByName(s2.getModelDescription(), "linearMechanicalPort"));

    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);
    systemStructure.getVariableConnections().add(vc);
    systemStructure.getVariableGroupConnections().add(vgc);

    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }

  private static ModelDescription getModelDescription(Variable.Causality causalityA, Variable.Causality causalityB) {
    ModelDescription modelDescription = new ModelDescription();

    Unit unit = new Unit();
    unit.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable v1 = new Variable();
    v1.setCausality(causalityA);
    v1.setType(Variable.Type.REAL);
    v1.setName("v1");
    v1.setUnit(unit);

    Variable v2 = new Variable();
    v2.setCausality(causalityA);
    v2.setType(Variable.Type.REAL);
    v2.setName("v2");
    v2.setUnit(unit);

    Variable v3 = new Variable();
    v3.setCausality(causalityB);
    v3.setType(Variable.Type.REAL);
    v3.setName("v3");
    v3.setUnit(unit);

    Variable v4 = new Variable();
    v4.setCausality(causalityB);
    v4.setType(Variable.Type.REAL);
    v4.setName("v4");
    v4.setUnit(unit);

    Variable v5 = new Variable();
    v5.setCausality(causalityA);
    v5.setType(Variable.Type.REAL);
    v5.setName("v5");
    v5.setUnit(unit);

    Force force = new Force();
    force.setName("force");
    force.setVariables(Arrays.asList(v1, v2));

    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setName("linearVelocity");
    linearVelocity.setVariables(Arrays.asList(v3, v4));

    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();
    linearMechanicalPort.setName("linearMechanicalPort");
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    modelDescription.getUnits().add(unit);
    modelDescription.getVariables().addAll(Arrays.asList(v1, v2, v3, v4, v5));
    modelDescription.getForces().add(force);
    modelDescription.getLinearVelocities().add(linearVelocity);
    modelDescription.getLinearMechanicalPorts().add(linearMechanicalPort);

    return modelDescription;
  }
}
