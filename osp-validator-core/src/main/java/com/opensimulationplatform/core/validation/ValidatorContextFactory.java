package com.opensimulationplatform.core.validation;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owlbuilder.OwlBuilderContext;
import com.opensimulationplatform.core.owlbuilder.SystemStructureOwlBuilder;
import com.opensimulationplatform.core.owlconfig.OWLConfig;

public class ValidatorContextFactory {
  public ValidatorContext create(SystemStructure systemStructure) {
    OwlBuilderContext builderContext = new OwlBuilderContext();
    builderContext.owl = new OWLConfig();

    SystemStructureOwlBuilder builder = new SystemStructureOwlBuilder();
    builder.setContext(builderContext);
    builder.build(systemStructure);
    builder.complete();

    ValidatorContext validatorContext = new ValidatorContext();
    validatorContext.owl = builderContext.owl;
    validatorContext.names = builderContext.names;
    validatorContext.units = builderContext.units;
    validatorContext.variables = builderContext.variables;
    validatorContext.variableGroups = builderContext.variableGroups;
    validatorContext.simulators = builderContext.simulators;
    validatorContext.variableConnections = builderContext.variableConnections;
    validatorContext.variableGroupConnections = builderContext.variableGroupConnections;

    return validatorContext;
  }

  public ValidatorContext create(ModelDescription modelDescription) {
    SystemStructure systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    simulator.setModelDescription(modelDescription);
    systemStructure.getSimulators().add(simulator);

    return create(systemStructure);
  }
}
