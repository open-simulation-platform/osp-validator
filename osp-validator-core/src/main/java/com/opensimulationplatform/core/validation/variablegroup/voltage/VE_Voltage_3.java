/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.voltage;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_Voltage_3;

public class VE_Voltage_3 extends ValidationError<Voltage> {
  @Override
  protected List<Voltage> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_Voltage_3, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (Voltage) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(Voltage voltage) {
    return "Voltage variable group '" + voltage.getName().getId().toString() + "' contains variables with incompatible units";
  }
}
