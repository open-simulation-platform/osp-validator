package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.*;

class OwlConverterUtil {
  public static String getIndividualName(Bond bond) {
    return "simulator_" + bond.getSimulator().getName() + "_bond_" + bond.getName();
  }
  
  public static String getIndividualName(Plug plug) {
    return "simulator_" + plug.getSimulator().getName() + "_" + plug.getType() + "_plug_" + plug.getName();
  }
  
  public static String getIndividualName(Socket socket) {
    return "simulator_" + socket.getSimulator().getName() + "_" + socket.getType() + "_socket_" + socket.getName();
  }
  
  public static String getIndividualName(Variable variable) {
    return "simulator_" + variable.getSimulator().getName() + "_variable_" + variable.getName();
  }
  
  public static String getIndividualName(Simulator simulator) {
    return "simulator_" + simulator.getName();
  }
}
