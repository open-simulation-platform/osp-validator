package com.opensimulationplatform.core.validator.model.modeldefinition;

import com.opensimulationplatform.core.validator.model.modeldescription.OspBond;
import com.opensimulationplatform.core.validator.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.validator.model.modeldescription.OspSimulator;
import com.opensimulationplatform.core.validator.model.modeldescription.OspSocket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OspBondTest {
  @Test
  public void canCreateBond() {
    OspBond b = new OspBond("name");
    
    assertEquals("name", b.getName());
  }
  
  @Test
  public void canSetSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    
    b.setOspSimulator(sim);
    
    assertEquals(sim, b.getOspSimulator());
    assertTrue(sim.getBonds().containsValue(b));
  }
  
  @Test(expected = Exception.class)
  public void canNotChangeSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspSimulator simNew = new OspSimulator("name", "source", "modelDefinition");
    
    b.setOspSimulator(sim);
    
    b.setOspSimulator(simNew);
  }
  
  @Test
  public void canAddPlug() {
    OspBond b = new OspBond("name");
    OspPlug p = new OspPlug("type", "name");
    
    b.addPlug(p);
    
    assertTrue(b.getOspPlugs().contains(p));
    assertTrue(p.getBonds().containsValue(b));
  }
  
  @Test
  public void canAddSocket() {
    OspBond b = new OspBond("name");
    OspSocket s = new OspSocket("type", "name");
    
    b.addSocket(s);
    
    assertTrue(b.getOspSockets().contains(s));
    assertEquals(b, s.getOspBond());
  }
  
  @Test
  public void addingSamePlugTwiceDoesNothing() {
    OspBond b = new OspBond("name");
    OspPlug p = new OspPlug("type", "name");
    
    b.addPlug(p);
    assertEquals(1, b.getOspPlugs().size());
    assertTrue(b.getOspPlugs().contains(p));
  
    b.addPlug(p);
    assertEquals(1, b.getOspPlugs().size());
    assertTrue(b.getOspPlugs().contains(p));
  }
  
  @Test
  public void addingSameSocketTwiceDoesNothing() {
    OspBond b = new OspBond("name");
    OspSocket s = new OspSocket("type", "name");
  
    b.addSocket(s);
    assertEquals(1, b.getOspSockets().size());
    assertTrue(b.getOspSockets().contains(s));
  
    b.addSocket(s);
    assertEquals(1, b.getOspSockets().size());
    assertTrue(b.getOspSockets().contains(s));
  }
  
  @Test
  public void addingPlugAddsSimulatorToPlug() {
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    b.setOspSimulator(sim);
    OspPlug p = new OspPlug("type", "name");
  
    b.addPlug(p);
    
    assertEquals(sim, p.getOspSimulator());
  }
  
  @Test
  public void addingPlugAddsSimulatorToBond() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim);
    OspBond b = new OspBond("name");
    
    b.addPlug(p);
    
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddPlugWithDifferentSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    b.setOspSimulator(sim1);
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    p.setOspSimulator(sim2);
  
    b.addPlug(p);
  }
  
  
  @Test
  public void addingSocketAddsSimulatorToSocket() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    OspBond b = new OspBond("name");
    b.setOspSimulator(sim);
    
    b.addSocket(s);
    
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test
  public void addingSocketAddsSimulatorToBond() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source", "modelDefinition");
    s.setOspSimulator(sim);
    OspBond b = new OspBond("name");
    
    b.addSocket(s);
    
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddSocketWithDifferentSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim1 = new OspSimulator("name", "source", "modelDefinition");
    b.setOspSimulator(sim1);
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim2 = new OspSimulator("name", "source", "modelDefinition");
    s.setOspSimulator(sim2);
    
    b.addSocket(s);
  }
  
  @Test
  public void twoBondsCanAddSamePlug() {
    OspBond b1 = new OspBond("name1");
    OspBond b2 = new OspBond("name2");
    OspPlug p = new OspPlug("type", "name");
    b1.addPlug(p);
    b2.addPlug(p);
    
    assertTrue(b1.getOspPlugs().contains(p));
    assertTrue(b2.getOspPlugs().contains(p));
  }
  
  @Test(expected = Exception.class)
  public void twoBondsCanNotAddSameSocket() {
    OspBond b1 = new OspBond("name");
    OspBond b2 = new OspBond("name");
    OspSocket s = new OspSocket("type", "name");
    b1.addSocket(s);
    b2.addSocket(s);
  }
}