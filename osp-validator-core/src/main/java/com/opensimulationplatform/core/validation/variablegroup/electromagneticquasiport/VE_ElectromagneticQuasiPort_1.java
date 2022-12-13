/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_ElectromagneticQuasiPort_1;

public class VE_ElectromagneticQuasiPort_1 extends ValidationError<ElectromagneticQuasiPort> {
  @Override
  protected List<ElectromagneticQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_ElectromagneticQuasiPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (ElectromagneticQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(ElectromagneticQuasiPort electromagneticQuasiPort) {
     return "ElectromagneticQuasiPort '" + electromagneticQuasiPort.getName().getId().toString() + "' contains incompatible Voltage(" +
             electromagneticQuasiPort.getVoltage().getName().getId().toString() + ") and Charge(" +
             electromagneticQuasiPort.getCharge().getName().getId().toString() + "). " +
             "The variables in the Voltage variable group must have opposite causality of the variables in the Charge variable group";
  }
}
