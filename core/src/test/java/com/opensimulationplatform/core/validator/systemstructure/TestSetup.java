package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;

class TestSetup {
  
  static SystemStructure getValidSystemStructure() {
    SystemStructure systemStructure = new SystemStructure();
  
    OspSimulator simulatorA = new OspSimulator("simulatorA", "source");
  
    OspPlug p = new OspPlug(OntologyClasses.GENERIC, "plugA");
    p.addVariable(new OspVariable("variable1"));
    p.addVariable(new OspVariable("variable2"));
    simulatorA.addPlug(p);
  
    OspSocket s = new OspSocket(OntologyClasses.GENERIC, "socketA");
    s.addVariable(new OspVariable("variable3"));
    s.addVariable(new OspVariable("variable4"));
    simulatorA.addSocket(s);
  
    OspBond bondA = new OspBond("bond");
    bondA.addPlug(p);
    bondA.addSocket(s);
    simulatorA.addBond(bondA);
  
    systemStructure.addSimulator(simulatorA);
  
    OspSimulator simulatorB = new OspSimulator("simulatorB", "source");
  
    p = new OspPlug(OntologyClasses.GENERIC, "plugA");
    p.addVariable(new OspVariable("variable1"));
    p.addVariable(new OspVariable("variable2"));
    simulatorB.addPlug(p);
  
    s = new OspSocket(OntologyClasses.GENERIC, "socketA");
    s.addVariable(new OspVariable("variable3"));
    s.addVariable(new OspVariable("variable4"));
    simulatorB.addSocket(s);
  
    OspBond bondB = new OspBond("bond");
    bondB.addPlug(p);
    bondB.addSocket(s);
    simulatorB.addBond(bondB);
  
    systemStructure.addSimulator(simulatorB);
    
    systemStructure.addBondConnection(new OspBondConnection(simulatorA, bondA, simulatorB, bondB));
  
    return systemStructure;
  }
  
  static SystemStructure getInvalidSystemStructure() {
    SystemStructure systemStructure = new SystemStructure();
    
    OspSimulator simulatorA = new OspSimulator("simulatorA", "source");
  
    OspPlug p = new OspPlug(OntologyClasses.FORCE, "plugA");
    p.addVariable(new OspVariable("variable1"));
    p.addVariable(new OspVariable("variable2"));
    simulatorA.addPlug(p);
  
    OspSocket s = new OspSocket(OntologyClasses.GENERIC, "socketA");
    s.addVariable(new OspVariable("variable3"));
    s.addVariable(new OspVariable("variable4"));
    simulatorA.addSocket(s);
  
    OspBond bondA = new OspBond("bond");
    bondA.addPlug(p);
    bondA.addSocket(s);
    simulatorA.addBond(bondA);
  
    systemStructure.addSimulator(simulatorA);
    
    OspSimulator simulatorB = new OspSimulator("simulatorB", "source");
  
    p = new OspPlug(OntologyClasses.GENERIC, "plugA");
    p.addVariable(new OspVariable("variable1"));
    p.addVariable(new OspVariable("variable2"));
    simulatorB.addPlug(p);
  
    s = new OspSocket(OntologyClasses.POSITION, "socketA");
    s.addVariable(new OspVariable("variable3"));
    s.addVariable(new OspVariable("variable4"));
    simulatorB.addSocket(s);
  
    OspBond bondB = new OspBond("bond");
    bondB.addPlug(p);
    bondB.addSocket(s);
    simulatorB.addBond(bondB);
  
    systemStructure.addSimulator(simulatorB);
  
    systemStructure.addBondConnection(new OspBondConnection(simulatorA, bondA, simulatorB, bondB));
    
    return systemStructure;
  }
  
}
