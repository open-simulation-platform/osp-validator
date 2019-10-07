package com.opensimulationplatform.systemstructure.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationServletTest {
  
  private final URI validConfigurationPath = TestResources.SYSTEM_STRUCTURE_VALID_XML.toFile().toURI();
  private final URI ontologyPath = TestResources.OSP_OWL.toFile().toURI();
  
  @Test
  public void canCallDoGetWithQueriesOnUrlFormat() throws Exception {
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
  public void canCallDoGetWithQueriesOnNonUrlFormat() {
    HttpServletRequest request = new MockHttpServletRequest(validConfigurationPath.toString(), ontologyPath.toString());
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    ValidationServlet validationServlet = new ValidationServlet();
    
    validationServlet.doGet(request, httpResponse);
  }
  
  @Test
  public void canCallDoGetOnValidConfiguration() {
    HttpServletRequest request = new MockHttpServletRequest(validConfigurationPath.toString(), ontologyPath.toString());
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    ValidationServlet validationServlet = new ValidationServlet();
  
    validationServlet.doGet(request, httpResponse);
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("true", response.getValid());
    assertTrue(response.getExplanations().isEmpty());
  }
  
  @Test
  public void canCallDoGetOnInvalidConfiguration() {
    ValidationServlet validationServlet = new ValidationServlet();
    String configuration = TestResources.SYSTEM_STRUCTURE_INVALID_XML.toFile().getAbsolutePath();
    HttpServletRequest request = new MockHttpServletRequest(configuration, ontologyPath.toString());
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    
    validationServlet.doGet(request, httpResponse);
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("false", response.getValid());
    assertFalse(response.getExplanations().isEmpty());
    assertEquals("generic DisjointWith velocity", response.getExplanations().get(0));
  }
  
  @Test
  public void canCallDoPostOnValidConfiguration() {
    HttpServletRequest request = new MockHttpServletRequest(validConfigurationPath.toString(), ontologyPath.toString());
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    ValidationServlet validationServlet = new ValidationServlet();
    
    validationServlet.doPost(request, httpResponse);
    
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("true", response.getValid());
    assertTrue(response.getExplanations().isEmpty());
  }
  
  @Test
  public void canCallDoPostOnInvalidConfiguration() {
    ValidationServlet validationServlet = new ValidationServlet();
    String configuration = TestResources.SYSTEM_STRUCTURE_INVALID_XML.toFile().getAbsolutePath();
    HttpServletRequest request = new MockHttpServletRequest(configuration, ontologyPath.toString());
    MockHttpServletResponse httpResponse = new MockHttpServletResponse();
    
    validationServlet.doPost(request, httpResponse);
    
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse response = gson.fromJson(httpResponse.response, ValidationServletResponse.class);
    assertEquals("false", response.getValid());
    assertFalse(response.getExplanations().isEmpty());
    assertEquals("generic DisjointWith velocity", response.getExplanations().get(0));
  }
}