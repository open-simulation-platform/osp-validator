
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for AngularMechanicalPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AngularMechanicalPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Torque" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}TorqueType"/>
 *         &lt;element name="AngularVelocity" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}AngularVelocityType"/>
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
@XmlType(name = "AngularMechanicalPortType", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", propOrder = {
    "torque",
    "angularVelocity"
})
public class AngularMechanicalPortType {

    @XmlElement(name = "Torque", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected TorqueType torque;
    @XmlElement(name = "AngularVelocity", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription", required = true)
    protected AngularVelocityType angularVelocity;
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
     * Gets the value of the angularVelocity property.
     * 
     * @return
     *     possible object is
     *     {@link AngularVelocityType }
     *     
     */
    public AngularVelocityType getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * Sets the value of the angularVelocity property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngularVelocityType }
     *     
     */
    public void setAngularVelocity(AngularVelocityType value) {
        this.angularVelocity = value;
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
