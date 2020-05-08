package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_1_Test {

  private final VE_Name_1 validationError = new VE_Name_1();
  private final VariableOwlBuilder builder = new VariableOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.names = builderContext.names;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable v1 = new Variable();
    v1.setName("not-a-unique-name");

    Variable v2 = new Variable();
    v2.setName("not-a-unique-name");

    builder.build(v1);
    builder.build(v2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), v1.getName().get());
    assertEquals(invalidName.get(), v2.getName().get());
  }

  @Test
  public void valid() {
    Variable v1 = new Variable();
    v1.setName("v1-unique-name");

    Variable v2 = new Variable();
    v2.setName("v2-unique-name");

    builder.build(v1);
    builder.build(v2);
    builder.complete();

    List<ValidationDiagnostic<Name>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}