package com.opensimulationplatform.core.util.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

import static java.util.Objects.isNull;

public class Resource {
  private static final Logger LOG = LoggerFactory.getLogger(Resource.class);
  
  private final String name;
  private final URL url;
  
  public Resource(String name) {
    this.name = name;
    this.url = getUrl(name);
  }
  
  private URL getUrl(String resource) {
    URL resourceUrl = Resource.class.getResource(resource);
    if (isNull(resourceUrl)) {
      String message = "Resource: " + resource + " does not exist.";
      if (!resource.startsWith("/")) {
        message = message + " Did you forget to start the resource name with '/'?";
      }
      LOG.error(message);
      throw new RuntimeException(message);
    }
    return resourceUrl;
  }
  
  public File toFile() {
    try (InputStream is = url.openStream();
         BufferedInputStream bis = new BufferedInputStream(is)) {
      LOG.trace("Creating temporary file to hold the resource: " + name + "...");
      String[] parts = name.split("\\.");
      String extension = parts[parts.length - 1];
      File file = File.createTempFile("osp-validator", "." + extension);
      file.deleteOnExit();
      LOG.trace("Created: " + file.getAbsolutePath());
      
      LOG.trace("Reading content from resource: " + name + " and writing to file: " + file.getAbsolutePath() + "...");
      try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
        byte[] bytes = new byte[2048];
        int numBytes;
        while ((numBytes = bis.read(bytes)) != -1) {
          bos.write(bytes, 0, numBytes);
        }
      }
      LOG.trace("Done writing content from resource: " + name + " to file: " + file.getAbsolutePath());
      
      return file;
    } catch (Exception e) {
      String message = "Error writing resource: " + name + " to temporary file";
      LOG.error(message, e);
      throw new RuntimeException(message, e);
    }
  }
}
