package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;

import java.util.List;

public class AllPlugsHaveUniqueNames implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    List<OspPlug> ospPlugs = ospModelDescription.getOspPlugs();
    for (int i = 0; i < ospPlugs.size(); i++) {
      for (int j = i + 1; j < ospPlugs.size(); j++) {
        String nameA = ospPlugs.get(i).getName();
        String nameB = ospPlugs.get(j).getName();
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
