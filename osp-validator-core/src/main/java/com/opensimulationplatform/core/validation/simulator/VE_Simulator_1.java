/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.simulator;

import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_Simulator_1;

public class VE_Simulator_1 extends ValidationError<Simulator> {
  @Override
  protected List<Simulator> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_Simulator_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(context.simulators::get).filter(Objects::nonNull).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(Simulator simulator) {
    return "Simulator '" + simulator.getName().getId().get() + "' does not have a unique name";
  }
}
