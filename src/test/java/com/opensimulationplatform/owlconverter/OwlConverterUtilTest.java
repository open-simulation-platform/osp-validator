package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.*;
import org.junit.Test;

import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;
import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;

public class OwlConverterUtilTest {
  @Test
  public void canCreateIndividualNameForVariableInPlug() {
    Variable v = new Variable("variableName");
    Plug p = new Plug("plugType", "plugName", singletonMap(v.getName(), v));
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", emptyMap(), singletonMap(p.getName(), p), emptyMap());
    
    assertEquals("simulator_simulatorName_plugType_plug_plugName_variable_variableName", getIndividualName(v));
  }

  @Test
  public void canCreateIndividualNameForVariableInSocket() {
    Variable v = new Variable("variableName");
    Socket s = new Socket("socketType", "socketName", singletonMap(v.getName(), v));
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", emptyMap(), emptyMap(), singletonMap(s.getName(), s));
    
    assertEquals("simulator_simulatorName_socketType_socket_socketName_variable_variableName", getIndividualName(v));
  }
  
  @Test
  public void canCreateIndividualNameForSocket() {
    Socket s = new Socket("socketType", "socketName", emptyMap());
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", emptyMap(), emptyMap(), singletonMap(s.getName(), s));
  
    assertEquals("simulator_simulatorName_socketType_socket_socketName", getIndividualName(s));
  }
  
  @Test
  public void canCreateIndividualNameForPlug() {
    Plug p = new Plug("plugType", "plugName", emptyMap());
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", emptyMap(), singletonMap(p.getName(), p), emptyMap());
    
    assertEquals("simulator_simulatorName_plugType_plug_plugName", getIndividualName(p));
  }
  
  @Test
  public void canCreateIndividualNameForBond() {
    Bond b = new Bond("bondName", emptyList(), emptyList());
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", singletonMap(b.getName(), b), emptyMap(), emptyMap());
    
    assertEquals("simulator_simulatorName_bond_bondName", getIndividualName(b));
  }
  
  @Test
  public void canCreateIndividualNameForSimulator() {
    Simulator sim = new Simulator("simulatorName", "source", "modelDefinition", emptyMap(), emptyMap(), emptyMap());
    
    assertEquals("simulator_simulatorName", getIndividualName(sim));
  }
}