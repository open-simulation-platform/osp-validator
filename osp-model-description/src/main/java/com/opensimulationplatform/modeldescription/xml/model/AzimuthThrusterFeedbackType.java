//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.11.30 at 11:13:07 AM CET 
//


package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This group represents feedbacks used for communications between azimuth thruster controllers and azimuth thrusters. It contains a ShaftSpeed variable group representing the actual shaft speed of the thruster; a AzimuthAngle variable group representing the actual azimuth angle; a BladePitch variable group representing the actual propeller blade pitch; and a Force variable group representing the actual thrust force. All variable groups are of dimension 1.
 * 
 * <p>Java class for AzimuthThrusterFeedbackType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AzimuthThrusterFeedbackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShaftSpeed" type="{https://open-simulation-platform.com/OspModelDescription/1.0.1}ShaftSpeedType" minOccurs="0"/>
 *         &lt;element name="AzimuthAngle" type="{https://open-simulation-platform.com/OspModelDescription/1.0.1}AzimuthAngleType"/>
 *         &lt;element name="BladePitch" type="{https://open-simulation-platform.com/OspModelDescription/1.0.1}BladePitchType" minOccurs="0"/>
 *         &lt;element name="Force" type="{https://open-simulation-platform.com/OspModelDescription/1.0.1}ForceType"/>
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
@XmlType(name = "AzimuthThrusterFeedbackType", propOrder = {
    "shaftSpeed",
    "azimuthAngle",
    "bladePitch",
    "force"
})
public class AzimuthThrusterFeedbackType {

    @XmlElement(name = "ShaftSpeed")
    protected ShaftSpeedType shaftSpeed;
    @XmlElement(name = "AzimuthAngle", required = true)
    protected AzimuthAngleType azimuthAngle;
    @XmlElement(name = "BladePitch")
    protected BladePitchType bladePitch;
    @XmlElement(name = "Force", required = true)
    protected ForceType force;
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
     * Gets the value of the azimuthAngle property.
     * 
     * @return
     *     possible object is
     *     {@link AzimuthAngleType }
     *     
     */
    public AzimuthAngleType getAzimuthAngle() {
        return azimuthAngle;
    }

    /**
     * Sets the value of the azimuthAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link AzimuthAngleType }
     *     
     */
    public void setAzimuthAngle(AzimuthAngleType value) {
        this.azimuthAngle = value;
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
     * Gets the value of the force property.
     * 
     * @return
     *     possible object is
     *     {@link ForceType }
     *     
     */
    public ForceType getForce() {
        return force;
    }

    /**
     * Sets the value of the force property.
     * 
     * @param value
     *     allowed object is
     *     {@link ForceType }
     *     
     */
    public void setForce(ForceType value) {
        this.force = value;
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
