/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation.variablegroup;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableGroup_4;

public class VE_VariableGroup_4 extends ValidationError<VariableGroup> {

  public boolean useOntology = true;

  @Override
  protected List<VariableGroup> getInvalidObjects () {
    if (this.useOntology){
      OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableGroup_4, context.owl.prefixManager);
      Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
      return invalidIndividuals.stream().map(individual -> context.variableGroups.get(individual)).collect(Collectors.toList());

    }
    else{
      List<VariableGroup> invalidObjects = new ArrayList<>();

      for (VariableGroup vg : context.variableGroups.values()) {
        List<Variable.Axis> axes = vg.getVariables().stream()
                .map(Variable::getAxis)
                .filter(axis -> axis != Variable.Axis.UNDEFINED)
                .collect(Collectors.toList());

        if (axes.size() != axes.stream().distinct().count()) {
          invalidObjects.add(vg);

        }
      }

      return invalidObjects;
    }


  }



  @Override
  protected String getErrorMessage(VariableGroup variableGroup) {
    return "VariableGroup '" + variableGroup.getName().getId().toString() + "' contains variables with identical axis properties";
  }
}
