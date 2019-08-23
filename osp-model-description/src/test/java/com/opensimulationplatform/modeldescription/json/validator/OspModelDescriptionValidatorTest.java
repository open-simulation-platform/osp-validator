package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.*;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.modeldescription.json.TestSetup;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OspModelDescriptionValidatorTest {
  @Test
  public void emptyModelDescriptionIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithPlugInOntologyIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspPlugs(new ArrayList<>(singleton(new OspPlug(OntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithPlugNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspPlugs(new ArrayList<>(singleton(new OspPlug("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketInOntologyIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspSockets(new ArrayList<>(singleton(new OspSocket(OntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspSockets(new ArrayList<>(singleton(new OspSocket("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingPlugIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    OspPlug ospPlug = new OspPlug(OntologyClasses.FORCE, "name");
    modelDescription.setOspPlugs(new ArrayList<>(singleton(ospPlug)));
    
    OspBond ospBond = new OspBond("name");
    ospBond.addPlug(ospPlug);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingPlugIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    OspPlug existingOspPlug = new OspPlug(OntologyClasses.FORCE, "existingPlug");
    modelDescription.setOspPlugs(new ArrayList<>(singleton(existingOspPlug)));
    
    OspPlug nonExistingOspPlug = new OspPlug(OntologyClasses.FORCE, "nonExistingPlug");
    OspBond ospBond = new OspBond("name");
    ospBond.addPlug(nonExistingOspPlug);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingSocketIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    OspSocket ospSocket = new OspSocket(OntologyClasses.FORCE, "name");
    modelDescription.setOspSockets(new ArrayList<>(singleton(ospSocket)));
    
    OspBond ospBond = new OspBond("name");
    ospBond.addSocket(ospSocket);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingSocketIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    OspSocket existingOspSocket = new OspSocket(OntologyClasses.FORCE, "existingSocket");
    modelDescription.setOspSockets(new ArrayList<>(singleton(existingOspSocket)));
    
    OspSocket nonExistingOspSocket = new OspSocket(OntologyClasses.FORCE, "nonExistingSocket");
    OspBond ospBond = new OspBond("name");
    ospBond.addSocket(nonExistingOspSocket);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void plugsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspPlugs(new ArrayList<>(asList(new OspPlug(OntologyClasses.FORCE, "not-a-unique-name"), new OspPlug(OntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void socketsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspSockets(new ArrayList<>(asList(new OspSocket(OntologyClasses.FORCE, "not-a-unique-name"), new OspSocket(OntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void bondsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setOspBonds(new ArrayList<>(asList(new OspBond("not-a-unique-name"), new OspBond("not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void allVariablesInPlugExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    OspPlug p = new OspPlug(OntologyClasses.FORCE, "p1");
    p.addVariable(new OspVariable("r1"));
    p.addVariable(new OspVariable("r2"));
    ospModelDescription.setOspPlugs(new ArrayList<>(singleton(p)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void allVariablesInPlugDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    OspPlug p = new OspPlug(OntologyClasses.FORCE, "p1");
    p.addVariable(new OspVariable("r1"));
    p.addVariable(new OspVariable("does-not-exist-in-fmi-model-description"));
    ospModelDescription.setOspPlugs(new ArrayList<>(singleton(p)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void allVariablesInSocketExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    OspSocket s = new OspSocket(OntologyClasses.FORCE, "s1");
    s.addVariable(new OspVariable("r1"));
    s.addVariable(new OspVariable("r2"));
    ospModelDescription.setOspSockets(new ArrayList<>(singleton(s)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void allVariablesInSocketDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    OspSocket s = new OspSocket(OntologyClasses.FORCE, "s1");
    s.addVariable(new OspVariable("r1"));
    s.addVariable(new OspVariable("does-not-exist-in-fmi-model-description"));
    ospModelDescription.setOspSockets(new ArrayList<>(singleton(s)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertFalse(result.isSuccess());
  }
  
}
