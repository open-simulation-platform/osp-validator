package com.opensimulationplatform.modeldescription.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.modeldescription.xml.model.Bonds;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.model.Plugs;
import com.opensimulationplatform.modeldescription.xml.model.Sockets;

import java.util.List;

public class OspModelDescriptionConverter {
  public static ModelDescription convert(OspModelDescription ospModelDescription) {
    ModelDescription modelDescription = new ModelDescription();
    
    Plugs plugs = ospModelDescription.getPlugs();
    List<OspPlug> ospPlugs = PlugsConverter.convert(plugs);
    ospPlugs.forEach(modelDescription::addPlug);
    
    Sockets sockets = ospModelDescription.getSockets();
    List<OspSocket> ospSockets = SocketsConverter.convert(sockets);
    ospSockets.forEach(modelDescription::addSocket);
    
    Bonds bonds = ospModelDescription.getBonds();
    List<OspBond> ospBonds = BondsConverter.convert(bonds, modelDescription.getOspPlugs(), modelDescription.getOspSockets());
    ospBonds.forEach(modelDescription::addBond);
    
    return modelDescription;
  }
}
