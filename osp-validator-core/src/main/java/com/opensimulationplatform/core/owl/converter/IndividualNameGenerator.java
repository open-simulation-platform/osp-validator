package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;

class IndividualNameGenerator {
  static String getIndividualName(OspBond ospBond) {
    return "simulator_" + ospBond.getOspSimulator().getName() + "_bond_" + ospBond.getName();
  }
  
  static String getIndividualName(OspPlug ospPlug) {
    return "simulator_" + ospPlug.getOspSimulator().getName() + "_" + ospPlug.getType() + "_plug_" + ospPlug.getName();
  }
  
  static String getIndividualName(OspSocket ospSocket) {
    return "simulator_" + ospSocket.getOspSimulator().getName() + "_" + ospSocket.getType() + "_socket_" + ospSocket.getName();
  }
  
  static String getIndividualName(OspVariable ospVariable) {
    return "simulator_" + ospVariable.getOspSimulator().getName() + "_variable_" + ospVariable.getName();
  }
  
  static String getIndividualName(OspSimulator ospSimulator) {
    return "simulator_" + ospSimulator.getName();
  }
}
