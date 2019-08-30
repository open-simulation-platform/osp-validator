package com.opensimulationplatform.core.model.configuration;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspSimulatorTest {
  @Test
  public void canCreateSimulator() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    
    assertEquals("name", sim.getName());
    assertEquals("source", sim.getSource());
    assertEquals("modelDefinition", sim.getModelDefinition());
  }
  
  @Test
  public void canAddVariable() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
    
    sim.addVariable(v);
  
    assertTrue(sim.getVariables().containsValue(v));
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameVariable() {
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
  
    sim1.addVariable(v);
    sim2.addVariable(v);
  }
  
  @Test
  public void canAddPlug() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspPlug p = new OspPlug("type", "name");
  
    sim.addPlug(p);
    
    assertTrue(sim.getPlugs().containsValue(p));
    assertEquals(sim, p.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSamePlug() {
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    OspPlug p = new OspPlug("type", "name");
  
    sim1.addPlug(p);
    sim2.addPlug(p);
  }
  
  @Test
  public void canAddSocket() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspSocket s = new OspSocket("type", "name");
    
    sim.addSocket(s);
    
    assertTrue(sim.getSockets().containsValue(s));
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameSocket() {
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    OspSocket s = new OspSocket("type", "name");
    
    sim1.addSocket(s);
    sim2.addSocket(s);
  }
  
  @Test
  public void canAddBond() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspBond s = new OspBond("name");
    
    sim.addBond(s);
    
    assertTrue(sim.getBonds().containsValue(s));
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void twoSimulatorsCanNotAddSameBond() {
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    OspBond s = new OspBond("name");
    
    sim1.addBond(s);
    sim2.addBond(s);
  }
  
  @Test
  public void addingBondWithPlugWithVariablePropagatesSimulator() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
    OspPlug p = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    p.addVariable(v);
    b.addPlug(p);
    
    sim.addBond(b);
    
    assertEquals(sim, b.getOspSimulator());
    assertEquals(sim, p.getOspSimulator());
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test
  public void addingBondWithSocketWithVariablePropagatesSimulator() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
    OspSocket s = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    s.addVariable(v);
    b.addSocket(s);
    
    sim.addBond(b);
    
    assertEquals(sim, b.getOspSimulator());
    assertEquals(sim, s.getOspSimulator());
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test
  public void addingVariableInSocketInBondPropagatesSimulator() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
    OspSocket s = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    s.addVariable(v);
    b.addSocket(s);
    
    sim.addVariable(v);
  
    assertEquals(sim, v.getOspSimulator());
    assertEquals(sim, s.getOspSimulator());
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test
  public void addingVariableInPlugInBondPropagatesSimulator() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v = new OspVariable("name");
    OspPlug p = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    p.addVariable(v);
    b.addPlug(p);
    
    sim.addVariable(v);
    
    assertEquals(sim, v.getOspSimulator());
    assertEquals(sim, p.getOspSimulator());
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoVariablesWithSameName() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspVariable v1 = new OspVariable("name");
    OspVariable v2 = new OspVariable("name");
    
    sim.addVariable(v1);
    assertTrue(sim.getVariables().containsValue(v1));
    
    sim.addVariable(v2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoPlugsWithSameName() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspPlug p1 = new OspPlug("type", "name");
    OspPlug p2 = new OspPlug("type", "name");
    
    sim.addPlug(p1);
    assertTrue(sim.getPlugs().containsValue(p1));
    
    sim.addPlug(p2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoSocketsWithSameName() {
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspSocket s1 = new OspSocket("type", "name");
    OspSocket s2 = new OspSocket("type", "name");
    
    sim.addSocket(s1);
    assertTrue(sim.getSockets().containsValue(s1));
    
    sim.addSocket(s2);
  }
}