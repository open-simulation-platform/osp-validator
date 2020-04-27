package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_2_Test {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();

    Simulator s1 = new Simulator();
    s1.setName("not-a-unique-name");

    Simulator s2 = new Simulator();
    s2.setName("not-a-unique-name");

    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    VE_Name_2 v = new VE_Name_2();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();
    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), s1.getName().get());
    assertEquals(invalidName.get(), s2.getName().get());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();

    Simulator s1 = new Simulator();
    s1.setName("s1-unique-name");

    Simulator s2 = new Simulator();
    s2.setName("s2-unique-name");

    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    VE_Name_2 v = new VE_Name_2();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();
    assertTrue(diagnostics.isEmpty());
  }
}