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
  
  private final String validConfigurationPath = "../core/src/test/resources/validator/cse-config-valid.json";
  private final String ontologyPath = "../core/src/test/resources/validator/osp.owl";
  
  @Test
  public void canCallGetWithQueriesOnUrlFormat() throws IOException {
    String configuration = new File(validConfigurationPath).toURI().toURL().toString();
    String ontology = new File(ontologyPath).toURI().toURL().toString();
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
  public void canCallGetWithQueriesOnNonUrlFormat() throws IOException {
    HttpServletRequest request = new MockHttpServletRequest(validConfigurationPath, ontologyPath);
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    ValidationServlet validationServlet = new ValidationServlet();
    
    validationServlet.doGet(request, httpResponse);
  }
  
  @Test
  public void canCallGetOnValidConfiguration() throws IOException {
    HttpServletRequest request = new MockHttpServletRequest(validConfigurationPath, ontologyPath);
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
    String configuration = "../core/src/test/resources/validator/cse-config-invalid.json";
    HttpServletRequest request = new MockHttpServletRequest(configuration, ontologyPath);
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    
    validationServlet.doGet(request, httpResponse);
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("false", response.getValid());
    assertFalse(response.getExplanations().isEmpty());
    assertEquals("generic DisjointWith velocity", response.getExplanations().get(0));
  }
  
}