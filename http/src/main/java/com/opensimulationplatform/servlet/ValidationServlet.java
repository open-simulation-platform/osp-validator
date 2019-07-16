package com.opensimulationplatform.servlet;

import com.google.gson.GsonBuilder;
import com.opensimulationplatform.validator.MsmiValidator;
import org.eclipse.jetty.http.HttpStatus;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationServlet extends HttpServlet {
  
  private static final Logger LOG = LoggerFactory.getLogger(ValidationServlet.class);
  
  @Override
  protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
    Map<String, URI> queries = getQueries(httpRequest);
    File ontology = new File(queries.get("ontology"));
    File configuration = new File(queries.get("configuration"));
    
    MsmiValidator.Result result = MsmiValidator.validate(ontology, configuration);
    
    createResponse(httpResponse, result);
  }
  
  private void createResponse(HttpServletResponse httpResponse, MsmiValidator.Result result) throws IOException {
    httpResponse.setContentType("application/json");
    httpResponse.setCharacterEncoding("UTF-8");
    httpResponse.setStatus(HttpStatus.OK_200);
    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    PrintWriter writer = httpResponse.getWriter();
    
    ValidationServletResponse servletResponse = createServletResponse(result);
    writer.println(new GsonBuilder().create().toJson(servletResponse));
    
    writer.flush();
    writer.close();
  }
  
  private ValidationServletResponse createServletResponse(MsmiValidator.Result result) {
    ValidationServletResponse response = new ValidationServletResponse();
    
    if (result.isSuccess()) {
      response.setValid("true");
    } else {
      response.setValid("false");
      
      List<String> explanations = new ArrayList<>();
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      result.getExplanations().forEach(axioms -> {
        axioms.forEach(axiom -> explanations.add(renderer.render(axiom.getAxiomWithoutAnnotations())));
      });
      
      response.setExplanations(explanations);
    }
    
    return response;
  }
  
  private Map<String, URI> getQueries(HttpServletRequest request) {
    Map<String, URI> queries = new HashMap<>();
    for (String queryString : request.getQueryString().split("&")) {
      String[] keyValue = queryString.split("=");
      String key = keyValue[0];
      String value = keyValue[1];
      try {
        LOG.debug("Attempting to parse query: " + key + " with value: " + value + " as a URI...");
        queries.put(key, new URL(value).toURI());
        LOG.debug("done!");
      } catch (Exception e) {
        LOG.debug("Query: " + key + " is not on proper URI format. Attempting conversion...");
        try {
          queries.put(key, new File(value).toURI());
          LOG.debug("done!");
        } catch (Exception ex) {
          String message = "Error parsing query: " + key + " with value: " + value;
          LOG.error(message, e);
          throw new RuntimeException(message, e);
        }
      }
    }
    return queries;
  }
}
