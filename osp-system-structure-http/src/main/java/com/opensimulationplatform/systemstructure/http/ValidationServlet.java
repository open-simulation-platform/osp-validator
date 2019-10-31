package com.opensimulationplatform.systemstructure.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensimulationplatform.core.validation.result.Diagnostic;
import com.opensimulationplatform.core.validation.result.Result;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static java.util.Objects.nonNull;

public class ValidationServlet extends HttpServlet {
  
  private static final Logger LOG = LoggerFactory.getLogger(ValidationServlet.class);
  private final Gson gson = new GsonBuilder().create();
  
  @Override
  protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
    try {
      handleRequest(httpRequest, httpResponse);
    } catch (Exception e) {
      String message = "Error during doGet";
      LOG.error(message, e);
      writeResponse(httpResponse, gson.toJson(e.getMessage()));
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
    try {
      handleRequest(httpRequest, httpResponse);
    } catch (Exception e) {
      String message = "Error during doPost";
      LOG.error(message, e);
      writeResponse(httpResponse, gson.toJson(e.getMessage()));
    }
  }
  
  private void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
    String requiredConfiguration = httpRequest.getParameter("configuration");
    String optionalOntology = httpRequest.getParameter("ontology");
  
    Result result = validate(requiredConfiguration, optionalOntology);
    
    createHttpResponse(httpResponse, result);
  }
  
  private Result validate(String requiredConfiguration, String optionalOntology) {
    File configuration = new File(getURI(requiredConfiguration));
    Result result;
    if (nonNull(optionalOntology)) {
      File ontology = new File(getURI(optionalOntology));
      result = XmlValidator.validate(configuration);
    } else {
      result = XmlValidator.validate(configuration);
    }
    return result;
  }
  
  private void createHttpResponse(HttpServletResponse httpResponse, Result result) {
    httpResponse.setContentType("application/json");
    httpResponse.setCharacterEncoding("UTF-8");
    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    
    if (result.isValid()) {
      httpResponse.setStatus(HttpStatus.OK_200);
    } else {
      httpResponse.setStatus(HttpStatus.BAD_REQUEST_400);
    }
    
    writeResponse(httpResponse, gson.toJson(getServletResponse(result)));
  }
  
  private void writeResponse(HttpServletResponse httpResponse, String response) {
    try (PrintWriter writer = httpResponse.getWriter()) {
      writer.println(response);
      writer.flush();
    } catch (Exception e) {
      LOG.error("Error writing http response", e);
    }
  }
  
  private ValidationServletResponse getServletResponse(Result result) {
    ValidationServletResponse response = new ValidationServletResponse();
    
    if (result.isValid()) {
      response.setValid("true");
    } else {
      response.setValid("false");
      response.setMessage(getResponseMessage(result));
    }
    
    return response;
  }
  
  private String getResponseMessage(Result result) {
    StringBuilder messageBuilder = new StringBuilder();
    List<Diagnostic> diagnostics = result.getDiagnostics();
    diagnostics.forEach(d -> {
      messageBuilder.append(d.getMessage()).append("\n");
    });
    return messageBuilder.toString().trim();
  }
  
  private URI getURI(String value) {
    try {
      LOG.debug("Attempting to parse: '" + value + "' as a URI...");
      URI uri = new URL(value).toURI();
      LOG.debug("done!");
      return uri;
    } catch (Exception e) {
      LOG.debug("'" + value + "' is not on proper URI format. Attempting conversion...");
      try {
        URI uri = new File(value).toURI();
        LOG.debug("done!");
        return uri;
      } catch (Exception ex) {
        String message = "Error parsing: '" + value + "' as a URI";
        LOG.error(message, e);
        throw new RuntimeException(message, e);
      }
    }
  }
}
