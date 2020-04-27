import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;

import java.io.File;
import java.util.List;

class ValidateOspModelDescriptionFile {
  public static void main(String[] args) {
    File ospModelDescriptionFile = new File("/path/to/OspModelDescription.xml");
    File fmu = new File("/path/to/model.fmu");

    ModelDescription modelDescription = ModelDescriptionFactory.create(ospModelDescriptionFile, fmu);
    ModelDescriptionValidator validator = new ModelDescriptionValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(modelDescription);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }
}
