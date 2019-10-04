package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.Sockets;

import java.util.ArrayList;
import java.util.List;

public class SocketsConverter {
  public static List<OspSocket> convert(Sockets sockets) {
    List<OspSocket> ospSockets = new ArrayList<>();
  
    sockets.getSocket().forEach(s -> {
      OspSocket ospSocket = new OspSocket(s.getType(), s.getName());
      List<OspVariable> ospVariables = VariablesConverter.convert(s.getVariables());
      ospVariables.forEach(ospSocket::addVariable);
      ospSockets.add(ospSocket);
    });
  
    return ospSockets;
  }
}
