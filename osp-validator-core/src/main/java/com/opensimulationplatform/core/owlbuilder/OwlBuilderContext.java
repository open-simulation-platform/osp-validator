/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OwlBuilderContext {
  public OWLConfig owl;

  public Set<OWLAxiom> axioms = new HashSet<>();
  public Set<OWLIndividual> individuals = new HashSet<>();

  public Map<OWLClass, Set<OWLNamedIndividual>> invalidIndividuals = new HashMap<>();

  public Map<OWLIndividual, Name> names = new HashMap<>();
  public Map<OWLIndividual, Unit> units = new HashMap<>();
  public Map<OWLIndividual, Variable> variables = new HashMap<>();
  public Map<OWLIndividual, VariableGroup> variableGroups = new HashMap<>();
  public Map<OWLIndividual, Simulator> simulators = new HashMap<>();
  public Map<OWLIndividual, VariableConnection> variableConnections = new HashMap<>();
  public Map<OWLIndividual, VariableGroupConnection> variableGroupConnections = new HashMap<>();
  public Map<OWLIndividual, SystemStructure> systemStructures = new HashMap<>();
}
