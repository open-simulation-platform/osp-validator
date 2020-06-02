
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for OspModelDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OspModelDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UnitDefinitions" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}UnitDefinitionsType" minOccurs="0"/>
 *         &lt;element name="VariableGroups" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VariableGroupsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OspModelDescriptionType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "unitDefinitions",
    "variableGroups"
})
public class OspModelDescriptionType {

    @XmlElement(name = "UnitDefinitions", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected UnitDefinitionsType unitDefinitions;
    @XmlElement(name = "VariableGroups", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected VariableGroupsType variableGroups;
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the unitDefinitions property.
     * 
     * @return
     *     possible object is
     *     {@link UnitDefinitionsType }
     *     
     */
    public UnitDefinitionsType getUnitDefinitions() {
        return unitDefinitions;
    }

    /**
     * Sets the value of the unitDefinitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitDefinitionsType }
     *     
     */
    public void setUnitDefinitions(UnitDefinitionsType value) {
        this.unitDefinitions = value;
    }

    /**
     * Gets the value of the variableGroups property.
     * 
     * @return
     *     possible object is
     *     {@link VariableGroupsType }
     *     
     */
    public VariableGroupsType getVariableGroups() {
        return variableGroups;
    }

    /**
     * Sets the value of the variableGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableGroupsType }
     *     
     */
    public void setVariableGroups(VariableGroupsType value) {
        this.variableGroups = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "0.1";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
