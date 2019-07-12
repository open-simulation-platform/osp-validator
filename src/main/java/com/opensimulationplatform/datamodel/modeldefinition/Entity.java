package com.opensimulationplatform.datamodel.modeldefinition;

public abstract class Entity {
  
  private Entity parent = null;
  private String name;
  
  protected Entity(String name) {
    this.name = name;
  }
  
  void setParent(Entity parent) {
    if (this.parent == null || this.parent == parent) {
      this.parent = parent;
    } else {
      throw new RuntimeException("Parent can not be changed for an Entity");
    }
  }
  
  public Entity getParent() {
    return parent;
  }
  
  public String getName() {
    return name;
  }
}
