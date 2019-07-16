package com.opensimulationplatform.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationServletTest {
  @Test
  public void canCallGetOnValidConfiguration() throws IOException {
    File configuration = new File("../core/src/test/resources/validator/cse-config-valid.json");
    File ontology = new File("../core/src/test/resources/validator/osp.owl");
    HttpServletRequest request = new MockHttpServletRequest(configuration, ontology);
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    ValidationServlet validationServlet = new ValidationServlet();
  
    validationServlet.doGet(request, httpResponse);
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("true", response.getValid());
    assertTrue(response.getExplanations().isEmpty());
  }
  
  @Test
  public void canCallGetOnInvalidConfiguration() throws IOException {
    ValidationServlet validationServlet = new ValidationServlet();
    File configuration = new File("../core/src/test/resources/validator/cse-config-invalid.json");
    File ontology = new File("../core/src/test/resources/validator/osp.owl");
    HttpServletRequest request = new MockHttpServletRequest(configuration, ontology);
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    
    validationServlet.doGet(request, httpResponse);
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("false", response.getValid());
    assertFalse(response.getExplanations().isEmpty());
    assertEquals("generic DisjointWith velocity", response.getExplanations().get(0));
  }
  
}