
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for NmeaMwvType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NmeaMwvType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NmeaWindDirection" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaWindDirectionType"/>
 *         &lt;element name="NmeaWindSpeed" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaWindSpeedType"/>
 *         &lt;element name="NmeaStatus" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}NmeaStatusType"/>
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
@XmlType(name = "NmeaMwvType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "nmeaWindDirection",
    "nmeaWindSpeed",
    "nmeaStatus"
})
public class NmeaMwvType {

    @XmlElement(name = "NmeaWindDirection", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected NmeaWindDirectionType nmeaWindDirection;
    @XmlElement(name = "NmeaWindSpeed", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected NmeaWindSpeedType nmeaWindSpeed;
    @XmlElement(name = "NmeaStatus", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", required = true)
    protected NmeaStatusType nmeaStatus;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the nmeaWindDirection property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaWindDirectionType }
     *     
     */
    public NmeaWindDirectionType getNmeaWindDirection() {
        return nmeaWindDirection;
    }

    /**
     * Sets the value of the nmeaWindDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaWindDirectionType }
     *     
     */
    public void setNmeaWindDirection(NmeaWindDirectionType value) {
        this.nmeaWindDirection = value;
    }

    /**
     * Gets the value of the nmeaWindSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaWindSpeedType }
     *     
     */
    public NmeaWindSpeedType getNmeaWindSpeed() {
        return nmeaWindSpeed;
    }

    /**
     * Sets the value of the nmeaWindSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaWindSpeedType }
     *     
     */
    public void setNmeaWindSpeed(NmeaWindSpeedType value) {
        this.nmeaWindSpeed = value;
    }

    /**
     * Gets the value of the nmeaStatus property.
     * 
     * @return
     *     possible object is
     *     {@link NmeaStatusType }
     *     
     */
    public NmeaStatusType getNmeaStatus() {
        return nmeaStatus;
    }

    /**
     * Sets the value of the nmeaStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link NmeaStatusType }
     *     
     */
    public void setNmeaStatus(NmeaStatusType value) {
        this.nmeaStatus = value;
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
