
package com.opensimulationplatform.systemstructure.xml.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.opensimulationplatform.systemstructure.xml.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.opensimulationplatform.systemstructure.xml.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Connections }
     * 
     */
    public Connections createConnections() {
        return new Connections();
    }

    /**
     * Create an instance of {@link Simulators }
     * 
     */
    public Simulators createSimulators() {
        return new Simulators();
    }

    /**
     * Create an instance of {@link Simulators.Simulator }
     * 
     */
    public Simulators.Simulator createSimulatorsSimulator() {
        return new Simulators.Simulator();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues }
     * 
     */
    public Simulators.Simulator.InitialValues createSimulatorsSimulatorInitialValues() {
        return new Simulators.Simulator.InitialValues();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues.InitialValue }
     * 
     */
    public Simulators.Simulator.InitialValues.InitialValue createSimulatorsSimulatorInitialValuesInitialValue() {
        return new Simulators.Simulator.InitialValues.InitialValue();
    }

    /**
     * Create an instance of {@link OspSystemStructure }
     * 
     */
    public OspSystemStructure createOspSystemStructure() {
        return new OspSystemStructure();
    }

    /**
     * Create an instance of {@link Functions }
     * 
     */
    public Functions createFunctions() {
        return new Functions();
    }

    /**
     * Create an instance of {@link VectorSumFunction }
     * 
     */
    public VectorSumFunction createVectorSumFunction() {
        return new VectorSumFunction();
    }

    /**
     * Create an instance of {@link SumFunction }
     * 
     */
    public SumFunction createSumFunction() {
        return new SumFunction();
    }

    /**
     * Create an instance of {@link VariableEndpoint }
     * 
     */
    public VariableEndpoint createVariableEndpoint() {
        return new VariableEndpoint();
    }

    /**
     * Create an instance of {@link SignalEndpoint }
     * 
     */
    public SignalEndpoint createSignalEndpoint() {
        return new SignalEndpoint();
    }

    /**
     * Create an instance of {@link LinearTransformationFunction }
     * 
     */
    public LinearTransformationFunction createLinearTransformationFunction() {
        return new LinearTransformationFunction();
    }

    /**
     * Create an instance of {@link Connections.VariableConnection }
     * 
     */
    public Connections.VariableConnection createConnectionsVariableConnection() {
        return new Connections.VariableConnection();
    }

    /**
     * Create an instance of {@link Connections.SignalConnection }
     * 
     */
    public Connections.SignalConnection createConnectionsSignalConnection() {
        return new Connections.SignalConnection();
    }

    /**
     * Create an instance of {@link Connections.VariableGroupConnection }
     * 
     */
    public Connections.VariableGroupConnection createConnectionsVariableGroupConnection() {
        return new Connections.VariableGroupConnection();
    }

    /**
     * Create an instance of {@link Connections.SignalGroupConnection }
     * 
     */
    public Connections.SignalGroupConnection createConnectionsSignalGroupConnection() {
        return new Connections.SignalGroupConnection();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues.InitialValue.Real }
     * 
     */
    public Simulators.Simulator.InitialValues.InitialValue.Real createSimulatorsSimulatorInitialValuesInitialValueReal() {
        return new Simulators.Simulator.InitialValues.InitialValue.Real();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues.InitialValue.Integer }
     * 
     */
    public Simulators.Simulator.InitialValues.InitialValue.Integer createSimulatorsSimulatorInitialValuesInitialValueInteger() {
        return new Simulators.Simulator.InitialValues.InitialValue.Integer();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues.InitialValue.Boolean }
     * 
     */
    public Simulators.Simulator.InitialValues.InitialValue.Boolean createSimulatorsSimulatorInitialValuesInitialValueBoolean() {
        return new Simulators.Simulator.InitialValues.InitialValue.Boolean();
    }

    /**
     * Create an instance of {@link Simulators.Simulator.InitialValues.InitialValue.String }
     * 
     */
    public Simulators.Simulator.InitialValues.InitialValue.String createSimulatorsSimulatorInitialValuesInitialValueString() {
        return new Simulators.Simulator.InitialValues.InitialValue.String();
    }

}
