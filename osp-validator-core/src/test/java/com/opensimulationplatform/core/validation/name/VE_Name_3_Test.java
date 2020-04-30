package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableGroupOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_3_Test {

  private final VE_Name_3 validationError = new VE_Name_3();
  private final VariableGroupOwlBuilder builder = new VariableGroupOwlBuilder();
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
    Generic vg1 = new Generic();
    vg1.setName("not-a-unique-name");

    Generic vg2 = new Generic();
    vg2.setName("not-a-unique-name");

    builder.build(vg1);
    builder.build(vg2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), vg1.getName().get());
    assertEquals(invalidName.get(), vg2.getName().get());
  }

  @Test
  public void valid() {
    Generic vg1 = new Generic();
    vg1.setName("vg1-unique-name");

    Generic vg2 = new Generic();
    vg2.setName("vg2-unique-name");

    builder.build(vg1);
    builder.build(vg2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}