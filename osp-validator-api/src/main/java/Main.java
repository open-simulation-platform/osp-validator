import com.opensimulationplatform.core.model.OspObject;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.xml.validator.XmlValidator;

import java.io.File;
import java.util.List;

class Main {
  public static void main(String[] args) {
    File file = new File("./src/test/resources/validator/xml/OspSystemStructure-invalid.xml");
  
    SystemStructureValidator.Result result = XmlValidator.validate(file);
  
    if (result.isSuccess()) {
      System.out.println("WOHO!");
    } else {
      List<SystemStructureValidator.Diagnostic> diagnostics = result.getDiagnostics();
      for (SystemStructureValidator.Diagnostic diagnostic : diagnostics) {
        System.out.println("Message:");
        String message = diagnostic.getMessage();
        System.out.println(message);
        List<OspObject> invalidObjects = diagnostic.getOspObjects();
        for (OspObject ospObject : invalidObjects) {
          System.out.println(ospObject);
        }
      }
    }
  }
}
