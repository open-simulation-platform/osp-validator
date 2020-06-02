/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup.lineardisplacement;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearDisplacement_2;

public class VE_LinearDisplacement_2 extends ValidationError<LinearDisplacement> {
  @Override
  protected List<LinearDisplacement> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearDisplacement_2, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (LinearDisplacement) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearDisplacement linearDisplacement) {
    return "LinearDisplacement variable group '" + linearDisplacement.getName().getId().get() + "' contains variables with mixed data types";
  }
}
