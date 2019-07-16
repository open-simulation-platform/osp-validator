package com.opensimulationplatform.servlet;

import java.util.Collections;
import java.util.List;

public class ValidationServletResponse {
  private String valid;
  private List<String> explanations = Collections.emptyList();
  
  public String getValid() {
    return valid;
  }
  
  public void setValid(String valid) {
    this.valid = valid;
  }
  
  public List<String> getExplanations() {
    return explanations;
  }
  
  public void setExplanations(List<String> explanations) {
    this.explanations = explanations;
  }
}
