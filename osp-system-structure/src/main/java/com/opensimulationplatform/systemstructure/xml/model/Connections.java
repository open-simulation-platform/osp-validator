
package com.opensimulationplatform.systemstructure.xml.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for connections complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="connections">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="VariableConnection" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint" maxOccurs="2" minOccurs="2"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SignalConnection" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint"/>
 *                   &lt;element name="Signal" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}signalEndpoint"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="VariableGroupConnection" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VariableGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint" maxOccurs="2" minOccurs="2"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SignalGroupConnection" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="SignalGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}signalEndpoint"/>
 *                   &lt;element name="VariableGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connections", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", propOrder = {
    "variableConnectionOrSignalConnectionOrVariableGroupConnection"
})
public class Connections {

    @XmlElements({
        @XmlElement(name = "VariableConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", type = Connections.VariableConnection.class),
        @XmlElement(name = "SignalConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", type = Connections.SignalConnection.class),
        @XmlElement(name = "VariableGroupConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", type = Connections.VariableGroupConnection.class),
        @XmlElement(name = "SignalGroupConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", type = Connections.SignalGroupConnection.class)
    })
    protected List<Object> variableConnectionOrSignalConnectionOrVariableGroupConnection;

    /**
     * Gets the value of the variableConnectionOrSignalConnectionOrVariableGroupConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableConnectionOrSignalConnectionOrVariableGroupConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableConnectionOrSignalConnectionOrVariableGroupConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connections.VariableConnection }
     * {@link Connections.SignalConnection }
     * {@link Connections.VariableGroupConnection }
     * {@link Connections.SignalGroupConnection }
     * 
     * 
     */
    public List<Object> getVariableConnectionOrSignalConnectionOrVariableGroupConnection() {
        if (variableConnectionOrSignalConnectionOrVariableGroupConnection == null) {
            variableConnectionOrSignalConnectionOrVariableGroupConnection = new ArrayList<Object>();
        }
        return this.variableConnectionOrSignalConnectionOrVariableGroupConnection;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint"/>
     *         &lt;element name="Signal" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}signalEndpoint"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class SignalConnection {

        @XmlElement(name = "Variable", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected VariableEndpoint variable;
        @XmlElement(name = "Signal", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected SignalEndpoint signal;

        /**
         * Gets the value of the variable property.
         * 
         * @return
         *     possible object is
         *     {@link VariableEndpoint }
         *     
         */
        public VariableEndpoint getVariable() {
            return variable;
        }

        /**
         * Sets the value of the variable property.
         * 
         * @param value
         *     allowed object is
         *     {@link VariableEndpoint }
         *     
         */
        public void setVariable(VariableEndpoint value) {
            this.variable = value;
        }

        /**
         * Gets the value of the signal property.
         * 
         * @return
         *     possible object is
         *     {@link SignalEndpoint }
         *     
         */
        public SignalEndpoint getSignal() {
            return signal;
        }

        /**
         * Sets the value of the signal property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignalEndpoint }
         *     
         */
        public void setSignal(SignalEndpoint value) {
            this.signal = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="SignalGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}signalEndpoint"/>
     *         &lt;element name="VariableGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class SignalGroupConnection {

        @XmlElement(name = "SignalGroup", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected SignalEndpoint signalGroup;
        @XmlElement(name = "VariableGroup", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected VariableEndpoint variableGroup;

        /**
         * Gets the value of the signalGroup property.
         * 
         * @return
         *     possible object is
         *     {@link SignalEndpoint }
         *     
         */
        public SignalEndpoint getSignalGroup() {
            return signalGroup;
        }

        /**
         * Sets the value of the signalGroup property.
         * 
         * @param value
         *     allowed object is
         *     {@link SignalEndpoint }
         *     
         */
        public void setSignalGroup(SignalEndpoint value) {
            this.signalGroup = value;
        }

        /**
         * Gets the value of the variableGroup property.
         * 
         * @return
         *     possible object is
         *     {@link VariableEndpoint }
         *     
         */
        public VariableEndpoint getVariableGroup() {
            return variableGroup;
        }

        /**
         * Sets the value of the variableGroup property.
         * 
         * @param value
         *     allowed object is
         *     {@link VariableEndpoint }
         *     
         */
        public void setVariableGroup(VariableEndpoint value) {
            this.variableGroup = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint" maxOccurs="2" minOccurs="2"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "variable"
    })
    public static class VariableConnection {

        @XmlElement(name = "Variable", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected List<VariableEndpoint> variable;

        /**
         * Gets the value of the variable property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the variable property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVariable().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VariableEndpoint }
         * 
         * 
         */
        public List<VariableEndpoint> getVariable() {
            if (variable == null) {
                variable = new ArrayList<VariableEndpoint>();
            }
            return this.variable;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="VariableGroup" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}variableEndpoint" maxOccurs="2" minOccurs="2"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "variableGroup"
    })
    public static class VariableGroupConnection {

        @XmlElement(name = "VariableGroup", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
        protected List<VariableEndpoint> variableGroup;

        /**
         * Gets the value of the variableGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the variableGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVariableGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VariableEndpoint }
         * 
         * 
         */
        public List<VariableEndpoint> getVariableGroup() {
            if (variableGroup == null) {
                variableGroup = new ArrayList<VariableEndpoint>();
            }
            return this.variableGroup;
        }

    }

}
