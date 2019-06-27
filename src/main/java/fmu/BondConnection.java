package fmu;

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
}
