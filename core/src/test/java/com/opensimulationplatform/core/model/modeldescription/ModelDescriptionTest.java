package com.opensimulationplatform.core.model.modeldescription;

import org.junit.Test;

import static java.util.Arrays.asList;

public class ModelDescriptionTest {
  @Test(expected = Exception.class)
  public void canNotAddTwoPlugsWithSameName() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspPlugs(asList(new OspPlug("type", "name"), new OspPlug("type", "name")));
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoSocketsWithSameName() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspSockets(asList(new OspSocket("type", "name"), new OspSocket("type", "name")));
  }
  
  @Test(expected = Exception.class)
  public void canNotAddTwoBondsWithSameName() {
    ModelDescription modelDescription = new ModelDescription();
    modelDescription.setOspBonds(asList(new OspBond("name"), new OspBond("name")));
  }
}