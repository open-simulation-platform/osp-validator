
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ElectromagneticPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ElectromagneticPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Voltage" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VoltageType"/>
 *         &lt;element name="Current" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}CurrentType"/>
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
@XmlType(name = "ElectromagneticPortType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "voltage",
    "current"
})
public class ElectromagneticPortType {

    @XmlElement(name = "Voltage", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected VoltageType voltage;
    @XmlElement(name = "Current", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected CurrentType current;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
     * Gets the value of the current property.
     * 
     * @return
     *     possible object is
     *     {@link CurrentType }
     *     
     */
    public CurrentType getCurrent() {
        return current;
    }

    /**
     * Sets the value of the current property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrentType }
     *     
     */
    public void setCurrent(CurrentType value) {
        this.current = value;
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
