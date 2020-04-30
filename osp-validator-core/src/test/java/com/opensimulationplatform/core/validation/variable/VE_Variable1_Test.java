package com.opensimulationplatform.core.validation.variable;

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

public class VE_Variable1_Test {

  private final VE_Variable_1 validationError = new VE_Variable_1();
  private final VariableOwlBuilder builder = new VariableOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variables = builderContext.variables;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setCausality(Variable.Causality.UNDEFINED);
    v2.setType(Variable.Type.REAL);

    builder.build(v1);
    builder.build(v2);
    builder.complete();

    List<ValidationDiagnostic<Variable>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    Variable invalidVariable = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidVariable, v2);
  }

  @Test
  public void valid() {
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.REAL);

    builder.build(v1);
    builder.build(v2);
    builder.complete();

    List<ValidationDiagnostic<Variable>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}