package com.opensimulationplatform.util.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ResourceUtil {
  
  private static final Logger LOG = LoggerFactory.getLogger(ResourceUtil.class);
  
  public static File writeResourceToTempFile(String resource) {
    if (!resource.startsWith("/")) {
      resource = "/" + resource;
    }
    
    try (InputStream is = ResourceUtil.class.getResourceAsStream(resource);
         BufferedInputStream bis = new BufferedInputStream(is)) {
      LOG.trace("Creating temporary file to hold the resource: " + resource + "...");
      File file = File.createTempFile("msmi-validator", ".tmp");
      file.deleteOnExit();
      LOG.trace("Created: " + file.getAbsolutePath());
      
      LOG.trace("Reading content from resource: " + resource + " and writing to file: " + file.getAbsolutePath() + "...");
      try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
        byte[] bytes = new byte[2048];
        int numBytes;
        while ((numBytes = bis.read(bytes)) != -1) {
          bos.write(bytes, 0, numBytes);
        }
      }
      LOG.trace("Done writing content from resource: " + resource + " to file: " + file.getAbsolutePath());
      
      return file;
    } catch (Exception e) {
      String message = "Error writing resource: " + resource + " to temporary file";
      LOG.error(message, e);
      throw new RuntimeException(e);
    }
  }
}
