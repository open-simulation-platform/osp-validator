package fmu;

public class Simulator extends NamedEntity {

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Simulator:\n");
        sb.append(super.toString());

        sb.append("Source:\n");
        sb.append(source);

        return sb.toString();
    }
}
