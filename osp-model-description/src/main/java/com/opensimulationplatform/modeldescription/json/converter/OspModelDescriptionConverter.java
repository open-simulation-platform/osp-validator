package com.opensimulationplatform.modeldescription.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Bond;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.model.Plug;
import com.opensimulationplatform.modeldescription.json.model.Socket;

import java.util.List;

public class OspModelDescriptionConverter {
  
  public static ModelDescription convert(OspModelDescription ospModelDescription) {
    ModelDescription modelDescription = new ModelDescription();
    
    modelDescription.setName(ospModelDescription.getName());
  
    List<Plug> plugs = ospModelDescription.getPlugs();
    for (Plug plug : plugs) {
      modelDescription.addPlug(PlugConverter.convert(plug));
    }
  
    List<Socket> sockets = ospModelDescription.getSockets();
    for (Socket socket : sockets) {
      modelDescription.addSocket(SocketConverter.convert(socket));
    }
    
    List<Bond> bonds = ospModelDescription.getBonds();
    for (Bond bond : bonds) {
      modelDescription.addBond(BondConverter.convert(bond, modelDescription.getOspPlugs(), modelDescription.getOspSockets()));
    }
    
    return modelDescription;
  }
}
