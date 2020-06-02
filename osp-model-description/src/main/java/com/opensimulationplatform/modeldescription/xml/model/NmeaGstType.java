
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for NmeaGstType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NmeaGstType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NmeaGstRms" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaGstRmsType"/>
 *         &lt;element name="NmeaGstEllipse" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaGstEllipseType"/>
 *         &lt;element name="NmeaGstPositionError" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaGstPositionErrorType"/>
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
@XmlType(name = "NmeaGstType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "nmeaGstRms",
    "nmeaGstEllipse",
    "nmeaGstPositionError"
})
public class NmeaGstType {

    @XmlElement(name = "NmeaGstRms", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected NmeaGstRmsType nmeaGstRms;
    @XmlElement(name = "NmeaGstEllipse", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected NmeaGstEllipseType nmeaGstEllipse;
    @XmlElement(name = "NmeaGstPositionError", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
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
