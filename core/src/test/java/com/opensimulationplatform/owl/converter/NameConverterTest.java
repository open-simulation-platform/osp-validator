package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.validator.model.modeldefinition.*;
import org.junit.Test;

import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;
import static org.junit.Assert.assertEquals;

public class NameConverterTest {
  @Test
  public void canCreateIndividualNameForVariableI() {
    Variable v = new Variable("variableName");
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition");
    sim.addVariable(v);
    
    assertEquals("simulator_simulatorName_variable_variableName", getIndividualName(v));
  }
  
  @Test
  public void canCreateIndividualNameForSocket() {
    Socket s = new Socket("socketType", "socketName");
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition");
    sim.addSocket(s);
    
    assertEquals("simulator_simulatorName_socketType_socket_socketName", getIndividualName(s));
  }
  
  @Test
  public void canCreateIndividualNameForPlug() {
    Plug p = new Plug("plugType", "plugName");
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition");
    sim.addPlug(p);
    
    assertEquals("simulator_simulatorName_plugType_plug_plugName", getIndividualName(p));
  }
  
  @Test
  public void canCreateIndividualNameForBond() {
    Bond b = new Bond("bondName");
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition");
    sim.addBond(b);
    
    assertEquals("simulator_simulatorName_bond_bondName", getIndividualName(b));
  }
  
  @Test
  public void canCreateIndividualNameForSimulator() {
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition");
    
    assertEquals("simulator_simulatorName", getIndividualName(sim));
  }
}