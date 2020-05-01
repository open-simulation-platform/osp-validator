package com.opensimulationplatform.modeldescription.xml.converter.ospmodeldescriptiontype;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.modeldescription.util.FmiModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.*;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OspModelDescriptionTypeConverterTest {
  @Test
  public void canConvert() {
    ConverterContext converterContext = getConverterContext();
    OspModelDescriptionType ospModelDescriptionType = getOspModelDescriptionType();

    OspModelDescriptionTypeConverter ospModelDescriptionTypeConverter = new OspModelDescriptionTypeConverter(converterContext);
    ModelDescription modelDescription = ospModelDescriptionTypeConverter.convert(ospModelDescriptionType);

    assertEquals(1, modelDescription.getVariables().size());

    assertEquals(2, modelDescription.getUnits().size());

    assertEquals(1, modelDescription.getGenerics().size());

    assertEquals(3, modelDescription.getForces().size());
    assertEquals(3, modelDescription.getTorques().size());
    assertEquals(3, modelDescription.getVoltages().size());
    assertEquals(3, modelDescription.getPressures().size());

    assertEquals(2, modelDescription.getLinearVelocities().size());
    assertEquals(2, modelDescription.getAngularVelocities().size());
    assertEquals(2, modelDescription.getCurrents().size());
    assertEquals(2, modelDescription.getVolumeFlowRates().size());

    assertEquals(2, modelDescription.getLinearDisplacements().size());
    assertEquals(2, modelDescription.getAngularDisplacements().size());
    assertEquals(2, modelDescription.getCharges().size());
    assertEquals(2, modelDescription.getVolumes().size());

    assertEquals(1, modelDescription.getLinearMechanicalPorts().size());
    assertEquals(1, modelDescription.getAngularMechanicalPorts().size());
    assertEquals(1, modelDescription.getElectromagneticPorts().size());
    assertEquals(1, modelDescription.getHydraulicPorts().size());

    assertEquals(1, modelDescription.getLinearMechanicalQuasiPorts().size());
    assertEquals(1, modelDescription.getAngularMechanicalQuasiPorts().size());
    assertEquals(1, modelDescription.getElectromagneticQuasiPorts().size());
    assertEquals(1, modelDescription.getHydraulicQuasiPorts().size());
  }

  @NotNull
  private ConverterContext getConverterContext() {
    ConverterContext converterContext = new ConverterContext();

    converterContext.fmiModelDescription = new FmiModelDescription();

    Variable fmiVariable = new Variable();
    fmiVariable.setName("fmiVariable");
    converterContext.fmiModelDescription.getVariables().add(fmiVariable);

    Unit fmiUnit = new Unit();
    fmiUnit.setName("fmiUnit");
    converterContext.fmiModelDescription.getUnits().add(fmiUnit);
    return converterContext;
  }

  @NotNull
  private OspModelDescriptionType getOspModelDescriptionType() {
    OspModelDescriptionType ospModelDescriptionType = new OspModelDescriptionType();

    UnitDefinitionsType unitDefinitionsType = new UnitDefinitionsType();
    UnitType unitType = new UnitType();
    Fmi2Unit.BaseUnit baseUnit = new Fmi2Unit.BaseUnit();
    unitType.setBaseUnit(baseUnit);
    unitDefinitionsType.getUnit().add(unitType);
    ospModelDescriptionType.setUnitDefinitions(unitDefinitionsType);

    VariableGroupsType variableGroupsType = new VariableGroupsType();

    VariableType variableType = new VariableType();
    variableType.setRef("fmiVariable");
    variableType.setUnit("fmiUnit");

    GenericType genericType = new GenericType();
    variableGroupsType.getGeneric().add(genericType);

    variableGroupsType.getForce().add(new ForceType());
    variableGroupsType.getTorque().add(new TorqueType());
    variableGroupsType.getVoltage().add(new VoltageType());
    variableGroupsType.getPressure().add(new PressureType());

    variableGroupsType.getLinearVelocity().add(new LinearVelocityType());
    variableGroupsType.getAngularVelocity().add(new AngularVelocityType());
    variableGroupsType.getCurrent().add(new CurrentType());
    variableGroupsType.getVolumeFlowRate().add(new VolumeFlowRateType());

    variableGroupsType.getLinearDisplacement().add(new LinearDisplacementType());
    variableGroupsType.getAngularDisplacement().add(new AngularDisplacementType());
    variableGroupsType.getCharge().add(new ChargeType());
    variableGroupsType.getVolume().add(new VolumeType());

    LinearMechanicalPortType linearMechanicalPortType = new LinearMechanicalPortType();
    linearMechanicalPortType.setForce(new ForceType());
    linearMechanicalPortType.setLinearVelocity(new LinearVelocityType());

    AngularMechanicalPortType angularMechanicalPortType = new AngularMechanicalPortType();
    angularMechanicalPortType.setTorque(new TorqueType());
    angularMechanicalPortType.setAngularVelocity(new AngularVelocityType());

    ElectromagneticPortType electromagneticPortType = new ElectromagneticPortType();
    electromagneticPortType.setVoltage(new VoltageType());
    electromagneticPortType.setCurrent(new CurrentType());

    HydraulicPortType hydraulicPortType = new HydraulicPortType();
    hydraulicPortType.setPressure(new PressureType());
    hydraulicPortType.setVolumeFlowRate(new VolumeFlowRateType());

    variableGroupsType.getLinearMechanicalPort().add(linearMechanicalPortType);
    variableGroupsType.getAngularMechanicalPort().add(angularMechanicalPortType);
    variableGroupsType.getElectromagneticPort().add(electromagneticPortType);
    variableGroupsType.getHydraulicPort().add(hydraulicPortType);

    LinearMechanicalQuasiPortType linearMechanicalQuasiPortType = new LinearMechanicalQuasiPortType();
    linearMechanicalQuasiPortType.setForce(new ForceType());
    linearMechanicalQuasiPortType.setLinearDisplacement(new LinearDisplacementType());

    AngularMechanicalQuasiPortType angularMechanicalQuasiPortType = new AngularMechanicalQuasiPortType();
    angularMechanicalQuasiPortType.setTorque(new TorqueType());
    angularMechanicalQuasiPortType.setAngularDisplacement(new AngularDisplacementType());

    ElectromagneticQuasiPortType electromagneticQuasiPortType = new ElectromagneticQuasiPortType();
    electromagneticQuasiPortType.setVoltage(new VoltageType());
    electromagneticQuasiPortType.setCharge(new ChargeType());

    HydraulicQuasiPortType hydraulicQuasiPortType = new HydraulicQuasiPortType();
    hydraulicQuasiPortType.setPressure(new PressureType());
    hydraulicQuasiPortType.setVolume(new VolumeType());

    variableGroupsType.getLinearMechanicalQuasiPort().add(linearMechanicalQuasiPortType);
    variableGroupsType.getAngularMechanicalQuasiPort().add(angularMechanicalQuasiPortType);
    variableGroupsType.getElectromagneticQuasiPort().add(electromagneticQuasiPortType);
    variableGroupsType.getHydraulicQuasiPort().add(hydraulicQuasiPortType);

    ospModelDescriptionType.setVariableGroups(variableGroupsType);
    return ospModelDescriptionType;
  }
}