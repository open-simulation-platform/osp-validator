/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearMechanicalQuasiPort_1;

public class VE_LinearMechanicalQuasiPort_1 extends ValidationError<LinearMechanicalQuasiPort> {
  @Override
  protected List<LinearMechanicalQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearMechanicalQuasiPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (LinearMechanicalQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearMechanicalQuasiPort linearMechanicalQuasiPort) {
     return "LinearMechanicalQuasiPort '" + linearMechanicalQuasiPort.getName().getId().toString() + "' contains incompatible Force(" +
             linearMechanicalQuasiPort.getForce().getName().getId().toString() + ") and LinearDisplacement(" +
             linearMechanicalQuasiPort.getLinearDisplacement().getName().getId().toString() + "). " +
             "The variables in the Force variable group must have opposite causality of the variables in the LinearDisplacement variable group";
  }
}
