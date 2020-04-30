package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VariableGroup;
import static com.opensimulationplatform.gen.owl.model.OntologyIndividuals.*;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.*;

public class VariableGroupOwlBuilder extends OspOwlBuilder<VariableGroup> {

  Map<Class<? extends VariableGroup>, String> typeMap = new HashMap<>();

  public VariableGroupOwlBuilder() {
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
    typeMap.put(LinearMechanicalQuasiPort.class, ind_vgtype_linear_mechanical_quasi_port);
    typeMap.put(AngularMechanicalQuasiPort.class, ind_vgtype_angular_mechanical_quasi_port);
    typeMap.put(ElectromagneticQuasiPort.class, ind_vgtype_electromagnetic_quasi_port);
    typeMap.put(HydraulicQuasiPort.class, ind_vgtype_hydraulic_quasi_port);
  }

  @Override
  public OWLNamedIndividual build(VariableGroup variableGroup) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(variableGroup.getId().get(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.variableGroups.put(individual, variableGroup);

    setClass(individual);
    setName(variableGroup, individual);
    setType(variableGroup, individual);
    setVariables(variableGroup, individual);
    setVariableGroups(variableGroup, individual);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(VariableGroup, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setName(VariableGroup variableGroup, OWLNamedIndividual individual) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder();
    nameOwlBuilder.setContext(context);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(variableGroup.getName());
    OWLObjectProperty hasName = context.owl.dataFactory.getOWLObjectProperty(op_has_name, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasName, individual, nameIndividual);
    context.axioms.add(axiom);
  }

  private void setType(VariableGroup variableGroup, OWLNamedIndividual individual) {
    OWLObjectProperty hasVariableGroupType = context.owl.dataFactory.getOWLObjectProperty(op_has_variable_group_type, context.owl.prefixManager);
    OWLNamedIndividual type = context.owl.dataFactory.getOWLNamedIndividual(typeMap.get(variableGroup.getClass()), context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroupType, individual, type);
    context.axioms.add(axiom);
  }

  private void setVariableGroups(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    OWLObjectProperty hasVariableGroup = context.owl.dataFactory.getOWLObjectProperty(op_has_variable_group, context.owl.prefixManager);
    List<VariableGroup> variableGroups = variableGroup.getVariableGroups();
    for (VariableGroup vg : variableGroups) {
      OWLNamedIndividual vgIndividual = this.build(vg);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroup, variableGroupIndividual, vgIndividual);
      context.axioms.add(axiom);
    }
  }

  private void setVariables(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder();
    variableOwlBuilder.setContext(context);
    OWLObjectProperty hasVariable = context.owl.dataFactory.getOWLObjectProperty(op_has_variable, context.owl.prefixManager);
    List<Variable> variables = variableGroup.getVariables();
    for (Variable v : variables) {
      OWLNamedIndividual variableIndividual = variableOwlBuilder.build(v);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, variableGroupIndividual, variableIndividual);
      context.axioms.add(axiom);
    }
  }
}
