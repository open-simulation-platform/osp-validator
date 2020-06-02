
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for GeneratorFeedbackType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeneratorFeedbackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElectricPower" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}ElectricPowerType"/>
 *         &lt;element name="Voltage" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}VoltageType"/>
 *         &lt;element name="Frequency" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}FrequencyType"/>
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
@XmlType(name = "GeneratorFeedbackType", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", propOrder = {
    "electricPower",
    "voltage",
    "frequency"
})
public class GeneratorFeedbackType {

    @XmlElement(name = "ElectricPower", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected ElectricPowerType electricPower;
    @XmlElement(name = "Voltage", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected VoltageType voltage;
    @XmlElement(name = "Frequency", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
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
