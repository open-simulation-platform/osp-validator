package datamodel;

public class VariableConnection extends Entity {
  private Simulator sourceSimulator;
  private Variable sourceVariable;
  private Simulator targetSimulator;
  private Variable targetVariable;
  
  public VariableConnection(Simulator sourceSimulator, Variable sourceVariable, Simulator targetSimulator, Variable targetVariable) {
    super(sourceVariable.getId() + "__" + targetVariable.getId());
    this.sourceSimulator = sourceSimulator;
    this.sourceVariable = sourceVariable;
    this.targetSimulator = targetSimulator;
    this.targetVariable = targetVariable;
  }
  
  public Simulator getSourceSimulator() {
    return sourceSimulator;
  }
  
  public Variable getSourceVariable() {
    return sourceVariable;
  }
  
  public Simulator getTargetSimulator() {
    return targetSimulator;
  }
  
  public Variable getTargetVariable() {
    return targetVariable;
  }
}
