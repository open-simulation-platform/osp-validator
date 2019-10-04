package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.Socket;

public class SocketConverter {
  public static OspSocket convert(Socket socket) {
    OspSocket ospSocket = new OspSocket(socket.getType(), socket.getName());
    socket.getVariables().forEach(v -> ospSocket.addVariable(new OspVariable(v)));
    
    return ospSocket;
  }
}
