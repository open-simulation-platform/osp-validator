package com.opensimulationplatform.core.validator.modeldescription;

import com.opensimulationplatform.core.model.modeldescription.*;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Collections.singleton;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModelDescriptionValidatorTest {
  @Test
  public void emptyModelDescriptionIsValid() {
    ModelDescription modelDescription = new ModelDescription();
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void descriptionWithPlugInOntologyIsValid() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspPlugs(new ArrayList<>(singleton(new OspPlug(OntologyClasses.FORCE, "name"))));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(String.valueOf(result.getMessages()), result.isValid());
  }
  
  @Test
  public void descriptionWithPlugNotInOntologyIsInvalid() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspPlugs(new ArrayList<>(singleton(new OspPlug("this-does-not-exist", "name"))));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
  @Test
  public void descriptionWithSocketInOntologyIsValid() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspSockets(new ArrayList<>(singleton(new OspSocket(OntologyClasses.FORCE, "name"))));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void descriptionWithSocketNotInOntologyIsInvalid() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspSockets(new ArrayList<>(singleton(new OspSocket("this-does-not-exist", "name"))));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
  @Test
  public void descriptionWithBondContainingExistingPlugIsValid() {
    ModelDescription modelDescription = new ModelDescription();
    
    OspPlug ospPlug = new OspPlug(OntologyClasses.FORCE, "name");
    modelDescription.setOspPlugs(new ArrayList<>(singleton(ospPlug)));
    
    OspBond ospBond = new OspBond("name");
    ospBond.addPlug(ospPlug);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingPlugIsInvalid() {
    ModelDescription modelDescription = new ModelDescription();
    
    OspPlug existingOspPlug = new OspPlug(OntologyClasses.FORCE, "existingPlug");
    modelDescription.setOspPlugs(new ArrayList<>(singleton(existingOspPlug)));
    
    OspPlug nonExistingOspPlug = new OspPlug(OntologyClasses.FORCE, "nonExistingPlug");
    OspBond ospBond = new OspBond("name");
    ospBond.addPlug(nonExistingOspPlug);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
  @Test
  public void descriptionWithBondContainingExistingSocketIsValid() {
    ModelDescription modelDescription = new ModelDescription();
    
    OspSocket ospSocket = new OspSocket(OntologyClasses.FORCE, "name");
    modelDescription.setOspSockets(new ArrayList<>(singleton(ospSocket)));
    
    OspBond ospBond = new OspBond("name");
    ospBond.addSocket(ospSocket);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingSocketIsInvalid() {
    ModelDescription modelDescription = new ModelDescription();
    
    OspSocket existingOspSocket = new OspSocket(OntologyClasses.FORCE, "existingSocket");
    modelDescription.setOspSockets(new ArrayList<>(singleton(existingOspSocket)));
    
    OspSocket nonExistingOspSocket = new OspSocket(OntologyClasses.FORCE, "nonExistingSocket");
    OspBond ospBond = new OspBond("name");
    ospBond.addSocket(nonExistingOspSocket);
    modelDescription.setOspBonds(new ArrayList<>(singleton(ospBond)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
  @Test
  public void allVariablesInPlugExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    ModelDescription modelDescription = new ModelDescription(fmiModelDescription);
    OspPlug p = new OspPlug(OntologyClasses.FORCE, "p1");
    p.addVariable(new OspVariable("r1"));
    p.addVariable(new OspVariable("r2"));
    modelDescription.setOspPlugs(new ArrayList<>(singleton(p)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void allVariablesInPlugDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    ModelDescription modelDescription = new ModelDescription(fmiModelDescription);
    OspPlug p = new OspPlug(OntologyClasses.FORCE, "p1");
    p.addVariable(new OspVariable("r1"));
    p.addVariable(new OspVariable("does-not-exist-in-fmi-model-description"));
    modelDescription.setOspPlugs(new ArrayList<>(singleton(p)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
  @Test
  public void allVariablesInSocketExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    ModelDescription modelDescription = new ModelDescription(fmiModelDescription);
    OspSocket s = new OspSocket(OntologyClasses.FORCE, "s1");
    s.addVariable(new OspVariable("r1"));
    s.addVariable(new OspVariable("r2"));
    modelDescription.setOspSockets(new ArrayList<>(singleton(s)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isValid());
  }
  
  @Test
  public void allVariablesInSocketDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = TestSetup.getFmiModelDescription();
    ModelDescription modelDescription = new ModelDescription(fmiModelDescription);
    OspSocket s = new OspSocket(OntologyClasses.FORCE, "s1");
    s.addVariable(new OspVariable("r1"));
    s.addVariable(new OspVariable("does-not-exist-in-fmi-model-description"));
    modelDescription.setOspSockets(new ArrayList<>(singleton(s)));
    
    ModelDescriptionValidator.Result result = ModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isValid());
  }
  
}
