package com.opensimulationplatform.servlet;

import com.google.gson.GsonBuilder;
import com.opensimulationplatform.validator.MsmiValidator;
import org.eclipse.jetty.http.HttpStatus;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> queries = getQueries(request);
    
    MsmiValidator.Result result = MsmiValidator.validate(new File(queries.get("ontology")), new File(queries.get("configuration")));
    
    ValidationServletResponse servletResponse = createServletResponse(result);
    
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpStatus.OK_200);
    PrintWriter writer = response.getWriter();
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
  
  private Map<String, String> getQueries(HttpServletRequest request) {
    Map<String, String> queries = new HashMap<>();
    for (String queryString : request.getQueryString().split("&")) {
      String[] keyValue = queryString.split("=");
      queries.put(keyValue[0], keyValue[1]);
    }
    return queries;
  }
}
