package fmu;

import java.util.List;

public class Mapping {
    private List<Simulator> simulators;
    private List<PlugSocketConnection> plugSocketConnections;
    private List <BondConnection> bondConnections;
    private List <VariableConnection> variableConnections;



    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Mapping:\n");

        for (Simulator simulator : simulators) {
            sb.append(simulator.toString());
            sb.append('\n');
        }

        for (PlugSocketConnection plugSocketConnection : plugSocketConnections) {
            sb.append(plugSocketConnection.toString());
            sb.append('\n');
        }

        for (BondConnection bondConnection : bondConnections) {
            sb.append(bondConnection.toString());
            sb.append('\n');
        }

        for (VariableConnection variableConnection : variableConnections) {
            sb.append(variableConnection.toString());
            sb.append('\n');
        }

        return sb.toString();
    }

    public void setSimulators(List<Simulator> simulators) {
        this.simulators = simulators;
    }

    public void setPlugSocketConnections(List<PlugSocketConnection> plugSocketConnections) {
        this.plugSocketConnections = plugSocketConnections;
    }

    public void setBondConnections(List<BondConnection> bondConnections) {
        this.bondConnections = bondConnections;
    }

    public void setVariableConnections(List<VariableConnection> variableConnections) {
        this.variableConnections = variableConnections;
    }
}
