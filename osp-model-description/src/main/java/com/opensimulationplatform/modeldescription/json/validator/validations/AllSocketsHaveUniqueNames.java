package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;

import java.util.List;

public class AllSocketsHaveUniqueNames implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    List<OspSocket> ospSockets = ospModelDescription.getOspSockets();
    for (int i = 0; i < ospSockets.size(); i++) {
      for (int j = i + 1; j < ospSockets.size(); j++) {
        String nameA = ospSockets.get(i).getName();
        String nameB = ospSockets.get(j).getName();
        if (nameA.equals(nameB)) {
          return new Result(false);
        }
      }
    }
    return new Result(true);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
