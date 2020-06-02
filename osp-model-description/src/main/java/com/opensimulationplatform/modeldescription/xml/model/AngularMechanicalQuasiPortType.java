
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for AngularMechanicalQuasiPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AngularMechanicalQuasiPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Torque" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}TorqueType"/>
 *         &lt;element name="AngularDisplacement" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularDisplacementType"/>
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
@XmlType(name = "AngularMechanicalQuasiPortType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "torque",
    "angularDisplacement"
})
public class AngularMechanicalQuasiPortType {

    @XmlElement(name = "Torque", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected TorqueType torque;
    @XmlElement(name = "AngularDisplacement", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected AngularDisplacementType angularDisplacement;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the torque property.
     * 
     * @return
     *     possible object is
     *     {@link TorqueType }
     *     
     */
    public TorqueType getTorque() {
        return torque;
    }

    /**
     * Sets the value of the torque property.
     * 
     * @param value
     *     allowed object is
     *     {@link TorqueType }
     *     
     */
    public void setTorque(TorqueType value) {
        this.torque = value;
    }

    /**
     * Gets the value of the angularDisplacement property.
     * 
     * @return
     *     possible object is
     *     {@link AngularDisplacementType }
     *     
     */
    public AngularDisplacementType getAngularDisplacement() {
        return angularDisplacement;
    }

    /**
     * Sets the value of the angularDisplacement property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngularDisplacementType }
     *     
     */
    public void setAngularDisplacement(AngularDisplacementType value) {
        this.angularDisplacement = value;
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
