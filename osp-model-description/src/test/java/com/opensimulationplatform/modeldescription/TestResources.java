package com.opensimulationplatform.modeldescription;

import com.opensimulationplatform.core.util.resource.Resource;

public class TestResources {
  public static final Resource MODEL_DEFINITION_JSON = new Resource("/parsing/json/OspModelDescription.json");
  public static final Resource MODEL_DEFINITION_XML = new Resource("/parsing/xml/OspModelDescription.xml");
  public static final Resource OSP_OWL = new Resource("/validator/osp.owl");
  public static final Resource CRANE_CONTROLLER_FMU = new Resource("/validator/CraneController.fmu");
  public static final Resource KNUCKLE_BOOM_CRANE_FMU = new Resource("/validator/KnuckleBoomCrane.fmu");
  public static final Resource CRANE_CONTROLLER_JSON = new Resource("/validator/json/CraneController.json");
  public static final Resource KNUCKLE_BOOM_CRANE_JSON = new Resource("/validator/json/KnuckleBoomCrane.json");
  public static final Resource CRANE_CONTROLLER_XML = new Resource("/validator/xml/CraneController_OspModelDescription.xml");
  public static final Resource KNUCKLE_BOOM_CRANE_XML = new Resource("/validator/xml/KnuckleBoomCrane_OspModelDescription.xml");
}
