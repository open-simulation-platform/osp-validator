/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableGroupConnection_1;

public class VE_VariableGroupConnection_1 extends ValidationError<VariableGroupConnection> {
  @Override
  protected List<VariableGroupConnection> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableGroupConnection_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(context.variableGroupConnections::get).filter(Objects::nonNull).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VariableGroupConnection variableGroupConnection) {
    String variableGroupA = variableGroupConnection.getVariableGroupA().getName().getId().toString();
    String typeA = variableGroupConnection.getVariableGroupA().getClass().getSimpleName();
    String variableGroupB = variableGroupConnection.getVariableGroupB().getName().getId().toString();
    String typeB = variableGroupConnection.getVariableGroupB().getClass().getSimpleName();
    return "VariableGroupConnection [" + variableGroupA + ", " + variableGroupB + "] is invalid because they have " +
        "different types [" + typeA + ", " + typeB + "]";
  }
}
