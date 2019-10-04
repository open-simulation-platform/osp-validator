package com.opensimulationplatform.cseconfig;

import com.opensimulationplatform.core.util.resource.Resource;

public class TestResources {
  public static final Resource CSE_CONFIG_JSON = new Resource("/parsing/json/cse-config.json");
  public static final Resource CSE_CONFIG_INVALID_JSON = new Resource("/validator/json/cse-config-invalid.json");
  public static final Resource CSE_CONFIG_INVALID_B_JSON = new Resource("/validator/json/cse-config-invalid-B.json");
  public static final Resource CSE_CONFIG_VALID_JSON = new Resource("/validator/json/cse-config-valid.json");
  public static final Resource SYSTEM_STRUCTURE_VALID_XML = new Resource("/validator/xml/OspSystemStructure-valid.xml");
  public static final Resource SYSTEM_STRUCTURE_INVALID_XML = new Resource("/validator/xml/OspSystemStructure-invalid.xml");
  public static final Resource SYSTEM_STRUCTURE_XML = new Resource("/parsing/xml/OspSystemStructure.xml");
}
