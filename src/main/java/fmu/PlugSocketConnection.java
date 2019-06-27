package fmu;

import utils.FMU2OWLConverter;

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

    public String getSourceSimulator() {
        return sourceSimulator;
    }

    public String getPlug() {
        return plug;
    }

    public String getTargetSimulator() {
        return targetSimulator;
    }

    public String getSocket() {
        return socket;
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

    public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
    {
        converter.convert(this, abbreviated_iri_name);
    }
}
