//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.27 at 12:28:02 PM CEST 
//


package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sockets" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}sockets"/>
 *         &lt;element name="Plugs" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}plugs"/>
 *         &lt;element name="Bonds" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}bonds"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sockets",
    "plugs",
    "bonds"
})
@XmlRootElement(name = "OspModelDescription")
public class OspModelDescription {

    @XmlElement(name = "Sockets", required = true)
    protected Sockets sockets;
    @XmlElement(name = "Plugs", required = true)
    protected Plugs plugs;
    @XmlElement(name = "Bonds", required = true)
    protected Bonds bonds;

    /**
     * Gets the value of the sockets property.
     * 
     * @return
     *     possible object is
     *     {@link Sockets }
     *     
     */
    public Sockets getSockets() {
        return sockets;
    }

    /**
     * Sets the value of the sockets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sockets }
     *     
     */
    public void setSockets(Sockets value) {
        this.sockets = value;
    }

    /**
     * Gets the value of the plugs property.
     * 
     * @return
     *     possible object is
     *     {@link Plugs }
     *     
     */
    public Plugs getPlugs() {
        return plugs;
    }

    /**
     * Sets the value of the plugs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugs }
     *     
     */
    public void setPlugs(Plugs value) {
        this.plugs = value;
    }

    /**
     * Gets the value of the bonds property.
     * 
     * @return
     *     possible object is
     *     {@link Bonds }
     *     
     */
    public Bonds getBonds() {
        return bonds;
    }

    /**
     * Sets the value of the bonds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bonds }
     *     
     */
    public void setBonds(Bonds value) {
        this.bonds = value;
    }

}
