/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_AngularMechanicalPort_1;

public class VE_AngularMechanicalPort_1 extends ValidationError<AngularMechanicalPort> {
  @Override
  protected List<AngularMechanicalPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_AngularMechanicalPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (AngularMechanicalPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularMechanicalPort angularMechanicalPort) {
     return "AngularMechanicalPort '" + angularMechanicalPort.getName().getId().toString() + "' contains incompatible Torque(" +
             angularMechanicalPort.getTorque().getName().getId().toString() + ") and AngularVelocity(" +
             angularMechanicalPort.getAngularVelocity().getName().getId().toString() + "). " +
             "The variables in the Torque variable group must have opposite causality of the variables in the AngularVelocity variable group";
  }
}
