package com.opensimulationplatform.core.validator.modeldescription.validations;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllPlugsAndSocketsInBondsAreDefinedInOspModelDescription implements Validation {
  private List<String> messages;
  
  @Override
  public Result validate(ModelDescription modelDescription, File ospOwlFile) {
    messages = new ArrayList<>();
    boolean valid = modelDescription.getOspBonds().values().stream().allMatch(bond -> allPlugsDefined(bond, modelDescription) && allSocketsDefined(bond, modelDescription));
    return new Result(valid, messages);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
  
  private boolean allPlugsDefined(OspBond ospBond, ModelDescription modelDescription) {
    return ospBond.getOspPlugs().stream().allMatch(plug -> {
      if (plugIsDefinedInModelDescription(modelDescription, plug)) {
        return true;
      } else {
        messages.add("Plug '" + plug.getName() + "' referenced in bond '" + ospBond.getName() + "' is not defined in the osp model description");
        return false;
      }
    });
  }
  
  private boolean allSocketsDefined(OspBond ospBond, ModelDescription modelDescription) {
    return ospBond.getOspSockets().stream().allMatch(socket -> {
      if (socketIsDefinedInModelDescription(modelDescription, socket)) {
        return true;
      } else {
        messages.add("Socket '" + socket.getName() + "' referenced in bond '" + ospBond.getName() + "' is not defined in the osp model description");
        return false;
      }
    });
  }
  
  private boolean plugIsDefinedInModelDescription(ModelDescription modelDescription, OspPlug ospPlug) {
    return modelDescription.getOspPlugs().values().stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
  }
  
  private boolean socketIsDefinedInModelDescription(ModelDescription modelDescription, OspSocket ospSocket) {
    return modelDescription.getOspSockets().values().stream().anyMatch(p -> p.getName().equals(ospSocket.getName()));
  }
}
