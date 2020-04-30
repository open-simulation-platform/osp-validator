package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.SimulatorOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_2_Test {

  private final VE_Name_2 validationError = new VE_Name_2();
  private final SimulatorOwlBuilder builder = new SimulatorOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.names = builderContext.names;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Simulator s1 = new Simulator();
    s1.setName("not-a-unique-name");

    Simulator s2 = new Simulator();
    s2.setName("not-a-unique-name");

    builder.build(s1);
    builder.build(s2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), s1.getName().get());
    assertEquals(invalidName.get(), s2.getName().get());
  }

  @Test
  public void valid() {
    Simulator s1 = new Simulator();
    s1.setName("s1-unique-name");

    Simulator s2 = new Simulator();
    s2.setName("s2-unique-name");

    builder.build(s1);
    builder.build(s2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}