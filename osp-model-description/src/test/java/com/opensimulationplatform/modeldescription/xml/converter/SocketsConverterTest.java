package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.model.Sockets;
import com.opensimulationplatform.modeldescription.xml.model.Variables;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SocketsConverterTest {
  @Test
  public void canConvert() {
    OspModelDescription ospModelDescription = TestSetup.getTestOspModelDescription();
    Sockets sockets = ospModelDescription.getSockets();
  
    List<OspSocket> ospSockets = SocketsConverter.convert(sockets);
  
    for (int i = 0; i < ospSockets.size(); i++) {
      Sockets.Socket socket = sockets.getSocket().get(i);
      OspSocket ospSocket = ospSockets.get(i);
    
      assertEquals(socket.getName(), ospSocket.getName());
      assertEquals(socket.getType(), ospSocket.getType());
      List<Variables.Variable> variables = socket.getVariables().getVariable();
      Map<String, OspVariable> ospVariables = ospSocket.getVariables();
      assertEquals(variables.size(), ospVariables.size());
      for (Variables.Variable variable : variables) {
        assertEquals(variable.getName(), ospVariables.get(variable.getName()).getName());
      }
    }
  
  }
}