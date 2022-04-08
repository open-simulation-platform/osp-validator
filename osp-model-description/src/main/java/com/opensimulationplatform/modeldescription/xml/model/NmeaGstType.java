//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.08 at 08:48:52 AM CEST 
//


package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This group represents an NMEA GST message, and contains exactly 1 NmeaGstRms, exactly 1 NmeaGstEllipse and exactly 1 NmeaGstPositionError.
 * 
 * <p>Java class for NmeaGstType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NmeaGstType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NmeaGstRms" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}NmeaGstRmsType"/>
 *         &lt;element name="NmeaGstEllipse" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}NmeaGstEllipseType"/>
 *         &lt;element name="NmeaGstPositionError" type="{https://open-simulation-platform.com/OspModelDescription/1.0.0}NmeaGstPositionErrorType"/>
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
@XmlType(name = "NmeaGstType", propOrder = {
    "nmeaGstRms",
    "nmeaGstEllipse",
    "nmeaGstPositionError"
})
public class NmeaGstType {

    @XmlElement(name = "NmeaGstRms", required = true)
    protected NmeaGstRmsType nmeaGstRms;
    @XmlElement(name = "NmeaGstEllipse", required = true)
    protected NmeaGstEllipseType nmeaGstEllipse;
    @XmlElement(name = "NmeaGstPositionError", required = true)
    protected NmeaGstPositionErrorType nmeaGstPositionError;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the nmeaGstRms property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaGstRmsType }
     *     
     */
    public NmeaGstRmsType getNmeaGstRms() {
        return nmeaGstRms;
    }

    /**
     * Sets the value of the nmeaGstRms property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaGstRmsType }
     *     
     */
    public void setNmeaGstRms(NmeaGstRmsType value) {
        this.nmeaGstRms = value;
    }

    /**
     * Gets the value of the nmeaGstEllipse property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaGstEllipseType }
     *     
     */
    public NmeaGstEllipseType getNmeaGstEllipse() {
        return nmeaGstEllipse;
    }

    /**
     * Sets the value of the nmeaGstEllipse property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaGstEllipseType }
     *     
     */
    public void setNmeaGstEllipse(NmeaGstEllipseType value) {
        this.nmeaGstEllipse = value;
    }

    /**
     * Gets the value of the nmeaGstPositionError property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaGstPositionErrorType }
     *     
     */
    public NmeaGstPositionErrorType getNmeaGstPositionError() {
        return nmeaGstPositionError;
    }

    /**
     * Sets the value of the nmeaGstPositionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaGstPositionErrorType }
     *     
     */
    public void setNmeaGstPositionError(NmeaGstPositionErrorType value) {
        this.nmeaGstPositionError = value;
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
