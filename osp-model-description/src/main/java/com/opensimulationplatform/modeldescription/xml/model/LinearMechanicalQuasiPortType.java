
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for LinearMechanicalQuasiPortType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LinearMechanicalQuasiPortType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Force" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ForceType"/>
 *         &lt;element name="LinearDisplacement" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}LinearDisplacementType"/>
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
@XmlType(name = "LinearMechanicalQuasiPortType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "force",
    "linearDisplacement"
})
public class LinearMechanicalQuasiPortType {

    @XmlElement(name = "Force", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected ForceType force;
    @XmlElement(name = "LinearDisplacement", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected LinearDisplacementType linearDisplacement;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
     * Gets the value of the linearDisplacement property.
     * 
     * @return
     *     possible object is
     *     {@link LinearDisplacementType }
     *     
     */
    public LinearDisplacementType getLinearDisplacement() {
        return linearDisplacement;
    }

    /**
     * Sets the value of the linearDisplacement property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearDisplacementType }
     *     
     */
    public void setLinearDisplacement(LinearDisplacementType value) {
        this.linearDisplacement = value;
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
