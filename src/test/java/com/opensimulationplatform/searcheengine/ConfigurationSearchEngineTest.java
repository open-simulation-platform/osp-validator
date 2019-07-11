package com.opensimulationplatform.searcheengine;

import com.opensimulationplatform.datamodel.configuration.Configuration;
import com.opensimulationplatform.datamodel.configuration.ConfigurationFactory;
import com.opensimulationplatform.jsonmodel.parsing.ConfigurationJsonFileParser;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.fail;

public class ConfigurationSearchEngineTest {
  @Test
  public void canFindStuff() {
    Configuration configuration = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(new File("./src/test/resources/parsing/cse-config.json")));
    fail();
  }
}