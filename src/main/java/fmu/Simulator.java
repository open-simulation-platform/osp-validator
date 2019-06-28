package fmu;

import utils.FMU2OWLConverter;

public class Simulator extends NamedEntity {

    private String source;
    private String model;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getModelFileReference() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Simulator:\n");
        sb.append(super.toString());

        sb.append("Source: "+ source + "\n");
        sb.append("Model: "+ model + "\n");

        return sb.toString();
    }

    public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
    {
        converter.convert(this, abbreviated_iri_name);
    }
}
