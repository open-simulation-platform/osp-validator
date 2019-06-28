package datamodel;

import java.util.List;

public class Bond extends Entity {
  private String name;
  private List<Plug> plugs;
  private List<Socket> sockets;
  
  public Bond(String name, List<Plug> plugs, List<Socket> sockets) {
    super("bond_" + name);
    this.name = name;
    this.plugs = plugs;
    this.sockets = sockets;
  }
  
  public String getName() {
    return name;
  }
  
  public List<Plug> getPlugs() {
    return plugs;
  }
  
  public List<Socket> getSockets() {
    return sockets;
  }
}
