
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ElectromagneticQuasiPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ElectromagneticQuasiPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Voltage" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VoltageType"/>
 *         &lt;element name="Charge" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ChargeType"/>
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
@XmlType(name = "ElectromagneticQuasiPortType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "voltage",
    "charge"
})
public class ElectromagneticQuasiPortType {

    @XmlElement(name = "Voltage", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected VoltageType voltage;
    @XmlElement(name = "Charge", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected ChargeType charge;
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
     * Gets the value of the charge property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeType }
     *     
     */
    public ChargeType getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeType }
     *     
     */
    public void setCharge(ChargeType value) {
        this.charge = value;
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
