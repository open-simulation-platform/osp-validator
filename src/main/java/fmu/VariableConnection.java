package fmu;

public class VariableConnection {
    private String sourceSimulator;
    private String sourceVariable;
    private String targetSimulator;
    private String targetVariable;

    public void setSourceSimulator(String sourceSimulator) {
        this.sourceSimulator = sourceSimulator;
    }

    public void setSourceVariable(String sourceVariable) {
        this.sourceVariable = sourceVariable;
    }

    public void setTargetSimulator(String targetSimulator) {
        this.targetSimulator = targetSimulator;
    }

    public void setTargetVariable(String targetVariable) {
        this.targetVariable = targetVariable;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("VariableConnection:\n");
        sb.append("- SourceSimulator: " + sourceSimulator+ "\n");
        sb.append("- SourceVariable: " + sourceVariable + "\n");
        sb.append("- TargetSimulator: " + targetSimulator+ "\n");
        sb.append("- TargetVariable: " + targetVariable + "\n");
        return sb.toString();
    }

}
