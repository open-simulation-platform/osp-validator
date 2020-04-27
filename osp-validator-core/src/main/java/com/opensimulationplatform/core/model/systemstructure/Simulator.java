package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Name;

import java.util.UUID;

public class Simulator {
  private final String id = UUID.randomUUID().toString();
  private Name name = new Name();
  private ModelDescription modelDescription = new ModelDescription();

  public ID getId() {
    return () -> id;
  }

  public Name getName() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public ModelDescription getModelDescription() {
    return modelDescription;
  }

  public void setModelDescription(ModelDescription modelDescription) {
    this.modelDescription = modelDescription;
  }
}
