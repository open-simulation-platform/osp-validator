package com.opensimulationplatform.gen.core.generator;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Generator2 {

  private static final File srcDir = new File("./osp-validator-core/src/main/java/com/opensimulationplatform/core/validation/variablegroup/");
  private static final File testDir = new File("./osp-validator-core/src/test/java/com/opensimulationplatform/core/validation/variablegroup/");
  private static final File templateDirectory = new File("./osp-validator-gen/src/main/java/com/opensimulationplatform/gen/core/generator/template");

  private static class PortInfo{
    public String name;
    public String effort;
    public String flow;

    public PortInfo(String name, String effort, String flow) {
      this.name = name;
      this.effort = effort;
      this.flow = flow;
    }
  }

  public static void main(String[] args) throws Exception {
    List<PortInfo> ports = new ArrayList<>();

    ports.add(new PortInfo("LinearMechanicalPort", "Force", "LinearVelocity"));
    ports.add(new PortInfo("AngularMechanicalPort", "Torque", "AngularVelocity"));
    ports.add(new PortInfo("ElectromagneticPort", "Voltage", "Current"));
    ports.add(new PortInfo("HydraulicPort", "Pressure", "VolumeFlowRate"));
    ports.add(new PortInfo("LinearMechanicalQuasiPort", "Force", "LinearDisplacement"));
    ports.add(new PortInfo("AngularMechanicalQuasiPort", "Torque", "AngularDisplacement"));
    ports.add(new PortInfo("ElectromagneticQuasiPort", "Voltage", "Charge"));
    ports.add(new PortInfo("HydraulicQuasiPort", "Pressure", "Volume"));

    for (PortInfo portInfo : ports) {
      generateValidationError_1(portInfo);
      generateValidator(portInfo);
    }
  }

  private static void generateValidationError_1(PortInfo portInfo) throws Exception {
    File srcTemplate = new File(templateDirectory, "/VE_Port_1_Template");
    File testTemplate = new File(templateDirectory, "/VE_Port_1_Test_Template");

    String srcClassName = "VE_" + portInfo.name + "_1";
    String testClassName = srcClassName + "_Test";

    generateSourceAndTest(portInfo, srcTemplate, testTemplate, srcClassName, testClassName);
  }

  private static void generateValidator(PortInfo portInfo) throws Exception {
    File srcValidatorTemplate = new File(templateDirectory, "/PortValidatorTemplate");
    File testValidatorTemplate = new File(templateDirectory, "/PortValidatorTestTemplate");

    String srcClassName = portInfo.name + "Validator";
    String testClassName = srcClassName + "Test";

    generateSourceAndTest(portInfo, srcValidatorTemplate, testValidatorTemplate, srcClassName, testClassName);
  }

  private static void generateSourceAndTest(PortInfo portInfo, File srcTemplate, File testTemplate, String srcClassName, String testClassName) throws Exception {
    String srcContent = getContent(portInfo, srcTemplate, srcClassName);
    String testContent = getContent(portInfo, testTemplate, testClassName);

    File srcPackage = new File(srcDir, portInfo.name.toLowerCase());
    srcPackage.mkdir();

    File testPackage = new File(testDir, portInfo.name.toLowerCase());
    testPackage.mkdir();

    File srcFile = new File(srcPackage, srcClassName + ".java");
    File testFile = new File(testPackage, testClassName + ".java");

    Files.write(srcFile.toPath(), srcContent.getBytes());
    Files.write(testFile.toPath(), testContent.getBytes());
  }

  private static String getContent(PortInfo portInfo, File template, String className) throws Exception {
    return new String(Files.readAllBytes(template.toPath()), StandardCharsets.UTF_8)
        .replaceAll("\\$PACKAGE_NAME\\$", portInfo.name.toLowerCase())
        .replaceAll("\\$EFFORT_PACKAGE_NAME\\$", portInfo.effort.toLowerCase())
        .replaceAll("\\$FLOW_PACKAGE_NAME\\$", portInfo.flow.toLowerCase())
        .replaceAll("\\$CORE_MODEL_CLASS_NAME\\$", portInfo.name)
        .replaceAll("\\$CORE_MODEL_CLASS_NAME_PLURAL\\$", portInfo.name.replace("Velocity", "Velocitie") + "s")
        .replaceAll("\\$CORE_MODEL_CLASS_NAME_CAMEL_CASE\\$", Character.toLowerCase(portInfo.name.charAt(0)) + portInfo.name.substring(1))
        .replaceAll("\\$EFFORT_CORE_MODEL_CLASS_NAME\\$", portInfo.effort)
        .replaceAll("\\$EFFORT_CORE_MODEL_CLASS_NAME_PLURAL\\$", portInfo.effort.replace("Velocity", "Velocitie") + "s")
        .replaceAll("\\$EFFORT_CORE_MODEL_CLASS_NAME_CAMEL_CASE\\$", Character.toLowerCase(portInfo.effort.charAt(0)) + portInfo.effort.substring(1))
        .replaceAll("\\$FLOW_CORE_MODEL_CLASS_NAME\\$", portInfo.flow)
        .replaceAll("\\$FLOW_CORE_MODEL_CLASS_NAME_PLURAL\\$", portInfo.flow.replace("Velocity", "Velocitie") + "s")
        .replaceAll("\\$FLOW_CORE_MODEL_CLASS_NAME_CAMEL_CASE\\$", Character.toLowerCase(portInfo.flow.charAt(0)) + portInfo.flow.substring(1))
        .replaceAll("\\$CLASS_NAME\\$", className);
  }
}
