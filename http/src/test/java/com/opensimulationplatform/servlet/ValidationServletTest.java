package com.opensimulationplatform.servlet;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ValidationServletTest {
  @Test
  public void canCallGetOnValidConfiguration() throws IOException {
    ValidationServlet validationServlet = new ValidationServlet();
    File configuration = new File("./src/test/resources/validator/cse-config-valid.json");
    File ontology = new File("./src/test/resources/validator/osp.owl");
    HttpServletRequest request = new DummyHttpServletRequest(configuration, ontology);
    DummyHttpServletResponse response = new DummyHttpServletResponse();
    
    validationServlet.doGet(request, response);
  
    assertEquals("valid", response.response);
  }
  
  @Test
  public void canCallGetOnInvalidConfiguration() throws IOException {
    ValidationServlet validationServlet = new ValidationServlet();
    File configuration = new File("./src/test/resources/validator/cse-config-invalid.json");
    File ontology = new File("./src/test/resources/validator/osp.owl");
    HttpServletRequest request = new DummyHttpServletRequest(configuration, ontology);
    DummyHttpServletResponse response = new DummyHttpServletResponse();
    
    validationServlet.doGet(request, response);
    
    assertEquals("invalid", response.response);
  }
  
}