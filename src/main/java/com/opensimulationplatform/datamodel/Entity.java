package com.opensimulationplatform.datamodel;

public abstract class Entity {
  private Entity parent = null;
  private String id;
  
  public Entity(String id) {
    this.id = id;
  }
  
  public String getId() {
    if (parent != null) {
      return parent.getId() + "_" + id;
    } else {
      return id;
    }
  }
  
  public void setParent(Entity parent) {
    if (this.parent == null || this.parent == parent) {
      this.parent = parent;
    } else {
      throw new RuntimeException("Parent can not be changed for an Entity");
    }
  }
}
