package com.opensimulationplatform.core.validation.simulator;

import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.SimulatorOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Simulator_1_Test {
  private final VE_Simulator_1 validationError = new VE_Simulator_1();
  private final SimulatorOwlBuilder builder = new SimulatorOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.simulators = builderContext.simulators;
    validationErrorContext.invalidIndividuals = builderContext.invalidIndividuals;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Simulator s1 = new Simulator();
    s1.setName("not-unique");

    Simulator s2 = new Simulator();
    s2.setName("not-unique");

    builder.build(s1);
    builder.build(s2);
    builder.complete();

    List<ValidationDiagnostic<Simulator>> diagnostics = validationError.validate();
    assertEquals(2, diagnostics.size());

    List<Simulator> invalidSimulators = diagnostics.stream().map(ValidationDiagnostic::getValidatedObject).collect(Collectors.toList());
    assertTrue(invalidSimulators.contains(s1));
    assertTrue(invalidSimulators.contains(s2));
  }

  @Test
  public void valid() {
    Simulator s1 = new Simulator();
    s1.setName("s1");

    Simulator s2 = new Simulator();
    s1.setName("s2");

    builder.build(s1);
    builder.build(s2);
    builder.complete();

    List<ValidationDiagnostic<Simulator>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
