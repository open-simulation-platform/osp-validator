package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.modeldescription.TestResources;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelDescriptionFactoryTest {
  @Test
  public void canCreate() {
    ModelDescription converted = ModelDescriptionFactory.create(TestResources.CRANE_CONTROLLER_XML, TestResources.CRANE_CONTROLLER_FMU);

    List<Variable> variables = converted.getVariables();

    List<LinearMechanicalPort> linearMechanicalPorts = converted.getLinearMechanicalPorts();
    assertEquals(1, linearMechanicalPorts.size());
    linearMechanicalPorts.forEach(linearMechanicalPort -> {
      assertTrue(variables.containsAll(linearMechanicalPort.getForce().getVariables()));
      assertTrue(variables.containsAll(linearMechanicalPort.getLinearVelocity().getVariables()));
    });

    List<Generic> generics = converted.getGenerics();
    assertEquals(1, generics.size());
    generics.forEach(generic -> {
      assertTrue(variables.containsAll(generic.getVariables()));
    });
  }
}