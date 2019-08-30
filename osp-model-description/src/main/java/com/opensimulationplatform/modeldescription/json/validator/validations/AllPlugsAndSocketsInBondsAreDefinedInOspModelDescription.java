package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;

import java.util.ArrayList;
import java.util.List;

public class AllPlugsAndSocketsInBondsAreDefinedInOspModelDescription implements Validation {
  private List<String> messages;
  
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    messages = new ArrayList<>();
    boolean valid = ospModelDescription.getOspBonds().stream().allMatch(bond -> allPlugsDefined(bond, ospModelDescription) && allSocketsDefined(bond, ospModelDescription));
    return new Result(valid, messages);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
  
  private boolean allPlugsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspPlugs().stream().allMatch(plug -> {
      if (plugIsDefinedInModelDescription(modelDescription, plug)) {
        return true;
      } else {
        messages.add("Plug " + plug.getName() + " referenced in bond " + ospBond.getName() + " is not defined in the osp model description");
        return false;
      }
    });
  }
  
  private boolean allSocketsDefined(OspBond ospBond, OspModelDescription modelDescription) {
    return ospBond.getOspSockets().stream().allMatch(socket -> {
      if (socketIsDefinedInModelDescription(modelDescription, socket)) {
        return true;
      } else {
        messages.add("Socket " + socket.getName() + " referenced in bond " + ospBond.getName() + " is not defined in the osp model description");
        return false;
      }
    });
  }
  
  private boolean plugIsDefinedInModelDescription(OspModelDescription modelDescription, OspPlug ospPlug) {
    return modelDescription.getOspPlugs().stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
  }
  
  private boolean socketIsDefinedInModelDescription(OspModelDescription modelDescription, OspSocket ospSocket) {
    return modelDescription.getOspSockets().stream().anyMatch(p -> p.getName().equals(ospSocket.getName()));
  }
}
