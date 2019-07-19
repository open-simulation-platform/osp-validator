package com.opensimulationplatform.cli;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceUtilTest {
  @Test
  public void canWriteResourceToTempFile() throws IOException {
    File original = new File("./src/test/resources/resourceutil/lorem-ipsum.txt");
    
    File temp = ResourceUtil.writeResourceToTempFile("/resourceutil/lorem-ipsum.txt");
    
    assertTrue(temp.exists());
    assertEquals(new String(Files.readAllBytes(original.toPath())), new String(Files.readAllBytes(temp.toPath())));
  }
  
  @Test
  public void canWriteResourceToTempFileIfResourceNameDoesNotStartWithForwardSlash() throws IOException {
    File original = new File("./src/test/resources/resourceutil/lorem-ipsum.txt");
    
    File temp = ResourceUtil.writeResourceToTempFile("resourceutil/lorem-ipsum.txt");
    
    assertTrue(temp.exists());
    assertEquals(new String(Files.readAllBytes(original.toPath())), new String(Files.readAllBytes(temp.toPath())));
  }
}