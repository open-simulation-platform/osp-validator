
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for connections complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="connections">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connections", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", propOrder = {
    "variableConnection",
    "signalConnection",
    "variableGroupConnection",
    "signalGroupConnection"
})
public class Connections {

    @XmlElement(name = "VariableConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<Connections.VariableConnection> variableConnection;
    @XmlElement(name = "SignalConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<Connections.SignalConnection> signalConnection;
    @XmlElement(name = "VariableGroupConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<Connections.VariableGroupConnection> variableGroupConnection;
    @XmlElement(name = "SignalGroupConnection", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<Connections.SignalGroupConnection> signalGroupConnection;

    /**
     * Gets the value of the variableConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connections.VariableConnection }
     * 
     * 
     */
    public List<Connections.VariableConnection> getVariableConnection() {
        if (variableConnection == null) {
            variableConnection = new ArrayList<Connections.VariableConnection>();
        }
        return this.variableConnection;
    }

    /**
     * Gets the value of the signalConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signalConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignalConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connections.SignalConnection }
     * 
     * 
     */
    public List<Connections.SignalConnection> getSignalConnection() {
        if (signalConnection == null) {
            signalConnection = new ArrayList<Connections.SignalConnection>();
        }
        return this.signalConnection;
    }

    /**
     * Gets the value of the variableGroupConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableGroupConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableGroupConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connections.VariableGroupConnection }
     * 
     * 
     */
    public List<Connections.VariableGroupConnection> getVariableGroupConnection() {
        if (variableGroupConnection == null) {
            variableGroupConnection = new ArrayList<Connections.VariableGroupConnection>();
        }
        return this.variableGroupConnection;
    }

    /**
     * Gets the value of the signalGroupConnection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signalGroupConnection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignalGroupConnection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Connections.SignalGroupConnection }
     * 
     * 
     */
    public List<Connections.SignalGroupConnection> getSignalGroupConnection() {
        if (signalGroupConnection == null) {
            signalGroupConnection = new ArrayList<Connections.SignalGroupConnection>();
        }
        return this.signalGroupConnection;
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
