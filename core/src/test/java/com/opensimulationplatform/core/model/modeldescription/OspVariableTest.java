package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspVariableTest {
  @Test
  public void canCreateVariable() {
    OspVariable v = new OspVariable("name");
    
    assertEquals("name", v.getName());
  }
  
  @Test
  public void canSetSimulator() {
    OspVariable v = new OspVariable("name");
    OspSimulator sim = new OspSimulator("name", "source");
    
    v.setOspSimulator(sim);
    
    assertEquals(sim, v.getOspSimulator());
    assertTrue(sim.getVariables().containsValue(v));
  }
  
  @Test(expected = RuntimeException.class)
  public void canNotChangeSimulator() {
    OspVariable v = new OspVariable("name");
    OspSimulator sim = new OspSimulator("name", "source");
    OspSimulator simNew = new OspSimulator("name", "source");
    
    v.setOspSimulator(sim);
    
    v.setOspSimulator(simNew);
  }
  
  @Test
  public void canAddPlug() {
    OspVariable v = new OspVariable("name");
    OspPlug p = new OspPlug("type", "name");
    
    v.addPlug(p);
    
    assertTrue(v.getPlugs().containsValue(p));
    assertTrue(p.getVariables().containsValue(v));
  }
  
  @Test
  public void canAddSocket() {
    OspVariable v = new OspVariable("name");
    OspSocket s = new OspSocket("type", "name");
    
    v.addSocket(s);
    
    assertEquals(s, v.getOspSocket());
    assertTrue(s.getVariables().containsValue(v));
  }
  
  @Test
  public void addingPlugAddsSimulatorToVariable() {
    OspVariable v = new OspVariable("name");
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    p.setOspSimulator(sim);
    
    v.addPlug(p);
    
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test
  public void addingSocketAddsSimulatorToVariable() {
    OspVariable v = new OspVariable("name");
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    s.setOspSimulator(sim);
    
    v.addSocket(s);
    
    assertEquals(sim, v.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableToPlugWithDifferentSimulator() {
    OspVariable v = new OspVariable("name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    v.setOspSimulator(sim1);
    OspSimulator sim2 = new OspSimulator("name", "source");
    OspPlug p = new OspPlug("type", "name");
    p.setOspSimulator(sim2);
    
    v.addPlug(p);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableToSocketWithDifferentSimulator() {
    OspVariable v = new OspVariable("name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    v.setOspSimulator(sim1);
    OspSimulator sim2 = new OspSimulator("name", "source");
    OspSocket s = new OspSocket("type", "name");
    s.setOspSimulator(sim2);
    
    v.addSocket(s);
  }
  
  @Test
  public void aVariableCanNotBelongToPlugAndSocketAtTheSameTime() {
    OspVariable v = new OspVariable("name");
    OspPlug p = new OspPlug("type", "name");
    OspSocket s = new OspSocket("type", "name");
    boolean first = false;
    
    v.addPlug(p);
    try {
      v.addSocket(s);
    } catch (Exception e) {
      first = true;
    }
    
    v = new OspVariable("name");
    p = new OspPlug("type", "name");
    s = new OspSocket("type", "name");
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
    OspVariable v = new OspVariable("name");
    OspPlug p1 = new OspPlug("type1", "name1");
    OspPlug p2 = new OspPlug("type2", "name2");
    
    v.addPlug(p1);
    v.addPlug(p2);
    
    assertTrue(v.getPlugs().containsValue(p1));
    assertTrue(v.getPlugs().containsValue(p2));
  }
  
  @Test(expected = Exception.class)
  public void oneVariableCanNotAddTwoSockets() {
    OspVariable v = new OspVariable("name");
    OspSocket s1 = new OspSocket("type1", "name1");
    OspSocket s2 = new OspSocket("type2", "name2");
    
    v.addSocket(s1);
    assertEquals(s1, v.getOspSocket());
    
    v.addSocket(s2);
  }
}