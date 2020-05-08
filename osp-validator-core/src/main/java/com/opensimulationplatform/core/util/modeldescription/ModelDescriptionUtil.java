package com.opensimulationplatform.core.util.modeldescription;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModelDescriptionUtil {
  public static Unit getUnitByName(ModelDescription modelDescription, String name) {
    List<Unit> units = modelDescription.getUnits();
    Optional<Unit> unitOptional = units.stream().filter(unit -> unit.getName().get().equals(name)).findAny();
    return unitOptional.orElse(null);
  }

  public static Variable getVariableByName(ModelDescription modelDescription, String name) {
    List<Variable> variables = modelDescription.getVariables();
    Optional<Variable> optional = variables.stream().filter(variable -> variable.getName().get().equals(name)).findAny();
    return optional.orElse(null);
  }

  public static VariableGroup getVariableGroupByName(ModelDescription modelDescription, String name) {
    List<VariableGroup> variableGroups = modelDescription.getVariableGroups();
    Optional<VariableGroup> optional = variableGroups.stream().filter(vg -> vg.getName().get().equals(name)).findAny();
    return optional.orElse(null);
  }

  public static List<VariableGroup> getVariableGroups(ModelDescription modelDescription) {
    List<VariableGroup> variableGroups = new ArrayList<>();

    variableGroups.addAll(modelDescription.getGenerics());
    variableGroups.addAll(modelDescription.getAngularDisplacements());
    variableGroups.addAll(modelDescription.getCharges());
    variableGroups.addAll(modelDescription.getLinearDisplacements());
    variableGroups.addAll(modelDescription.getVolumes());
    variableGroups.addAll(modelDescription.getForces());
    variableGroups.addAll(modelDescription.getPressures());
    variableGroups.addAll(modelDescription.getTorques());
    variableGroups.addAll(modelDescription.getVoltages());
    variableGroups.addAll(modelDescription.getAngularVelocities());
    variableGroups.addAll(modelDescription.getCurrents());
    variableGroups.addAll(modelDescription.getLinearVelocities());
    variableGroups.addAll(modelDescription.getVolumeFlowRates());
    variableGroups.addAll(modelDescription.getAngularMechanicalPorts());
    variableGroups.addAll(modelDescription.getElectromagneticPorts());
    variableGroups.addAll(modelDescription.getHydraulicPorts());
    variableGroups.addAll(modelDescription.getLinearMechanicalPorts());
    variableGroups.addAll(modelDescription.getAngularMechanicalQuasiPorts());
    variableGroups.addAll(modelDescription.getElectromagneticQuasiPorts());
    variableGroups.addAll(modelDescription.getHydraulicQuasiPorts());
    variableGroups.addAll(modelDescription.getLinearMechanicalQuasiPorts());

    return variableGroups;
  }
}
