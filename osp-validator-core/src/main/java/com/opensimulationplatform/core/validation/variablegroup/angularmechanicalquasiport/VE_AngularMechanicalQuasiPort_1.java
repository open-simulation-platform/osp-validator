/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_AngularMechanicalQuasiPort_1;

public class VE_AngularMechanicalQuasiPort_1 extends ValidationError<AngularMechanicalQuasiPort> {
  @Override
  protected List<AngularMechanicalQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_AngularMechanicalQuasiPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (AngularMechanicalQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularMechanicalQuasiPort angularMechanicalQuasiPort) {
     return "AngularMechanicalQuasiPort '" + angularMechanicalQuasiPort.getName().getId().toString() + "' contains incompatible Torque(" +
             angularMechanicalQuasiPort.getTorque().getName().getId().toString() + ") and AngularDisplacement(" +
             angularMechanicalQuasiPort.getAngularDisplacement().getName().getId().toString() + "). " +
             "The variables in the Torque variable group must have opposite causality of the variables in the AngularDisplacement variable group";
  }
}
