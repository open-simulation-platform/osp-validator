package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
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
    OspSimulator sim = new OspSimulator("name", "source");
    
    b.setOspSimulator(sim);
    
    assertEquals(sim, b.getOspSimulator());
    assertTrue(sim.getBonds().containsValue(b));
  }
  
  @Test(expected = Exception.class)
  public void canNotChangeSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim = new OspSimulator("name", "source");
    OspSimulator simNew = new OspSimulator("name", "source");
    
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
    OspSimulator sim = new OspSimulator("name", "source");
    b.setOspSimulator(sim);
    OspPlug p = new OspPlug("type", "name");
  
    b.addPlug(p);
    
    assertEquals(sim, p.getOspSimulator());
  }
  
  @Test
  public void addingPlugAddsSimulatorToBond() {
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    p.setOspSimulator(sim);
    OspBond b = new OspBond("name");
    
    b.addPlug(p);
    
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddPlugWithDifferentSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    b.setOspSimulator(sim1);
    OspPlug p = new OspPlug("type", "name");
    OspSimulator sim2 = new OspSimulator("name", "source");
    p.setOspSimulator(sim2);
  
    b.addPlug(p);
  }
  
  
  @Test
  public void addingSocketAddsSimulatorToSocket() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    OspBond b = new OspBond("name");
    b.setOspSimulator(sim);
    
    b.addSocket(s);
    
    assertEquals(sim, s.getOspSimulator());
  }
  
  @Test
  public void addingSocketAddsSimulatorToBond() {
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim = new OspSimulator("name", "source");
    s.setOspSimulator(sim);
    OspBond b = new OspBond("name");
    
    b.addSocket(s);
    
    assertEquals(sim, b.getOspSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddSocketWithDifferentSimulator() {
    OspBond b = new OspBond("name");
    OspSimulator sim1 = new OspSimulator("name", "source");
    b.setOspSimulator(sim1);
    OspSocket s = new OspSocket("type", "name");
    OspSimulator sim2 = new OspSimulator("name", "source");
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
  
  @Test(expected = Exception.class)
  public void canNotAddTwoPlugsWithSameName() {
    OspPlug p1 = new OspPlug("type", "name");
    OspPlug p2 = new OspPlug("type", "name");
    OspBond b = new OspBond("name");
    b.addPlug(p1);
    b.addPlug(p2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoSocketsWithSameName() {
    OspSocket p1 = new OspSocket("type", "name");
    OspSocket p2 = new OspSocket("type", "name");
    OspBond b = new OspBond("name");
    b.addSocket(p1);
    b.addSocket(p2);
  }
}