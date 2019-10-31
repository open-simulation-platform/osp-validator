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

    assertEquals(1, modelDescription.getForces().size());
    assertEquals(1, modelDescription.getTorques().size());
    assertEquals(1, modelDescription.getVoltages().size());
    assertEquals(1, modelDescription.getPressures().size());

    assertEquals(1, modelDescription.getLinearVelocities().size());
    assertEquals(1, modelDescription.getAngularVelocities().size());
    assertEquals(1, modelDescription.getCurrents().size());
    assertEquals(1, modelDescription.getVolumeFlowRates().size());

    assertEquals(1, modelDescription.getLinearDisplacements().size());
    assertEquals(1, modelDescription.getAngularDisplacements().size());
    assertEquals(1, modelDescription.getCharges().size());
    assertEquals(1, modelDescription.getVolumes().size());

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

    ForceType forceType = new ForceType();
    TorqueType torqueType = new TorqueType();
    VoltageType voltageType = new VoltageType();
    PressureType pressureType = new PressureType();
    variableGroupsType.getForce().add(forceType);
    variableGroupsType.getTorque().add(torqueType);
    variableGroupsType.getVoltage().add(voltageType);
    variableGroupsType.getPressure().add(pressureType);

    LinearVelocityType linearVelocityType = new LinearVelocityType();
    AngularVelocityType angularVelocityType = new AngularVelocityType();
    CurrentType currentType = new CurrentType();
    VolumeFlowRateType volumeFlowRateType = new VolumeFlowRateType();
    variableGroupsType.getLinearVelocity().add(linearVelocityType);
    variableGroupsType.getAngularVelocity().add(angularVelocityType);
    variableGroupsType.getCurrent().add(currentType);
    variableGroupsType.getVolumeFlowRate().add(volumeFlowRateType);

    LinearDisplacementType linearDisplacementType = new LinearDisplacementType();
    AngularDisplacementType angularDisplacementType = new AngularDisplacementType();
    ChargeType chargeType = new ChargeType();
    VolumeType volumeType = new VolumeType();
    variableGroupsType.getLinearDisplacement().add(linearDisplacementType);
    variableGroupsType.getAngularDisplacement().add(angularDisplacementType);
    variableGroupsType.getCharge().add(chargeType);
    variableGroupsType.getVolume().add(volumeType);

    LinearMechanicalPortType linearMechanicalPortType = new LinearMechanicalPortType();
    linearMechanicalPortType.setForce(forceType);
    linearMechanicalPortType.setLinearVelocity(linearVelocityType);

    AngularMechanicalPortType angularMechanicalPortType = new AngularMechanicalPortType();
    angularMechanicalPortType.setTorque(torqueType);
    angularMechanicalPortType.setAngularVelocity(angularVelocityType);

    ElectromagneticPortType electromagneticPortType = new ElectromagneticPortType();
    electromagneticPortType.setVoltage(voltageType);
    electromagneticPortType.setCurrent(currentType);

    HydraulicPortType hydraulicPortType = new HydraulicPortType();
    hydraulicPortType.setPressure(pressureType);
    hydraulicPortType.setVolumeFlowRate(volumeFlowRateType);

    variableGroupsType.getLinearMechanicalPort().add(linearMechanicalPortType);
    variableGroupsType.getAngularMechanicalPort().add(angularMechanicalPortType);
    variableGroupsType.getElectromagneticPort().add(electromagneticPortType);
    variableGroupsType.getHydraulicPort().add(hydraulicPortType);

    LinearMechanicalQuasiPortType linearMechanicalQuasiPortType = new LinearMechanicalQuasiPortType();
    linearMechanicalQuasiPortType.setForce(forceType);
    linearMechanicalQuasiPortType.setLinearDisplacement(linearDisplacementType);

    AngularMechanicalQuasiPortType angularMechanicalQuasiPortType = new AngularMechanicalQuasiPortType();
    angularMechanicalQuasiPortType.setTorque(torqueType);
    angularMechanicalQuasiPortType.setAngularDisplacement(angularDisplacementType);

    ElectromagneticQuasiPortType electromagneticQuasiPortType = new ElectromagneticQuasiPortType();
    electromagneticQuasiPortType.setVoltage(voltageType);
    electromagneticQuasiPortType.setCharge(chargeType);

    HydraulicQuasiPortType hydraulicQuasiPortType = new HydraulicQuasiPortType();
    hydraulicQuasiPortType.setPressure(pressureType);
    hydraulicQuasiPortType.setVolume(volumeType);

    variableGroupsType.getLinearMechanicalQuasiPort().add(linearMechanicalQuasiPortType);
    variableGroupsType.getAngularMechanicalQuasiPort().add(angularMechanicalQuasiPortType);
    variableGroupsType.getElectromagneticQuasiPort().add(electromagneticQuasiPortType);
    variableGroupsType.getHydraulicQuasiPort().add(hydraulicQuasiPortType);

    ospModelDescriptionType.setVariableGroups(variableGroupsType);
    return ospModelDescriptionType;
  }
}