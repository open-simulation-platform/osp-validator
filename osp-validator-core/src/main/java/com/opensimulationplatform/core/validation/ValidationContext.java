package com.opensimulationplatform.core.validation;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owlconfig.OWLConfig;

public class ValidationContext {
  private final SystemStructure systemStructure;
  private final OWLConfig owlConfig;

  public ValidationContext(SystemStructure systemStructure) {
    this.systemStructure = systemStructure;
    this.owlConfig = new OWLConfig(systemStructure);
  }

  public ValidationContext(ModelDescription modelDescription) {
    this.systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    simulator.setModelDescription(modelDescription);
    this.systemStructure.getSimulators().add(simulator);

    this.owlConfig = new OWLConfig(systemStructure);
  }

  public SystemStructure getSystemStructure() {
    return systemStructure;
  }

  public OWLConfig getOwlConfig() {
    return owlConfig;
  }
}
