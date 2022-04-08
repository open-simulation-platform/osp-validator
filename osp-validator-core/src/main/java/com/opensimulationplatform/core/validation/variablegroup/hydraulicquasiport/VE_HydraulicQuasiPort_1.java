/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.hydraulicquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_HydraulicQuasiPort_1;

public class VE_HydraulicQuasiPort_1 extends ValidationError<HydraulicQuasiPort> {
  @Override
  protected List<HydraulicQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_HydraulicQuasiPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (HydraulicQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(HydraulicQuasiPort hydraulicQuasiPort) {
     return "HydraulicQuasiPort '" + hydraulicQuasiPort.getName().getId().toString() + "' contains incompatible Pressure(" +
             hydraulicQuasiPort.getPressure().getName().getId().toString() + ") and Volume(" +
             hydraulicQuasiPort.getVolume().getName().getId().toString() + "). " +
             "The variables in the Pressure variable group must have opposite causality of the variables in the Volume variable group";
  }
}
