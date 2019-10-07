package com.opensimulationplatform.modeldescription.cli;

import com.opensimulationplatform.core.util.resource.Resource;

public class TestResources {
  static final Resource CRANE_CONTROLLER_FMU = new Resource("/CraneController.fmu");
  static final Resource CRANE_CONTROLLER_VALID = new Resource("/CraneController_OspModelDescription-valid.xml");
  static final Resource CRANE_CONTROLLER_INVALID = new Resource("/CraneController_OspModelDescription-invalid.xml");
  static final Resource OSP_OWL = new Resource("/osp.owl");
}
