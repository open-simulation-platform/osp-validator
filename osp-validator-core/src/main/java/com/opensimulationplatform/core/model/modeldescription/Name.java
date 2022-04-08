/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.ID;

public class Name {
  private String name = "";
  private ID id;

  public Name() {
  }

  public Name(String value) {
    this.name = value;
  }

  public ID getId() {
    if (id != null) {
      return id;
    } else {
      return new ID(name);
    }
  }

  public void setId(String id) {
    this.id = new ID(id);
  }

  public String get() {
    return name;
  }

  public void set(String name) {
    this.name = name;
  }
}
