/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_HydraulicPort_1;

public class VE_HydraulicPort_1 extends ValidationError<HydraulicPort> {
  @Override
  protected List<HydraulicPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_HydraulicPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (HydraulicPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(HydraulicPort hydraulicPort) {
     return "HydraulicPort '" + hydraulicPort.getName().getId().get() + "' contains incompatible Pressure(" +
             hydraulicPort.getPressure().getName().getId().get() + ") and VolumeFlowRate(" +
             hydraulicPort.getVolumeFlowRate().getName().getId().get() + "). " +
             "The variables in the Pressure variable group must have opposite causality of the variables in the VolumeFlowRate variable group";
  }
}
