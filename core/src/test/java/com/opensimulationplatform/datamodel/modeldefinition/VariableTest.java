package com.opensimulationplatform.datamodel.modeldefinition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VariableTest {
  @Test
  public void canCreateVariable() {
    Variable v = new Variable("name");
    
    assertEquals("name", v.getName());
  }
  
  @Test
  public void canSetSimulator() {
    Variable v = new Variable("name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    
    v.setSimulator(sim);
    
    assertEquals(sim, v.getSimulator());
    assertTrue(sim.getVariables().containsValue(v));
  }
  
  @Test(expected = RuntimeException.class)
  public void canNotChangeSimulator() {
    Variable v = new Variable("name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Simulator simNew = new Simulator("name", "source", "modelDefinition");
    
    v.setSimulator(sim);
    
    v.setSimulator(simNew);
  }
  
  @Test
  public void canAddPlug() {
    Variable v = new Variable("name");
    Plug p = new Plug("type", "name");
    
    v.addPlug(p);
    
    assertTrue(v.getPlugs().containsValue(p));
    assertTrue(p.getVariables().containsValue(v));
  }
  
  @Test
  public void canAddSocket() {
    Variable v = new Variable("name");
    Socket s = new Socket("type", "name");
    
    v.addSocket(s);
    
    assertEquals(s, v.getSocket());
    assertTrue(s.getVariables().containsValue(v));
  }
  
  @Test
  public void addingPlugAddsSimulatorToVariable() {
    Variable v = new Variable("name");
    Plug p = new Plug("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim);
    
    v.addPlug(p);
    
    assertEquals(sim, v.getSimulator());
  }
  
  @Test
  public void addingSocketAddsSimulatorToVariable() {
    Variable v = new Variable("name");
    Socket s = new Socket("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    s.setSimulator(sim);
    
    v.addSocket(s);
    
    assertEquals(sim, v.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableToPlugWithDifferentSimulator() {
    Variable v = new Variable("name");
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    v.setSimulator(sim1);
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Plug p = new Plug("type", "name");
    p.setSimulator(sim2);
    
    v.addPlug(p);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableToSocketWithDifferentSimulator() {
    Variable v = new Variable("name");
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    v.setSimulator(sim1);
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Socket s = new Socket("type", "name");
    s.setSimulator(sim2);
    
    v.addSocket(s);
  }
  
  @Test
  public void aVariableCanNotBelongToPlugAndSocketAtTheSameTime() {
    Variable v = new Variable("name");
    Plug p = new Plug("type", "name");
    Socket s = new Socket("type", "name");
    boolean first = false;
    
    v.addPlug(p);
    try {
      v.addSocket(s);
    } catch (Exception e) {
      first = true;
    }
    
    v = new Variable("name");
    p = new Plug("type", "name");
    s = new Socket("type", "name");
    boolean second = false;
    
    v.addSocket(s);
    try {
      v.addPlug(p);
    } catch (Exception e) {
      second = true;
    }
    
    assertTrue(first);
    assertTrue(second);
  }
  
  @Test
  public void oneVariableCanAddTwoPlugs() {
    Variable v = new Variable("name");
    Plug p1 = new Plug("type1", "name1");
    Plug p2 = new Plug("type2", "name2");
    
    v.addPlug(p1);
    v.addPlug(p2);
    
    assertTrue(v.getPlugs().containsValue(p1));
    assertTrue(v.getPlugs().containsValue(p2));
  }
  
  @Test(expected = Exception.class)
  public void oneVariableCanNotAddTwoSockets() {
    Variable v = new Variable("name");
    Socket s1 = new Socket("type1", "name1");
    Socket s2 = new Socket("type2", "name2");
    
    v.addSocket(s1);
    assertEquals(s1, v.getSocket());
    
    v.addSocket(s2);
  }
}