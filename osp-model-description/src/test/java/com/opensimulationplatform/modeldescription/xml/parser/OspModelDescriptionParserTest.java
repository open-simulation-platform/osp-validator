package com.opensimulationplatform.modeldescription.xml.parser;

import com.opensimulationplatform.modeldescription.TestResources;
import com.opensimulationplatform.modeldescription.xml.model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OspModelDescriptionParserTest {
  @Test
  public void valid() {
    OspModelDescriptionType content = OspModelDescriptionParser.parse(TestResources.VALID_OSP_MODEL_DESCRIPTION);

    UnitDefinitionsType unitDefinitions = content.getUnitDefinitions();
    List<UnitType> unitTypes = unitDefinitions.getUnit();
    assertEquals(3, unitTypes.size());

    UnitType u1 = unitTypes.get(0);
    UnitType u2 = unitTypes.get(1);
    UnitType u3 = unitTypes.get(2);

    assertEquals("u1", u1.getName());
    assertEquals("u2", u2.getName());
    assertEquals("u3", u3.getName());

    List<GenericType> generics = content.getVariableGroups().getGeneric();
    assertEquals(1, generics.size());

    GenericType genericType = generics.get(0);
    List<VariableType> variables = genericType.getVariable();
    assertEquals(3, variables.size());

    VariableType v1 = variables.get(0);
    VariableType v2 = variables.get(1);
    VariableType v3 = variables.get(2);

    assertEquals("v1", v1.getRef());
    assertEquals("v2", v2.getRef());
    assertEquals("v3", v3.getRef());

    assertEquals("u1", v1.getUnit());
    assertEquals("u2", v2.getUnit());
    assertEquals("u3", v3.getUnit());
  }

  @Test (expected = RuntimeException.class)
  public void invalid() {
    OspModelDescriptionParser.parse(TestResources.INVALID_OSP_MODEL_DESCRIPTION);
  }
}