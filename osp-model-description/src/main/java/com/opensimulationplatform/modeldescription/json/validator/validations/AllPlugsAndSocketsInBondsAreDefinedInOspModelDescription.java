package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;

public class AllPlugsAndSocketsInBondsAreDefinedInOspModelDescription implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    boolean valid = ospModelDescription.getOspBonds().stream().allMatch(bond -> allPlugsDefined(bond, ospModelDescription) && allSocketsDefined(bond, ospModelDescription));
    return new Result(valid);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
  
  private boolean allPlugsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspPlugs().stream().allMatch(plug -> plugIsDefinedInModelDescription(modelDescription, plug));
  }
  
  private boolean allSocketsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspSockets().stream().allMatch(socket -> socketIsDefinedInModelDescription(modelDescription, socket));
  }
  
  private boolean plugIsDefinedInModelDescription(OspModelDescription modelDescription, OspPlug ospPlug) {
    return modelDescription.getOspPlugs().stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
  }
  
  private boolean socketIsDefinedInModelDescription(OspModelDescription modelDescription, OspSocket ospSocket) {
    return modelDescription.getOspSockets().stream().anyMatch(p -> p.getName().equals(ospSocket.getName()));
  }
}
