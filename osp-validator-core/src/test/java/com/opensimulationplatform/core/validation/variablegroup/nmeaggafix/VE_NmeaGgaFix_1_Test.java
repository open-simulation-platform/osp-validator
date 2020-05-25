package com.opensimulationplatform.core.validation.variablegroup.nmeaggafix;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
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

public class VE_NmeaGgaFix_1_Test {

  private final VE_NmeaGgaFix_1 validationError = new VE_NmeaGgaFix_1();
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
  public void valid() {
    NmeaGgaFix nmeaGgaFix = new NmeaGgaFix();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    Variable v3 = new Variable();
    v3.setCausality(v1.getCausality());
    v3.setType(v1.getType());
    v3.setUnit(v1.getUnit());

    Variable v4 = new Variable();
    v4.setCausality(v1.getCausality());
    v4.setType(v1.getType());
    v4.setUnit(v1.getUnit());

    Variable v5 = new Variable();
    v5.setCausality(v1.getCausality());
    v5.setType(v1.getType());
    v5.setUnit(v1.getUnit());

    Variable v6 = new Variable();
    v6.setCausality(v1.getCausality());
    v6.setType(v1.getType());
    v6.setUnit(v1.getUnit());

    Variable v7 = new Variable();
    v7.setCausality(v1.getCausality());
    v7.setType(v1.getType());
    v7.setUnit(v1.getUnit());

    nmeaGgaFix.setVariables(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));

    builder.build(nmeaGgaFix);
    builder.complete();

    List<ValidationDiagnostic<NmeaGgaFix>> diagnostics = validationError.validate();

    assertTrue(diagnostics.isEmpty());
  }

  @Test
  public void invalid() {
    NmeaGgaFix nmeaGgaFix = new NmeaGgaFix();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    Variable v3 = new Variable();
    v3.setCausality(v1.getCausality());
    v3.setType(v1.getType());
    v3.setUnit(v1.getUnit());

    Variable v4 = new Variable();
    v4.setCausality(v1.getCausality());
    v4.setType(v1.getType());
    v4.setUnit(v1.getUnit());

    Variable v5 = new Variable();
    v5.setCausality(v1.getCausality());
    v5.setType(v1.getType());
    v5.setUnit(v1.getUnit());

    Variable v6 = new Variable();
    v6.setCausality(v1.getCausality());
    v6.setType(v1.getType());
    v6.setUnit(v1.getUnit());

    Variable v7 = new Variable();
    v7.setCausality(v1.getCausality());
    v7.setType(v1.getType());
    v7.setUnit(v1.getUnit());

    nmeaGgaFix.setVariables(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));

    builder.build(nmeaGgaFix);
    builder.complete();

    List<ValidationDiagnostic<NmeaGgaFix>> diagnostics = validationError.validate();

    assertEquals(1, diagnostics.size());
    NmeaGgaFix invalidObject = diagnostics.get(0).getValidatedObject();
    List<Variable> variables = invalidObject.getVariables();
    assertTrue(variables.contains(v1));
    assertTrue(variables.contains(v2));
  }
}
