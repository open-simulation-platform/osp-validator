package com.opensimulationplatform.servlet;

import com.opensimulationplatform.msmivalidator.MsmiValidator;
import org.eclipse.jetty.http.HttpStatus;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> queries = getQueries(request);
    
    MsmiValidator.Result result = MsmiValidator.validate(new File(queries.get("ontology")), new File(queries.get("configuration")));
    
    PrintWriter writer = response.getWriter();
    if (result.isSuccess()) {
      writer.write("valid\n");
    } else {
      writer.write("invalid\n");
    }
    
    response.setStatus(HttpStatus.OK_200);
    writer.flush();
    writer.close();
  }
  
  private Map<String, String> getQueries(HttpServletRequest request) {
    Map<String, String> queries = new HashMap<>();
    for (String p : request.getQueryString().split("&")) {
      String[] keyValue = p.split("=");
      queries.put(keyValue[0], keyValue[1]);
    }
    return queries;
  }
}
