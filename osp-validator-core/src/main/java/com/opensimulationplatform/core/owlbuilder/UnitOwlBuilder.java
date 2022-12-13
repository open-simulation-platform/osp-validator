/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.Unit;

public class UnitOwlBuilder extends OspOwlBuilder<Unit> {

  @Override
  public OWLNamedIndividual build(Unit unit) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(unit.getId().toString(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.units.put(individual, unit);

    OWLClass clazz = context.owl.dataFactory.getOWLClass(Unit, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);

    return individual;
  }
}
