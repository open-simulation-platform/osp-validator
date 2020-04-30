package com.opensimulationplatform.core.validation.variablegroup.electromagneticport;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.VariableGroupOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.ValidationErrorContext;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_ElectromagneticPort_1_Test {

  private final VE_ElectromagneticPort_1 validationError = new VE_ElectromagneticPort_1();
  private final VariableGroupOwlBuilder builder = new VariableGroupOwlBuilder();
  private final ValidationErrorContext validationErrorContext = new ValidationErrorContext();
  private final OwlBuilderContext builderContext = new OwlBuilderContext();

  @Before
  public void setUp() {
    builder.setContext(builderContext);
    builderContext.owl = new OWLConfig();

    validationErrorContext.owl = builderContext.owl;
    validationErrorContext.variableGroups = builderContext.variableGroups;

    validationError.setContext(validationErrorContext);
  }

  @Test
  public void invalid() {
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.OUTPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.OUTPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Voltage voltage = new Voltage();
    voltage.setVariables(Arrays.asList(v1, v2));

    Current current = new Current();
    current.setVariables(Arrays.asList(v3, v4));

    ElectromagneticPort electromagneticPort = new ElectromagneticPort();
    electromagneticPort.setVoltage(voltage);
    electromagneticPort.setCurrent(current);

    builder.build(electromagneticPort);
    builder.complete();

    List<ValidationDiagnostic<ElectromagneticPort>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    ElectromagneticPort invalidElectromagneticPort = diagnostics.get(0).getValidatedObject();
    assertEquals(electromagneticPort, invalidElectromagneticPort);
  }

  @Test
  public void valid() {
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.INPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.INPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Voltage voltage = new Voltage();
    voltage.setVariables(Arrays.asList(v1, v2));

    Current current = new Current();
    current.setVariables(Arrays.asList(v3, v4));

    ElectromagneticPort electromagneticPort = new ElectromagneticPort();
    electromagneticPort.setVoltage(voltage);
    electromagneticPort.setCurrent(current);

    builder.build(electromagneticPort);
    builder.complete();

    List<ValidationDiagnostic<ElectromagneticPort>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
