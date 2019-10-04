package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspSocketTest {
  @Test
  public void canCreateSocket() {
    OspSocket s = new OspSocket("type", "name");
    
    assertEquals("type", s.getType());
    assertEquals("name", s.getName());
  }
  
  @Test
  public void canSetSimulator() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
  
    s.setOspSimulator(sim);
    
    assertEquals(sim, s.getOspSimulator());
    assertTrue(sim.getSockets().containsValue(s));
  }
  
  @Test(expected = Exception.class)
  public void canNotChangeSimulator() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    OspSimulator simNew = new OspSimulator("name", "source");
    
    s.setOspSimulator(sim);
    
    s.setOspSimulator(simNew);
  }
  
  @Test
  public void canAddVariable() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    s.setOspSimulator(sim);
    OspVariable v = new OspVariable("name");
    
    s.addVariable(v);
    
    assertTrue(s.getVariables().containsValue(v));
    assertEquals(s, v.getOspSocket());
  }
  
  @Test
  public void addingVariableAddsSimulatorToVariable() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    s.setOspSimulator(sim);
    OspVariable v = new OspVariable("name");
    
    s.addVariable(v);
   
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test
  public void addingVariableAddsSimulatorToSocket() {
    OspSocket s = new OspSocket("type", "name");
    OspVariable v = new OspVariable("name");
    OspSimulator sim = new OspSimulator("name", "source");
    v.setOspSimulator(sim);
    
    s.addVariable(v);
   
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableWithDifferentSimulator() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    s.setOspSimulator(sim1);
    OspVariable v = new OspVariable("name");
    OspSimulator sim2 = new OspSimulator("name", "source");
    v.setOspSimulator(sim2);
  
    s.addVariable(v);
  }
  
  @Test
  public void canAddBond() {
    OspSocket s = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    
    s.addBond(b);
  
    assertEquals(b, s.getOspBond());
    assertTrue(b.getOspSockets().contains(s));
  }
  
  @Test
  public void addingBondAddsSimulatorToSocket() {
    OspSocket s = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source");
    b.setOspSimulator(sim);
    
    s.addBond(b);
    
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test
  public void addingBondAddsSimulatorToBond() {
    OspSocket s = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source");
    s.setOspSimulator(sim);
    
    s.addBond(b);
    
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddBondWithDifferentSimulator() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    s.setOspSimulator(sim1);
    OspBond b = new OspBond("name");
    OspSimulator sim2 = new OspSimulator("name", "source");
    b.setOspSimulator(sim2);
    
    s.addBond(b);
  }
  
  @Test(expected = Exception.class)
  public void oneSocketCanNotAddTwoBonds() {
    OspBond b1 = new OspBond("name");
    OspBond b2 = new OspBond("name");
    OspSocket s = new OspSocket("type", "name");
    s.addBond(b1);
    assertEquals(b1, s.getOspBond());
    
    s.addBond(b2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoVariablesWithSameName() {
    OspSocket s = new OspSocket("type", "name");
    OspVariable v1 = new OspVariable("name");
    OspVariable v2 = new OspVariable("name");
    
    s.addVariable(v1);
    assertTrue(s.getVariables().containsValue(v1));
    
    s.addVariable(v2);
  }
}