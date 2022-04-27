//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.27 at 03:29:35 PM CEST 
//


package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This group represents feedback sent from a bus and contains exactly 1 ElectricPower (representing the power available on the bus presently), exactly 1 Voltage, and exactly 1 Frequency.
 * 
 * <p>Java class for BusFeedbackType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusFeedbackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElectricPower" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}ElectricPowerType"/>
 *         &lt;element name="Voltage" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}VoltageType"/>
 *         &lt;element name="Frequency" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}FrequencyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusFeedbackType", propOrder = {
    "electricPower",
    "voltage",
    "frequency"
})
public class BusFeedbackType {

    @XmlElement(name = "ElectricPower", required = true)
    protected ElectricPowerType electricPower;
    @XmlElement(name = "Voltage", required = true)
    protected VoltageType voltage;
    @XmlElement(name = "Frequency", required = true)
    protected FrequencyType frequency;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the electricPower property.
     * 
     * @return
     *     possible object is
     *     {@link ElectricPowerType }
     *     
     */
    public ElectricPowerType getElectricPower() {
        return electricPower;
    }

    /**
     * Sets the value of the electricPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectricPowerType }
     *     
     */
    public void setElectricPower(ElectricPowerType value) {
        this.electricPower = value;
    }

    /**
     * Gets the value of the voltage property.
     * 
     * @return
     *     possible object is
     *     {@link VoltageType }
     *     
     */
    public VoltageType getVoltage() {
        return voltage;
    }

    /**
     * Sets the value of the voltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoltageType }
     *     
     */
    public void setVoltage(VoltageType value) {
        this.voltage = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link FrequencyType }
     *     
     */
    public FrequencyType getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link FrequencyType }
     *     
     */
    public void setFrequency(FrequencyType value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
