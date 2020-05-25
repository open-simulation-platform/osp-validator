
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for FixedThrusterSetpointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FixedThrusterSetpointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShaftSpeed" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ShaftSpeedType" minOccurs="0"/>
 *         &lt;element name="BladePitch" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}BladePitchType" minOccurs="0"/>
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
@XmlType(name = "FixedThrusterSetpointType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "shaftSpeed",
    "bladePitch"
})
public class FixedThrusterSetpointType {

    @XmlElement(name = "ShaftSpeed", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected ShaftSpeedType shaftSpeed;
    @XmlElement(name = "BladePitch", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected BladePitchType bladePitch;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the shaftSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link ShaftSpeedType }
     *     
     */
    public ShaftSpeedType getShaftSpeed() {
        return shaftSpeed;
    }

    /**
     * Sets the value of the shaftSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShaftSpeedType }
     *     
     */
    public void setShaftSpeed(ShaftSpeedType value) {
        this.shaftSpeed = value;
    }

    /**
     * Gets the value of the bladePitch property.
     * 
     * @return
     *     possible object is
     *     {@link BladePitchType }
     *     
     */
    public BladePitchType getBladePitch() {
        return bladePitch;
    }

    /**
     * Sets the value of the bladePitch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BladePitchType }
     *     
     */
    public void setBladePitch(BladePitchType value) {
        this.bladePitch = value;
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
