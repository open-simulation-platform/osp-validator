package com.opensimulationplatform.core.validation.systemstructure;

import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.simulator.SimulatorsValidator;
import com.opensimulationplatform.core.validation.variableconnection.VariableConnectionsValidator;
import com.opensimulationplatform.core.validation.variablegroupconnection.VariableGroupConnectionsValidator;

import java.util.Arrays;
import java.util.List;

public class SystemStructureValidator extends Validator<Object> {
  private final SimulatorsValidator simulatorsValidator = new SimulatorsValidator();
  private final VariableConnectionsValidator variableConnectionsValidator = new VariableConnectionsValidator();
  private final VariableGroupConnectionsValidator variableGroupConnectionsValidator = new VariableGroupConnectionsValidator();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(simulatorsValidator, variableConnectionsValidator, variableGroupConnectionsValidator);
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return null;
  }
}
