/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model;

import java.util.UUID;

import static com.opensimulationplatform.gen.util.ontologycontent.OntologyContent.PREFIX;

public class ID {

  private final String id;

  public ID(String id) {
    this.id = PREFIX + UUID.nameUUIDFromBytes(id.replace(PREFIX, "").getBytes());
  }

  public ID() {
    this.id = PREFIX + UUID.randomUUID();
  }

  public String toString() {
    return id;
  }
}
