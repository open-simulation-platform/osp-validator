package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;

import java.util.ArrayList;
import java.util.List;

public class AllPlugsHaveUniqueNames implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    List<OspPlug> ospPlugs = ospModelDescription.getOspPlugs();
    List<String> messages = new ArrayList<>();
    boolean isValid = true;
    for (int i = 0; i < ospPlugs.size(); i++) {
      for (int j = i + 1; j < ospPlugs.size(); j++) {
        String nameA = ospPlugs.get(i).getName();
        String nameB = ospPlugs.get(j).getName();
        if (nameA.equals(nameB)) {
          messages.add("Plug '" + nameA + "' does not have a unique name");
          isValid = false;
        }
      }
    }
    return new Result(isValid, messages);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
