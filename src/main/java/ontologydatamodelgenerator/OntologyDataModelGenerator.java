package ontologydatamodelgenerator;

import ontologydatamodelgenerator.ontologycontent.OntologyContent;
import ontologydatamodelgenerator.ontologyparser.OntologyParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class OntologyDataModelGenerator {
  
  private static final File templateFile = new File("./src/main/java/ontologydatamodelgenerator/Template");
  
  public static void main(String[] args) throws Exception {
    File ontologyFile = new File("./src/main/resources/osp.owl");
    
    String[] split = ontologyFile.getName().split("\\.");
    String pkg = split[split.length - 2] + "ontologydatamodel";
    
    OntologyContent content = OntologyParser.parse(ontologyFile);
    
    String classes = getContentForClassesDataModel(content);
    String objectProperties = getContentForObjectPropertiesDataModel(content);
    String dataProperties = getContentForDataPropertiesDataModel(content);
    String individuals = getContentForIndividualsDataModel(content);
    
    writeDataModelClassFile(pkg, classes, "OspOntologyClasses");
    writeDataModelClassFile(pkg, objectProperties, "OspOntologyObjectProperties");
    writeDataModelClassFile(pkg, dataProperties, "OspOntologyDataProperties");
    writeDataModelClassFile(pkg, individuals, "OspOntologyIndividuals");
  }
  
  private static String getContentForClassesDataModel(OntologyContent content) {
    StringBuilder classes = new StringBuilder();
    content.getClasses().forEach((name, owlClass) -> classes.append("public static final String ").append(name.toUpperCase()).append(" = ").append("\"").append(name.toLowerCase()).append("\"").append(";\n\t"));
    
    return classes.toString();
  }
  
  private static String getContentForObjectPropertiesDataModel(OntologyContent content) {
    StringBuilder objectProperties = new StringBuilder();
    content.getObjectProperties().forEach((name, owlObjectProperty) -> objectProperties.append("public static final String ").append(name.toUpperCase()).append(" = ").append("\"").append(name.toLowerCase()).append("\"").append(";\n\t"));
    
    return objectProperties.toString();
  }
  
  private static String getContentForDataPropertiesDataModel(OntologyContent content) {
    StringBuilder dataProperties = new StringBuilder();
    content.getDataProperties().forEach((name, owlDataProperty) -> dataProperties.append("public static final String ").append(name.toUpperCase()).append(" = ").append("\"").append(name.toLowerCase()).append("\"").append(";\n\t"));
    
    return dataProperties.toString();
  }
  
  private static String getContentForIndividualsDataModel(OntologyContent content) {
    StringBuilder individuals = new StringBuilder();
    content.getIndividuals().forEach((name, owlIndividual) -> individuals.append("public static final String ").append(name.toUpperCase()).append(" = ").append("\"").append(name.toLowerCase()).append("\"").append(";\n\t"));
    
    return individuals.toString();
  }
  
  private static void writeDataModelClassFile(String pkg, String fields, String classFileName) throws IOException {
    String templateContent = new String(Files.readAllBytes(templateFile.toPath()));
    String dataModelClassContent = templateContent.replace("$FIELDS$", fields).replace("$CLASS_NAME$", classFileName).replace("$PACKAGE$", pkg);
    File destination = new File("./src/main/java/" + pkg.replace(".", "/") + "/");
    File dataModelClassFile = new File(destination, classFileName + ".java");
    dataModelClassFile.getParentFile().mkdirs();
    FileWriter writer = new FileWriter(dataModelClassFile);
    writer.write(dataModelClassContent);
    writer.flush();
    writer.close();
  }
}
