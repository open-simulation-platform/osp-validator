package com.opensimulationplatform.core.validation.variable;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_Variable_4;

public class VE_Variable_4 extends ValidationError<Variable> {
    @Override
    protected List<Variable> getInvalidObjects() {
        OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_Variable_4, context.owl.prefixManager);
        Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
        return invalidIndividuals.stream().map(context.variables::get).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    protected String getErrorMessage(Variable variable) {
        return "Class of variables with more than one unit";
    }
}

