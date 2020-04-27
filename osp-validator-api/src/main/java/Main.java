import com.opensimulationplatform.core.model.ospobject.OspObject;
import com.opensimulationplatform.core.validation.result.Diagnostic;
import com.opensimulationplatform.core.validation.result.Result;

import java.io.File;
import java.util.List;

class Main {
  public static void main(String[] args) {
    File file = new File("./src/test/resources/validator/xml/OspSystemStructure-invalid.xml");
  
    Result result = XmlValidator.validate(file);
  
    if (result.isValid()) {
      System.out.println("WOHO!");
    } else {
      List<Diagnostic> diagnostics = result.getDiagnostics();
      for (Diagnostic diagnostic : diagnostics) {
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
