package com.opensimulationplatform.core.validator.modeldescription;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiScalarVariable;

public class TestSetup {
  static FmiModelDescription getFmiModelDescription() {
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
