package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.configuration.OspSimulator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspPlugTest {
  @Test
  public void canCreatePlug() {
    OspPlug p = new OspPlug("type", "name");
    
    assertEquals("type", p.getType());
    assertEquals("name", p.getName());
  }
  
  @Test
  public void canSetSimulator() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
  
    p.setOspSimulator(sim);
    
    assertEquals(sim, p.getOspSimulator());
    assertTrue(sim.getPlugs().containsValue(p));
  }
  
  @Test(expected = Exception.class)
  public void canNotChangeSimulator() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator simNew = new OspSimulator("name", "source", "modelDefinition");
    
    p.setOspSimulator(sim);
    
    p.setOspSimulator(simNew);
  }
  
  @Test
  public void canAddVariable() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim);
    OspVariable v = new OspVariable("name");
    
    p.addVariable(v);
    
    assertTrue(p.getVariables().containsValue(v));
    assertTrue(v.getPlugs().containsValue(p));
  }
  
  @Test
  public void addingVariableAddsSimulatorToVariable() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim);
    OspVariable v = new OspVariable("name");
    
    p.addVariable(v);
   
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test
  public void addingVariableAddsSimulatorToPlug() {
    OspPlug p = new OspPlug("type", "name");
    OspVariable v = new OspVariable("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    v.setOspSimulator(sim);
    
    p.addVariable(v);
   
    assertEquals(sim, p.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableWithDifferentSimulator() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim1);
    OspVariable v = new OspVariable("name");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    v.setOspSimulator(sim2);
  
    p.addVariable(v);
  }
  
  @Test
  public void canAddBond() {
    OspPlug p = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    
    p.addBond(b);
    
    assertTrue(p.getBonds().containsValue(b));
    assertTrue(b.getOspPlugs().contains(p));
  }
  
  @Test
  public void addingBondAddsSimulatorToPlug() {
    OspPlug p = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    b.setOspSimulator(sim);
  
    p.addBond(b);
    
    assertEquals(sim, p.getOspSimulator());
  }
  
  @Test
  public void addingBondAddsSimulatorToBond() {
    OspPlug p = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim);
  
    p.addBond(b);
  
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddBondWithDifferentSimulator() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim1);
    OspBond b = new OspBond("name");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    b.setOspSimulator(sim2);
    
    p.addBond(b);
  }
  
  @Test
  public void onePlugCanAddTwoBonds() {
    OspPlug p = new OspPlug("type", "name");
    OspBond b1 = new OspBond("name1");
    OspBond b2 = new OspBond("name2");
    
    p.addBond(b1);
    p.addBond(b2);
    
    assertTrue(p.getBonds().containsValue(b1));
    assertTrue(p.getBonds().containsValue(b2));
  }
  
  @Test(expected = Exception.class)
  public void onePlugCanNotAddTwoBondsWithSameName() {
    OspPlug p = new OspPlug("type", "name");
    OspBond b1 = new OspBond("name");
    OspBond b2 = new OspBond("name");
  
    p.addBond(b1);
    assertTrue(p.getBonds().containsValue(b1));
   
    p.addBond(b2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoVariablesWithSameName() {
    OspPlug p = new OspPlug("type", "name");
    OspVariable v1 = new OspVariable("name");
    OspVariable v2 = new OspVariable("name");
    
    p.addVariable(v1);
    assertTrue(p.getVariables().containsValue(v1));
    
    p.addVariable(v2);
  }
}