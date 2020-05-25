
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for BatteryFeedbackType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatteryFeedbackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElectricPower" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ElectricPowerType"/>
 *         &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VariableType"/>
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
@XmlType(name = "BatteryFeedbackType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "electricPower",
    "variable"
})
public class BatteryFeedbackType {

    @XmlElement(name = "ElectricPower", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected ElectricPowerType electricPower;
    @XmlElement(name = "Variable", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected VariableType variable;
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
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link VariableType }
     *     
     */
    public VariableType getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableType }
     *     
     */
    public void setVariable(VariableType value) {
        this.variable = value;
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
