package com.opensimulationplatform.validator;

import com.opensimulationplatform.osp.model.OspOntologyClasses;
import com.opensimulationplatform.validator.model.ospmodeldescription.*;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
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
    modelDescription.setPlugs(new ArrayList<>(singleton(new Plug(OspOntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithPlugNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setPlugs(new ArrayList<>(singleton(new Plug("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketInOntologyIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(singleton(new Socket(OspOntologyClasses.FORCE, "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithSocketNotInOntologyIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(singleton(new Socket("this-does-not-exist", "name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingPlugIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Plug plug = new Plug(OspOntologyClasses.FORCE, "name");
    modelDescription.setPlugs(new ArrayList<>(singleton(plug)));
    
    Bond bond = new Bond("name");
    bond.addPlug(plug);
    modelDescription.setBonds(new ArrayList<>(singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingPlugIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Plug existingPlug = new Plug(OspOntologyClasses.FORCE, "existingPlug");
    modelDescription.setPlugs(new ArrayList<>(singleton(existingPlug)));
    
    Plug nonExistingPlug = new Plug(OspOntologyClasses.FORCE, "nonExistingPlug");
    Bond bond = new Bond("name");
    bond.addPlug(nonExistingPlug);
    modelDescription.setBonds(new ArrayList<>(singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingExistingSocketIsValid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Socket socket = new Socket(OspOntologyClasses.FORCE, "name");
    modelDescription.setSockets(new ArrayList<>(singleton(socket)));
    
    Bond bond = new Bond("name");
    bond.addSocket(socket);
    modelDescription.setBonds(new ArrayList<>(singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void descriptionWithBondContainingNonExistingSocketIsInvalid() {
    OspModelDescription modelDescription = new OspModelDescription();
    
    Socket existingSocket = new Socket(OspOntologyClasses.FORCE, "existingSocket");
    modelDescription.setSockets(new ArrayList<>(singleton(existingSocket)));
    
    Socket nonExistingSocket = new Socket(OspOntologyClasses.FORCE, "nonExistingSocket");
    Bond bond = new Bond("name");
    bond.addSocket(nonExistingSocket);
    modelDescription.setBonds(new ArrayList<>(singleton(bond)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void plugsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setPlugs(new ArrayList<>(asList(new Plug(OspOntologyClasses.FORCE, "not-a-unique-name"), new Plug(OspOntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void socketsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setSockets(new ArrayList<>(asList(new Socket(OspOntologyClasses.FORCE, "not-a-unique-name"), new Socket(OspOntologyClasses.FORCE, "not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void bondsMustHaveUniqueNames() {
    OspModelDescription modelDescription = new OspModelDescription();
    modelDescription.setBonds(new ArrayList<>(asList(new Bond("not-a-unique-name"), new Bond("not-a-unique-name"))));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(modelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void allVariablesInPlugExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    Plug p = new Plug(OspOntologyClasses.FORCE, "p1");
    p.addVariable(new Variable("r1"));
    p.addVariable(new Variable("r2"));
    ospModelDescription.setPlugs(new ArrayList<>(singleton(p)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void allVariablesInPlugDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    Plug p = new Plug(OspOntologyClasses.FORCE, "p1");
    p.addVariable(new Variable("r1"));
    p.addVariable(new Variable("does-not-exist-in-fmi-model-description"));
    ospModelDescription.setPlugs(new ArrayList<>(singleton(p)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @Test
  public void allVariablesInSocketExistInFmiModelDescriptionIsValid() {
    FmiModelDescription fmiModelDescription = getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    Socket s = new Socket(OspOntologyClasses.FORCE, "s1");
    s.addVariable(new Variable("r1"));
    s.addVariable(new Variable("r2"));
    ospModelDescription.setSockets(new ArrayList<>(singleton(s)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertTrue(result.isSuccess());
  }
  
  @Test
  public void allVariablesInSocketDoesNotExistInFmiModelDescriptionIsInvalid() {
    FmiModelDescription fmiModelDescription = getFmiModelDescription();
    OspModelDescription ospModelDescription = new OspModelDescription(fmiModelDescription);
    Socket s = new Socket(OspOntologyClasses.FORCE, "s1");
    s.addVariable(new Variable("r1"));
    s.addVariable(new Variable("does-not-exist-in-fmi-model-description"));
    ospModelDescription.setSockets(new ArrayList<>(singleton(s)));
    
    OspModelDescriptionValidator.Result result = OspModelDescriptionValidator.validate(ospModelDescription);
    
    assertFalse(result.isSuccess());
  }
  
  @NotNull
  private FmiModelDescription getFmiModelDescription() {
    FmiModelDescription fmiModelDescription = new FmiModelDescription();
    FmiModelDescription.ModelVariables mv = new FmiModelDescription.ModelVariables();
    
    FmiScalarVariable v = new FmiScalarVariable();
    v.setReal(new FmiScalarVariable.Real());
    v.setName("r1");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setReal(new FmiScalarVariable.Real());
    v.setName("r2");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setString(new FmiScalarVariable.String());
    v.setName("s1");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setString(new FmiScalarVariable.String());
    v.setName("s2");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setInteger(new FmiScalarVariable.Integer());
    v.setName("i1");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setInteger(new FmiScalarVariable.Integer());
    v.setName("i2");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setBoolean(new FmiScalarVariable.Boolean());
    v.setName("b1");
    mv.getScalarVariable().add(v);
    
    v = new FmiScalarVariable();
    v.setBoolean(new FmiScalarVariable.Boolean());
    v.setName("b2");
    mv.getScalarVariable().add(v);
    
    fmiModelDescription.setModelVariables(mv);
    return fmiModelDescription;
  }
}
