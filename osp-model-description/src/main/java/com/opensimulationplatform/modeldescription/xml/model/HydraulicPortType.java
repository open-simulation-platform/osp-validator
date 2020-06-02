
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for HydraulicPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HydraulicPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pressure" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}PressureType"/>
 *         &lt;element name="VolumeFlowRate" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}VolumeFlowRateType"/>
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
@XmlType(name = "HydraulicPortType", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", propOrder = {
    "pressure",
    "volumeFlowRate"
})
public class HydraulicPortType {

    @XmlElement(name = "Pressure", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected PressureType pressure;
    @XmlElement(name = "VolumeFlowRate", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected VolumeFlowRateType volumeFlowRate;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the pressure property.
     * 
     * @return
     *     possible object is
     *     {@link PressureType }
     *     
     */
    public PressureType getPressure() {
        return pressure;
    }

    /**
     * Sets the value of the pressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link PressureType }
     *     
     */
    public void setPressure(PressureType value) {
        this.pressure = value;
    }

    /**
     * Gets the value of the volumeFlowRate property.
     * 
     * @return
     *     possible object is
     *     {@link VolumeFlowRateType }
     *     
     */
    public VolumeFlowRateType getVolumeFlowRate() {
        return volumeFlowRate;
    }

    /**
     * Sets the value of the volumeFlowRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link VolumeFlowRateType }
     *     
     */
    public void setVolumeFlowRate(VolumeFlowRateType value) {
        this.volumeFlowRate = value;
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
