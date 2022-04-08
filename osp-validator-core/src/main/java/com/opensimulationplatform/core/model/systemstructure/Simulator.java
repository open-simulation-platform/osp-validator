/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Name;

public class Simulator {
  private final ID id = new ID();
  private final Name name = new Name();
  private ModelDescription modelDescription = new ModelDescription();

  public ID getId() {
    return id;
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
