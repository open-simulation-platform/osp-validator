package fmu;

public class PlugSocketConnection extends NamedEntity {
    private String sourceSimulator;
    private String plug;
    private String targetSimulator;
    private String socket;


    public void setSourceSimulator(String sourceSimulator) {
        this.sourceSimulator = sourceSimulator;
    }

    public void setPlug(String plug) {
        this.plug = plug;
    }

    public void setTargetSimulator(String targetSimulator) {
        this.targetSimulator = targetSimulator;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("PlugSocketConnection:\n");
        sb.append("- SourceSimulator: " + sourceSimulator + "\n");
        sb.append("- Plug: " + plug + "\n");
        sb.append("- TargetSimulator: " + targetSimulator + "\n");
        sb.append("- Socket: " + socket + "\n");
        return sb.toString();
    }
}
