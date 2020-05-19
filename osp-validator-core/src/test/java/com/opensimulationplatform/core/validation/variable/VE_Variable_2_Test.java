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
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Variable_2_Test {

  private final VE_Variable_2 validationError = new VE_Variable_2();
  private final VariableOwlBuilder builder = new VariableOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();
    builderContext.owl.removeNakedVariables = false;

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variables = builderContext.variables;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable v1 = new Variable();
    v1.setName("not-unique");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setName("not-unique");
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.REAL);

    builder.build(v1);
    builder.build(v2);
    builder.complete();

    List<ValidationDiagnostic<Variable>> diagnostics = validationError.validate();
    assertEquals(2, diagnostics.size());

    List<Variable> invalidVariables = diagnostics.stream().map(ValidationDiagnostic::getValidatedObject).collect(Collectors.toList());
    assertTrue(invalidVariables.contains(v1));
    assertTrue(invalidVariables.contains(v2));
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