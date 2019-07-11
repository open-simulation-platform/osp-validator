package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.*;

public class OwlConverterUtil {
  public static String getIndividualName(Entity entity) {
    StringBuilder builder = new StringBuilder();
    
    while (entity != null) {
      if (entity instanceof Variable) {
        builder.insert(0, "_variable_" + entity.getName());
      } else if (entity instanceof Plug) {
        builder.insert(0, "_" + ((Plug) entity).getType() + "_plug_" + entity.getName());
      } else if (entity instanceof Socket) {
        builder.insert(0, "_" + ((Socket) entity).getType() + "_socket_" + entity.getName());
      } else if (entity instanceof Bond){
        builder.insert(0, "_bond_" + entity.getName());
      } else if (entity instanceof Simulator) {
        builder.insert(0, "_simulator_" + entity.getName());
      } else {
        throw new UnsupportedOperationException();
      }
      entity = entity.getParent();
    }
    
    
    return builder.toString().replaceFirst("_", "");
  }
}
