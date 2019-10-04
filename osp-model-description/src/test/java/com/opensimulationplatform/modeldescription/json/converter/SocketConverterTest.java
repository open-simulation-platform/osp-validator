package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.modeldescription.json.model.Socket;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SocketConverterTest {
  @Test
  public void canConvert() {
    Socket socket = new Socket();
    socket.setName("name");
    socket.setType("type");
    socket.setVariables(Arrays.asList("variableA", "variableB"));
  
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setName("name");
    
    OspSocket ospSocket = SocketConverter.convert(socket);
    
    assertEquals(socket.getName(), ospSocket.getName());
    assertEquals(socket.getType(), ospSocket.getType());
    List<String> variables = socket.getVariables();
    Map<String, OspVariable> ospVariables = ospSocket.getVariables();
    assertEquals(variables.size(), ospVariables.size());
    for (String variable : variables) {
      assertEquals(variable, ospVariables.get(variable).getName());
    }
  }
}