package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.validator.model.ospmodeldescription.Bond;
import com.opensimulationplatform.validator.model.ospmodeldescription.Plug;
import com.opensimulationplatform.validator.model.ospmodeldescription.Simulator;
import com.opensimulationplatform.validator.model.ospmodeldescription.Variable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlugTest {
  @Test
  public void canCreatePlug() {
    Plug p = new Plug("type", "name");
    
    assertEquals("type", p.getType());
    assertEquals("name", p.getName());
  }
  
  @Test
  public void canSetSimulator() {
    Plug p = new Plug("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
  
    p.setSimulator(sim);
    
    assertEquals(sim, p.getSimulator());
    assertTrue(sim.getPlugs().containsValue(p));
  }
  
  @Test(expected = Exception.class)
  public void canNotChangeSimulator() {
    Plug p = new Plug("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    Simulator simNew = new Simulator("name", "source", "modelDefinition");
    
    p.setSimulator(sim);
    
    p.setSimulator(simNew);
  }
  
  @Test
  public void canAddVariable() {
    Plug p = new Plug("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim);
    Variable v = new Variable("name");
    
    p.addVariable(v);
    
    assertTrue(p.getVariables().containsValue(v));
    assertTrue(v.getPlugs().containsValue(p));
  }
  
  @Test
  public void addingVariableAddsSimulatorToVariable() {
    Plug p = new Plug("type", "name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim);
    Variable v = new Variable("name");
    
    p.addVariable(v);
   
    assertEquals(sim, v.getSimulator());
  }
  
  @Test
  public void addingVariableAddsSimulatorToPlug() {
    Plug p = new Plug("type", "name");
    Variable v = new Variable("name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    v.setSimulator(sim);
    
    p.addVariable(v);
   
    assertEquals(sim, p.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddVariableWithDifferentSimulator() {
    Plug p = new Plug("type", "name");
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim1);
    Variable v = new Variable("name");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    v.setSimulator(sim2);
  
    p.addVariable(v);
  }
  
  @Test
  public void canAddBond() {
    Plug p = new Plug("type", "name");
    Bond b = new Bond("name");
    
    p.addBond(b);
    
    assertTrue(p.getBonds().containsValue(b));
    assertTrue(b.getPlugs().contains(p));
  }
  
  @Test
  public void addingBondAddsSimulatorToPlug() {
    Plug p = new Plug("type", "name");
    Bond b = new Bond("name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    b.setSimulator(sim);
  
    p.addBond(b);
    
    assertEquals(sim, p.getSimulator());
  }
  
  @Test
  public void addingBondAddsSimulatorToBond() {
    Plug p = new Plug("type", "name");
    Bond b = new Bond("name");
    Simulator sim = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim);
  
    p.addBond(b);
  
    assertEquals(sim, b.getSimulator());
  }
  
  @Test(expected = Exception.class)
  public void canNotAddBondWithDifferentSimulator() {
    Plug p = new Plug("type", "name");
    Simulator sim1 = new Simulator("name", "source", "modelDefinition");
    p.setSimulator(sim1);
    Bond b = new Bond("name");
    Simulator sim2 = new Simulator("name", "source", "modelDefinition");
    b.setSimulator(sim2);
    
    p.addBond(b);
  }
  
  @Test
  public void onePlugCanAddTwoBonds() {
    Plug p = new Plug("type", "name");
    Bond b1 = new Bond("name1");
    Bond b2 = new Bond("name2");
    
    p.addBond(b1);
    p.addBond(b2);
    
    assertTrue(p.getBonds().containsValue(b1));
    assertTrue(p.getBonds().containsValue(b2));
  }
  
  @Test(expected = Exception.class)
  public void onePlugCanNotAddTwoBondsWithSameName() {
    Plug p = new Plug("type", "name");
    Bond b1 = new Bond("name");
    Bond b2 = new Bond("name");
  
    p.addBond(b1);
    assertTrue(p.getBonds().containsValue(b1));
   
    p.addBond(b2);
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoVariablesWithSameName() {
    Plug p = new Plug("type", "name");
    Variable v1 = new Variable("name");
    Variable v2 = new Variable("name");
    
    p.addVariable(v1);
    assertTrue(p.getVariables().containsValue(v1));
    
    p.addVariable(v2);
  }
}