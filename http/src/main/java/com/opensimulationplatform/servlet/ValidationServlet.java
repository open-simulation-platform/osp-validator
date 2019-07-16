package com.opensimulationplatform.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensimulationplatform.msmivalidator.MsmiValidator;
import org.eclipse.jetty.http.HttpStatus;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ValidationServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> queries = getQueries(request);
    
    MsmiValidator.Result result = MsmiValidator.validate(new File(queries.get("ontology")), new File(queries.get("configuration")));
  
    Gson gson = new GsonBuilder().create();
    ValidationServletResponse r = new ValidationServletResponse();
    if (result.isSuccess()) {
      r.setValid("true");
    } else {
      r.setValid("false");
      
      List<String> explanations = new ArrayList<>();
      Set<Set<OWLAxiom>> explanation = result.getExplanation();
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      explanation.forEach(axioms -> {
        axioms.forEach(axiom -> explanations.add(renderer.render(axiom.getAxiomWithoutAnnotations())));
      });
      r.setExplanations(explanations);
    }
  
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpStatus.OK_200);
    PrintWriter writer = response.getWriter();
    writer.println(gson.toJson(r));
    writer.flush();
    writer.close();
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
