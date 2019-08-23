package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.JsonOspSocket;

import java.util.List;

public class OspSocketFactory {
  public static OspSocket create(JsonOspSocket jsonOspSocket) {
    OspSocket ospSocket = new OspSocket(jsonOspSocket.getType(), jsonOspSocket.getName());
    List<OspVariable> ospVariables = OspVariableFactory.create(jsonOspSocket.getVariables());
    ospVariables.forEach(ospSocket::addVariable);
    return ospSocket;
  }
}
