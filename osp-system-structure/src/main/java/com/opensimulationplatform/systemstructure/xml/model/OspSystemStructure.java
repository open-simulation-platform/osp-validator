
package com.opensimulationplatform.systemstructure.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="BaseStepSize" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Algorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Simulators" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}simulators"/>
 *         &lt;element name="Functions" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}functions" minOccurs="0"/>
 *         &lt;element name="Connections" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}connections" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" fixed="0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "startTime",
    "baseStepSize",
    "algorithm",
    "simulators",
    "functions",
    "connections"
})
@XmlRootElement(name = "OspSystemStructure", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
public class OspSystemStructure {

    @XmlElement(name = "StartTime", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", defaultValue = "0.0")
    protected Double startTime;
    @XmlElement(name = "BaseStepSize", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected Double baseStepSize;
    @XmlElement(name = "Algorithm", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", defaultValue = "fixedStep")
    protected String algorithm;
    @XmlElement(name = "Simulators", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
    protected Simulators simulators;
    @XmlElement(name = "Functions", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected Functions functions;
    @XmlElement(name = "Connections", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected Connections connections;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String version;

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartTime(Double value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the baseStepSize property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBaseStepSize() {
        return baseStepSize;
    }

    /**
     * Sets the value of the baseStepSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBaseStepSize(Double value) {
        this.baseStepSize = value;
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    /**
     * Gets the value of the simulators property.
     * 
     * @return
     *     possible object is
     *     {@link Simulators }
     *     
     */
    public Simulators getSimulators() {
        return simulators;
    }

    /**
     * Sets the value of the simulators property.
     * 
     * @param value
     *     allowed object is
     *     {@link Simulators }
     *     
     */
    public void setSimulators(Simulators value) {
        this.simulators = value;
    }

    /**
     * Gets the value of the functions property.
     * 
     * @return
     *     possible object is
     *     {@link Functions }
     *     
     */
    public Functions getFunctions() {
        return functions;
    }

    /**
     * Sets the value of the functions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Functions }
     *     
     */
    public void setFunctions(Functions value) {
        this.functions = value;
    }

    /**
     * Gets the value of the connections property.
     * 
     * @return
     *     possible object is
     *     {@link Connections }
     *     
     */
    public Connections getConnections() {
        return connections;
    }

    /**
     * Sets the value of the connections property.
     * 
     * @param value
     *     allowed object is
     *     {@link Connections }
     *     
     */
    public void setConnections(Connections value) {
        this.connections = value;
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
