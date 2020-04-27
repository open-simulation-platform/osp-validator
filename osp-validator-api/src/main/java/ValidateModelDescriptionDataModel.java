import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;

import java.util.List;

public class ValidateModelDescriptionDataModel {
  public static void main(String[] args) {
    ModelDescription modelDescription = new ModelDescription();

    Unit unit = new Unit();
    unit.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setType(Variable.Type.REAL);
    v1.setName("v1");
    v1.setUnit(unit);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setType(Variable.Type.REAL);
    v2.setName("v2");
    v2.setUnit(unit);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.INPUT);
    v3.setType(Variable.Type.REAL);
    v3.setName("v3");
    v3.setUnit(unit);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.INPUT);
    v4.setType(Variable.Type.REAL);
    v4.setName("v4");
    v4.setUnit(unit);

    Force force = new Force();
    force.setName("force");
    force.getVariables().add(v1);
    force.getVariables().add(v2);

    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setName("linearVelocity");
    linearVelocity.getVariables().add(v3);
    linearVelocity.getVariables().add(v4);

    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();
    linearMechanicalPort.setName("linearMechanicalPort");
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    modelDescription.getUnits().add(unit);
    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);
    modelDescription.getVariables().add(v3);
    modelDescription.getVariables().add(v4);
    modelDescription.getForces().add(force);
    modelDescription.getLinearVelocities().add(linearVelocity);
    modelDescription.getLinearMechanicalPorts().add(linearMechanicalPort);


    ModelDescriptionValidator validator = new ModelDescriptionValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(modelDescription);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }
}
