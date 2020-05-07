package com.opensimulationplatform.core.validation.variablegroup.general;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableGroupOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import com.opensimulationplatform.core.validation.variablegroup.VE_VariableGroup_3;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableGroup_3_Test {
  private final VE_VariableGroup_3 validationError = new VE_VariableGroup_3();
  private final VariableGroupOwlBuilder builder = new VariableGroupOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variableGroups = builderContext.variableGroups;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Generic vg1 = new Generic();
    vg1.setName("vg1");

    Generic vg2 = new Generic();
    vg2.setName("vg2");
    Variable v1 = new Variable();
    v1.setName("not-unique");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    Variable v2 = new Variable();
    v2.setName("not-unique");
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.REAL);
    vg2.setVariables(Arrays.asList(v1, v2));

    vg1.getGenerics().add(vg2);

    builder.build(vg1);
    builder.complete();

    List<ValidationDiagnostic<VariableGroup>> diagnostics = validationError.validate();
    assertEquals(1, diagnostics.size());

    List<VariableGroup> invalidVariableGroups = diagnostics.stream().map(ValidationDiagnostic::getValidatedObject).collect(Collectors.toList());
    assertTrue(invalidVariableGroups.contains(vg1));
  }

  @Test
  public void valid() {
    Generic vg1 = new Generic();
    vg1.setName("vg1");

    Generic vg2 = new Generic();
    vg2.setName("vg2");
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.REAL);
    vg2.setVariables(Arrays.asList(v1, v2));

    vg1.getGenerics().add(vg2);

    builder.build(vg1);
    builder.complete();

    List<ValidationDiagnostic<VariableGroup>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
