package com.opensimulationplatform.util.resource;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceTest {
  @Test
  public void canWriteResourceToTempFile() throws IOException {
    File original = new File("./src/test/resources/resourceutil/lorem-ipsum.txt");
    
    File temp = new Resource("/resourceutil/lorem-ipsum.txt").toFile();
    
    assertTrue(temp.exists());
    assertEquals(new String(Files.readAllBytes(original.toPath())), new String(Files.readAllBytes(temp.toPath())));
  }
  
  @Test
  public void canWriteResourceToTempFileIfResourceNameDoesNotStartWithForwardSlash() throws IOException {
    File original = new File("./src/test/resources/resourceutil/lorem-ipsum.txt");
    
    File temp = new Resource("/resourceutil/lorem-ipsum.txt").toFile();
    
    assertTrue(temp.exists());
    assertEquals(new String(Files.readAllBytes(original.toPath())), new String(Files.readAllBytes(temp.toPath())));
  }
}