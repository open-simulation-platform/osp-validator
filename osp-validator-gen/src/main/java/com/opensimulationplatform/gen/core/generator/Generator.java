package com.opensimulationplatform.gen.core.generator;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Generator {

  private static final File srcDir = new File("./osp-validator-core/src/main/java/com/opensimulationplatform/core/validation/variablegroup/");
  private static final File testDir = new File("./osp-validator-core/src/test/java/com/opensimulationplatform/core/validation/variablegroup/");
  private static final File templateDirectory = new File("./osp-validator-gen/src/main/java/com/opensimulationplatform/gen/core/generator/template");

  public static void main(String[] args) throws Exception {
    generatePhysicsVariableGroups();
  }

  private static void generatePhysicsVariableGroups() throws Exception {
    List<String> physicsVariableGroups = new ArrayList<>();

    physicsVariableGroups.add("AngularDisplacement");
    physicsVariableGroups.add("AngularVelocity");
    physicsVariableGroups.add("Torque");
    physicsVariableGroups.add("Charge");
    physicsVariableGroups.add("Current");
    physicsVariableGroups.add("Voltage");
    physicsVariableGroups.add("LinearDisplacement");
    physicsVariableGroups.add("LinearVelocity");
    physicsVariableGroups.add("Force");
    physicsVariableGroups.add("Volume");
    physicsVariableGroups.add("VolumeFlowRate");
    physicsVariableGroups.add("Pressure");

    for (String name : physicsVariableGroups) {
      generateValidationError_1(name);
      generateValidationError_2(name);
      generateValidationError_3(name);

      generateValidator(name);
    }
  }

  private static void generateValidationError_1(String name) throws Exception {
    File srcTemplate = new File(templateDirectory, "/VE_VG_1_Template");
    File testTemplate = new File(templateDirectory, "/VE_VG_1_Test_Template");

    String srcClassName = "VE_" + name + "_1";
    String testClassName = srcClassName + "_Test";

    generateSourceAndTest(name, srcTemplate, testTemplate, srcClassName, testClassName);
  }

  private static void generateValidationError_2(String name) throws Exception {
    File srcTemplate = new File(templateDirectory, "/VE_VG_2_Template");
    File testTemplate = new File(templateDirectory, "/VE_VG_2_Test_Template");

    String srcClassName = "VE_" + name + "_2";
    String testClassName = srcClassName + "_Test";

    generateSourceAndTest(name, srcTemplate, testTemplate, srcClassName, testClassName);
  }

  private static void generateValidationError_3(String name) throws Exception {
    File srcTemplate = new File(templateDirectory, "/VE_VG_3_Template");
    File testTemplate = new File(templateDirectory, "/VE_VG_3_Test_Template");

    String srcClassName = "VE_" + name + "_3";
    String testClassName = srcClassName + "_Test";

    generateSourceAndTest(name, srcTemplate, testTemplate, srcClassName, testClassName);
  }

  private static void generateValidator(String name) throws Exception {
    File srcValidatorTemplate = new File(templateDirectory, "/ValidatorTemplate");
    File testValidatorTemplate = new File(templateDirectory, "/ValidatorTestTemplate");

    String srcClassName = name + "Validator";
    String testClassName = srcClassName + "Test";

    generateSourceAndTest(name, srcValidatorTemplate, testValidatorTemplate, srcClassName, testClassName);
  }

  private static void generateSourceAndTest(String name, File srcTemplate, File testTemplate, String srcClassName, String testClassName) throws Exception {
    String srcContent = getContent(name, srcTemplate, srcClassName);
    String testContent = getContent(name, testTemplate, testClassName);

    File srcPackage = new File(srcDir, name.toLowerCase());
    srcPackage.mkdir();

    File testPackage = new File(testDir, name.toLowerCase());
    testPackage.mkdir();

    File srcFile = new File(srcPackage, srcClassName + ".java");
    File testFile = new File(testPackage, testClassName + ".java");

    Files.write(srcFile.toPath(), srcContent.getBytes());
    Files.write(testFile.toPath(), testContent.getBytes());
  }

  private static String getContent(String name, File template, String className) throws Exception {
    return new String(Files.readAllBytes(template.toPath()), StandardCharsets.UTF_8)
        .replaceAll("\\$PACKAGE_NAME\\$", name.toLowerCase())
        .replaceAll("\\$CORE_MODEL_CLASS_NAME\\$", name)
        .replaceAll("\\$CORE_MODEL_CLASS_NAME_PLURAL\\$", name.replace("Velocity", "Velocitie") + "s")
        .replaceAll("\\$CORE_MODEL_CLASS_NAME_CAMEL_CASE\\$", Character.toLowerCase(name.charAt(0)) + name.substring(1))
        .replaceAll("\\$CLASS_NAME\\$", className);
  }
}
