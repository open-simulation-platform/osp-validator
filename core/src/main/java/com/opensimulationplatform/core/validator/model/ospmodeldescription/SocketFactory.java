package com.opensimulationplatform.core.validator.model.ospmodeldescription;

import com.opensimulationplatform.core.json.model.modeldefinition.JsonSocket;

import java.util.List;

public class SocketFactory {
  public static Socket create(JsonSocket jsonSocket) {
    Socket socket = new Socket(jsonSocket.getType(), jsonSocket.getName());
    List<Variable> variables = VariableFactory.create(jsonSocket.getVariables());
    variables.forEach(socket::addVariable);
    return socket;
  }
}
