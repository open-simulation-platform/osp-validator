package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.modeldescription.TestResources;
import com.opensimulationplatform.modeldescription.xml.model.ObjectFactory;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;
import org.junit.Test;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import static org.junit.Assert.fail;

public class OspModelDescriptionTypeFactoryTest {
  @Test
  public void canCreate() throws Exception {
    ModelDescriptionFactory factory = new ModelDescriptionFactory();
    ModelDescription modelDescription = factory.create(TestResources.CRANE_CONTROLLER_XML, TestResources.CRANE_CONTROLLER_FMU.toURI());

    OspModelDescriptionTypeFactory typeFactory = new OspModelDescriptionTypeFactory();
    OspModelDescriptionType ospModelDescriptionType = typeFactory.create(modelDescription);

    ObjectFactory objectFactory = new ObjectFactory();
    JAXBContext jc = JAXBContext.newInstance(ospModelDescriptionType.getClass());
    JAXBSource source = new JAXBSource(jc, objectFactory.createOspModelDescription(ospModelDescriptionType));

    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = schemaFactory.newSchema(OspModelDescriptionParser.class.getResource("/OspModelDescription-1.0.1.xsd"));

    Validator validator = schema.newValidator();
    validator.setErrorHandler(new ErrorHandler() {
      @Override
      public void warning(SAXParseException exception) {
        fail(exception.toString());
      }

      @Override
      public void error(SAXParseException exception) {
        fail(exception.toString());
      }

      @Override
      public void fatalError(SAXParseException exception) {
        fail(exception.toString());
      }
    });
    validator.validate(source);
  }
}