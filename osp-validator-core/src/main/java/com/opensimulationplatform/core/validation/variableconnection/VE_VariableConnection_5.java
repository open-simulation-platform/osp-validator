/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

//import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableConnection_5;

public class VE_VariableConnection_5 extends ValidationError<VariableConnection> {
  @Override
  protected List<VariableConnection> getInvalidObjects() {
//    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableConnection_5, context.owl.prefixManager);
//    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
//    return invalidIndividuals.stream().map(context.variableConnections::get).filter(Objects::nonNull).collect(Collectors.toList());
    return null;
  }

  @Override
  protected String getErrorMessage(VariableConnection variableConnection) {
    String variableA = variableConnection.getVariableA().getName().getId().toString();
    Variable.Type typeA = variableConnection.getVariableA().getType();
    String variableB = variableConnection.getVariableB().getName().getId().toString();
    Variable.Type typeB = variableConnection.getVariableA().getType();
    return "VariableConnection [" + variableA + ", " + variableB + "] is invalid because they have different axis " +
        "[" + typeA + ", " + typeB + "]";
  }
}