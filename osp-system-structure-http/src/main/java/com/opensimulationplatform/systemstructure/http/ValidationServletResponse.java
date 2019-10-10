package com.opensimulationplatform.systemstructure.http;

public class ValidationServletResponse {
  private String valid = "";
  private String message = "";
  
  public String getValid() {
    return valid;
  }
  
  public void setValid(String valid) {
    this.valid = valid;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
}
