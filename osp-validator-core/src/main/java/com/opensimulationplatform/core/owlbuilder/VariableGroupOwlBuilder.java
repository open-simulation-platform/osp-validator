package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.VolumeFlowRate;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opensimulationplatform.gen.model.OntologyClasses.VariableGroup;
import static com.opensimulationplatform.gen.model.OntologyIndividuals.*;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.*;

public class VariableGroupOwlBuilder extends OspOwlBuilder<VariableGroup> {

  Map<Class<? extends VariableGroup>, String> typeMap = new HashMap<>();

  public VariableGroupOwlBuilder(OWLConfig config) {
    super(config);

    typeMap.put(Generic.class, ind_vgtype_generic);
    typeMap.put(Force.class, ind_vgtype_force);
    typeMap.put(Pressure.class, ind_vgtype_pressure);
    typeMap.put(Torque.class, ind_vgtype_torque);
    typeMap.put(Voltage.class, ind_vgtype_voltage);
    typeMap.put(AngularVelocity.class, ind_vgtype_angular_velocity);
    typeMap.put(Current.class, ind_vgtype_current);
    typeMap.put(LinearVelocity.class, ind_vgtype_linear_velocity);
    typeMap.put(VolumeFlowRate.class, ind_vgtype_volume_flow_rate);
    typeMap.put(AngularDisplacement.class, ind_vgtype_angular_displacement);
    typeMap.put(Charge.class, ind_vgtype_charge);
    typeMap.put(LinearDisplacement.class, ind_vgtype_linear_displacement);
    typeMap.put(Volume.class, ind_vgtype_volume);
    typeMap.put(LinearMechanicalPort.class, ind_vgtype_linear_mechanical_port);
    typeMap.put(AngularMechanicalPort.class, ind_vgtype_angular_mechanical_port);
    typeMap.put(ElectromagneticPort.class, ind_vgtype_electromagnetic_port);
    typeMap.put(HydraulicPort.class, ind_vgtype_hydraulic_port);
  }

  @Override
  public OWLNamedIndividual build(VariableGroup variableGroup) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(variableGroup.getId().get(), prefixManager);

    setClass(individual);
    setName(variableGroup, individual);
    setType(variableGroup, individual);
    setVariables(variableGroup, individual);
    setVariableGroups(variableGroup, individual);
    setDisjointAxioms(individual);

    config.addVariableGroup(individual, variableGroup);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(VariableGroup, prefixManager);
    config.addClassAssertionAxiom(clazz, individual);
  }

  private void setName(VariableGroup variableGroup, OWLNamedIndividual individual) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder(config);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(variableGroup.getName());
    OWLObjectProperty hasName = dataFactory.getOWLObjectProperty(op_has_name, prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasName, nameIndividual);
  }

  private void setType(VariableGroup variableGroup, OWLNamedIndividual individual) {
    OWLObjectProperty hasVariableGroupType = dataFactory.getOWLObjectProperty(op_has_variable_group_type, prefixManager);
    OWLNamedIndividual type = dataFactory.getOWLNamedIndividual(typeMap.get(variableGroup.getClass()), prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasVariableGroupType, type);
  }

  private void setVariableGroups(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    OWLObjectProperty hasVariableGroup = dataFactory.getOWLObjectProperty(op_has_variable_group, prefixManager);
    List<VariableGroup> variableGroups = variableGroup.getVariableGroups();
    variableGroups.stream()
        .map(this::build)
        .forEach(member -> config.addObjectPropertyAssertionAxiom(variableGroupIndividual, hasVariableGroup, member));
  }

  private void setVariables(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder(config);
    OWLObjectProperty hasVariable = dataFactory.getOWLObjectProperty(op_has_variable, prefixManager);
    List<Variable> variables = variableGroup.getVariables();
    variables.stream()
        .map(variableOwlBuilder::build)
        .forEach(member -> config.addObjectPropertyAssertionAxiom(variableGroupIndividual, hasVariable, member));
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(VariableGroup, prefixManager));
  }
}
