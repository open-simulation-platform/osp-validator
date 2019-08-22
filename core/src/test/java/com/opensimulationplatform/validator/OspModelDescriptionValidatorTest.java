package com.opensimulationplatform.validator;

import com.opensimulationplatform.osp.model.OspOntologyClasses;
import com.opensimulationplatform.validator.model.ospmodeldescription.Bond;
import com.opensimulationplatform.validator.model.ospmodeldescription.OspModelDescription;
import com.opensimulationplatform.validator.model.ospmodeldescription.Plug;
import com.opensimulationplatform.validator.model.ospmodeldescription.Socket;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OspModelDescriptionValidatorTest {
/*  @Test
  public void validConfigurationIsProperlyValidated() {
    ModelDefinitionValidator.Result result = ModelDefinitionValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/model-definition-valid.json"));
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void invalidConfigurationsAreProperlyValidated() {
    ModelDefinitionValidator.Result result = ModelDefinitionValidator.validate(new File("./src/main/resources/osp.owl"), new File("./src/test/resources/validator/model-definition-invalid.json"));
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void canUseDefaultOwlFile() {
    ModelDefinitionValidator.Result result = ModelDefinitionValidator.validate(new File("./src/test/resources/validator/model-definition-valid.json"));
    assertTrue(result.isSuccess());
  }*/
  
  @Test
  public void emptyModelDescriptionIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithPlugInOntologyIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setPlugs(new ArrayList<>(Collections.singleton(new Plug(OspOntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithPlugNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setPlugs(new ArrayList<>(Collections.singleton(new Plug("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketInOntologyIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(Collections.singleton(new Socket(OspOntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(Collections.singleton(new Socket("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingPlugIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Plug plug = new Plug(OspOntologyClasses.FORCE, "name");
    modelDescription.setPlugs(new ArrayList<>(Collections.singleton(plug)));
    
    Bond bond = new Bond("name");
    bond.addPlug(plug);
    modelDescription.setBonds(new ArrayList<>(Collections.singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingPlugIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Plug existingPlug = new Plug(OspOntologyClasses.FORCE, "existingPlug");
    modelDescription.setPlugs(new ArrayList<>(Collections.singleton(existingPlug)));
    
    Plug nonExistingPlug = new Plug(OspOntologyClasses.FORCE, "nonExistingPlug");
    Bond bond = new Bond("name");
    bond.addPlug(nonExistingPlug);
    modelDescription.setBonds(new ArrayList<>(Collections.singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingSocketIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Socket socket = new Socket(OspOntologyClasses.FORCE, "name");
    modelDescription.setSockets(new ArrayList<>(Collections.singleton(socket)));
    
    Bond bond = new Bond("name");
    bond.addSocket(socket);
    modelDescription.setBonds(new ArrayList<>(Collections.singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingSocketIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Socket existingSocket = new Socket(OspOntologyClasses.FORCE, "existingSocket");
    modelDescription.setSockets(new ArrayList<>(Collections.singleton(existingSocket)));
    
    Socket nonExistingSocket = new Socket(OspOntologyClasses.FORCE, "nonExistingSocket");
    Bond bond = new Bond("name");
    bond.addSocket(nonExistingSocket);
    modelDescription.setBonds(new ArrayList<>(Collections.singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void plugsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setPlugs(new ArrayList<>(Arrays.asList(new Plug(OspOntologyClasses.FORCE, "not-a-unique-name"), new Plug(OspOntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void socketsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(Arrays.asList(new Socket(OspOntologyClasses.FORCE, "not-a-unique-name"), new Socket(OspOntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void bondsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setBonds(new ArrayList<>(Arrays.asList(new Bond("not-a-unique-name"), new Bond("not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
}
