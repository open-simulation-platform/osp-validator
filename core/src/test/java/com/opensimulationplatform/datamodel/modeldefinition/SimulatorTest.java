package com.opensimulationplatform.datamodel.modeldefinition;

import com.opensimulationplatform.msmivalidator.model.modeldefinition.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulatorTest {
  @Test
  public void canCreateSimulator() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    
    assertEquals("name", sim.getName());
    assertEquals("source", sim.getSource());
    assertEquals("modelDefinition", sim.getModelDefinition());
  }
  
  @Test
  public void canAddVariable() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
    
    sim.addVariable(v);
  
    assertTrue(sim.getVariables().containsValue(v));
    assertEquals(sim, v.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameVariable() {
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
  
    sim1.addVariable(v);
    sim2.addVariable(v);
  }
  
  @Test
  public void canAddPlug() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Plug p = new Plug("type", "name");
  
    sim.addPlug(p);
    
    assertTrue(sim.getPlugs().containsValue(p));
    assertEquals(sim, p.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSamePlug() {
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Plug p = new Plug("type", "name");
  
    sim1.addPlug(p);
    sim2.addPlug(p);
  }
  
  @Test
  public void canAddSocket() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Socket s = new Socket("type", "name");
    
    sim.addSocket(s);
    
    assertTrue(sim.getSockets().containsValue(s));
    assertEquals(sim, s.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameSocket() {
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Socket s = new Socket("type", "name");
    
    sim1.addSocket(s);
    sim2.addSocket(s);
  }
  
  @Test
  public void canAddBond() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Bond s = new Bond("name");
    
    sim.addBond(s);
    
    assertTrue(sim.getBonds().containsValue(s));
    assertEquals(sim, s.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameBond() {
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    Bond s = new Bond("name");
    
    sim1.addBond(s);
    sim2.addBond(s);
  }
  
  @Test
  public void addingBondWithPlugWithVariablePropagatesSimulator() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
    Plug p = new Plug("type", "name");
    Bond b = new Bond("name");
    p.addVariable(v);
    b.addPlug(p);
    
    sim.addBond(b);
    
    assertEquals(sim, b.getSimulator());
    assertEquals(sim, p.getSimulator());
    assertEquals(sim, v.getSimulator());
  }
  
  @Test
  public void addingBondWithSocketWithVariablePropagatesSimulator() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
    Socket s = new Socket("type", "name");
    Bond b = new Bond("name");
    s.addVariable(v);
    b.addSocket(s);
    
    sim.addBond(b);
    
    assertEquals(sim, b.getSimulator());
    assertEquals(sim, s.getSimulator());
    assertEquals(sim, v.getSimulator());
  }
  
  @Test
  public void addingVariableInSocketInBondPropagatesSimulator() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
    Socket s = new Socket("type", "name");
    Bond b = new Bond("name");
    s.addVariable(v);
    b.addSocket(s);
    
    sim.addVariable(v);
  
    assertEquals(sim, v.getSimulator());
    assertEquals(sim, s.getSimulator());
    assertEquals(sim, b.getSimulator());
  }
  
  @Test
  public void addingVariableInPlugInBondPropagatesSimulator() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v = new Variable("name");
    Plug p = new Plug("type", "name");
    Bond b = new Bond("name");
    p.addVariable(v);
    b.addPlug(p);
    
    sim.addVariable(v);
    
    assertEquals(sim, v.getSimulator());
    assertEquals(sim, p.getSimulator());
    assertEquals(sim, b.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoVariablesWithSameName() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Variable v1 = new Variable("name");
    Variable v2 = new Variable("name");
    
    sim.addVariable(v1);
    assertTrue(sim.getVariables().containsValue(v1));
    
    sim.addVariable(v2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoPlugsWithSameName() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Plug p1 = new Plug("type", "name");
    Plug p2 = new Plug("type", "name");
    
    sim.addPlug(p1);
    assertTrue(sim.getPlugs().containsValue(p1));
    
    sim.addPlug(p2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoSocketsWithSameName() {
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Socket s1 = new Socket("type", "name");
    Socket s2 = new Socket("type", "name");
    
    sim.addSocket(s1);
    assertTrue(sim.getSockets().containsValue(s1));
    
    sim.addSocket(s2);
  }
}