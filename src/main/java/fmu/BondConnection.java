package fmu;

import utils.FMU2OWLConverter;

public class BondConnection {
    private String simulatorA;
    private String bondA;
    private String simulatorB;
    private String bondB;

    public void setSimulatorA(String simulatorA) {
        this.simulatorA = simulatorA;
    }

    public void setBondA(String bondA) {
        this.bondA = bondA;
    }

    public void setSimulatorB(String simulatorB) {
        this.simulatorB = simulatorB;
    }

    public void setBondB(String bondB) {
        this.bondB = bondB;
    }

    public String getSimulatorA() {
        return simulatorA;
    }

    public String getBondA() {
        return bondA;
    }

    public String getSimulatorB() {
        return simulatorB;
    }

    public String getBondB() {
        return bondB;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("BondConnection:\n");
        sb.append("- SimulatorA: " + simulatorA + "\n");
        sb.append("- BondA: " + bondA+ "\n");
        sb.append("- SimulatorB: " + simulatorB + "\n");
        sb.append("- BondB: " + bondB + "\n");
        return sb.toString();
    }

    public void accept(final FMU2OWLConverter converter, final String abbreviated_iri_name)
    {
        converter.convert(this, abbreviated_iri_name);
    }
}
