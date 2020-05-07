package com.opensimulationplatform.core.validation.variablegroup.general;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableGroupOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableGroup_2_Test {
  private final VE_VariableGroup_2 validationError = new VE_VariableGroup_2();
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
    vg1.setName("not-unique");

    Generic vg2 = new Generic();
    vg2.setName("not-unique");

    builder.build(vg1);
    builder.build(vg2);
    builder.complete();

    List<ValidationDiagnostic<VariableGroup>> diagnostics = validationError.validate();
    assertEquals(2, diagnostics.size());

    List<VariableGroup> invalidVariableGroups = diagnostics.stream().map(ValidationDiagnostic::getValidatedObject).collect(Collectors.toList());
    assertTrue(invalidVariableGroups.contains(vg1));
    assertTrue(invalidVariableGroups.contains(vg2));
  }

  @Test
  public void valid() {
    Generic vg1 = new Generic();
    vg1.setName("vg1");

    Generic vg2 = new Generic();
    vg2.setName("vg2");

    builder.build(vg1);
    builder.build(vg2);
    builder.complete();

    List<ValidationDiagnostic<VariableGroup>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
