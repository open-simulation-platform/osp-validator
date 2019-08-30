package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;

import java.util.ArrayList;
import java.util.List;

public class AllBondsHaveUniqueNames implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    List<OspBond> ospBonds = ospModelDescription.getOspBonds();
    List<String> messages = new ArrayList<>();
    boolean isValid = true;
    for (int i = 0; i < ospBonds.size(); i++) {
      for (int j = i + 1; j < ospBonds.size(); j++) {
        String nameA = ospBonds.get(i).getName();
        String nameB = ospBonds.get(j).getName();
        if (nameA.equals(nameB)) {
          messages.add("Bond " + nameA + " does not have a unique name");
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
