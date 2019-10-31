
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for VariableGroupsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VariableGroupsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Generic" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}GenericType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Force" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ForceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Torque" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}TorqueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Voltage" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VoltageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Pressure" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}PressureType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LinearVelocity" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}LinearVelocityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AngularVelocity" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularVelocityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Current" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}CurrentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VolumeFlowRate" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VolumeFlowRateType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LinearDisplacement" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}LinearDisplacementType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AngularDisplacement" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularDisplacementType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Charge" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ChargeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Volume" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VolumeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LinearMechanicalPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}LinearMechanicalPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AngularMechanicalPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularMechanicalPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ElectromagneticPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ElectromagneticPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HydraulicPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}HydraulicPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LinearMechanicalQuasiPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}LinearMechanicalQuasiPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AngularMechanicalQuasiPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularMechanicalQuasiPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ElectromagneticQuasiPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}ElectromagneticQuasiPortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HydraulicQuasiPort" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}HydraulicQuasiPortType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariableGroupsType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription", propOrder = {
    "generic",
    "force",
    "torque",
    "voltage",
    "pressure",
    "linearVelocity",
    "angularVelocity",
    "current",
    "volumeFlowRate",
    "linearDisplacement",
    "angularDisplacement",
    "charge",
    "volume",
    "linearMechanicalPort",
    "angularMechanicalPort",
    "electromagneticPort",
    "hydraulicPort",
    "linearMechanicalQuasiPort",
    "angularMechanicalQuasiPort",
    "electromagneticQuasiPort",
    "hydraulicQuasiPort"
})
public class VariableGroupsType {

    @XmlElement(name = "Generic", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<GenericType> generic;
    @XmlElement(name = "Force", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<ForceType> force;
    @XmlElement(name = "Torque", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<TorqueType> torque;
    @XmlElement(name = "Voltage", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<VoltageType> voltage;
    @XmlElement(name = "Pressure", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<PressureType> pressure;
    @XmlElement(name = "LinearVelocity", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<LinearVelocityType> linearVelocity;
    @XmlElement(name = "AngularVelocity", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<AngularVelocityType> angularVelocity;
    @XmlElement(name = "Current", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<CurrentType> current;
    @XmlElement(name = "VolumeFlowRate", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<VolumeFlowRateType> volumeFlowRate;
    @XmlElement(name = "LinearDisplacement", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<LinearDisplacementType> linearDisplacement;
    @XmlElement(name = "AngularDisplacement", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<AngularDisplacementType> angularDisplacement;
    @XmlElement(name = "Charge", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<ChargeType> charge;
    @XmlElement(name = "Volume", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<VolumeType> volume;
    @XmlElement(name = "LinearMechanicalPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<LinearMechanicalPortType> linearMechanicalPort;
    @XmlElement(name = "AngularMechanicalPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<AngularMechanicalPortType> angularMechanicalPort;
    @XmlElement(name = "ElectromagneticPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<ElectromagneticPortType> electromagneticPort;
    @XmlElement(name = "HydraulicPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<HydraulicPortType> hydraulicPort;
    @XmlElement(name = "LinearMechanicalQuasiPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<LinearMechanicalQuasiPortType> linearMechanicalQuasiPort;
    @XmlElement(name = "AngularMechanicalQuasiPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<AngularMechanicalQuasiPortType> angularMechanicalQuasiPort;
    @XmlElement(name = "ElectromagneticQuasiPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<ElectromagneticQuasiPortType> electromagneticQuasiPort;
    @XmlElement(name = "HydraulicQuasiPort", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
    protected List<HydraulicQuasiPortType> hydraulicQuasiPort;

    /**
     * Gets the value of the generic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneric().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericType }
     * 
     * 
     */
    public List<GenericType> getGeneric() {
        if (generic == null) {
            generic = new ArrayList<GenericType>();
        }
        return this.generic;
    }

    /**
     * Gets the value of the force property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the force property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForce().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ForceType }
     * 
     * 
     */
    public List<ForceType> getForce() {
        if (force == null) {
            force = new ArrayList<ForceType>();
        }
        return this.force;
    }

    /**
     * Gets the value of the torque property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the torque property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTorque().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TorqueType }
     * 
     * 
     */
    public List<TorqueType> getTorque() {
        if (torque == null) {
            torque = new ArrayList<TorqueType>();
        }
        return this.torque;
    }

    /**
     * Gets the value of the voltage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voltage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVoltage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VoltageType }
     * 
     * 
     */
    public List<VoltageType> getVoltage() {
        if (voltage == null) {
            voltage = new ArrayList<VoltageType>();
        }
        return this.voltage;
    }

    /**
     * Gets the value of the pressure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pressure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPressure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PressureType }
     * 
     * 
     */
    public List<PressureType> getPressure() {
        if (pressure == null) {
            pressure = new ArrayList<PressureType>();
        }
        return this.pressure;
    }

    /**
     * Gets the value of the linearVelocity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linearVelocity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinearVelocity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearVelocityType }
     * 
     * 
     */
    public List<LinearVelocityType> getLinearVelocity() {
        if (linearVelocity == null) {
            linearVelocity = new ArrayList<LinearVelocityType>();
        }
        return this.linearVelocity;
    }

    /**
     * Gets the value of the angularVelocity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the angularVelocity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAngularVelocity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AngularVelocityType }
     * 
     * 
     */
    public List<AngularVelocityType> getAngularVelocity() {
        if (angularVelocity == null) {
            angularVelocity = new ArrayList<AngularVelocityType>();
        }
        return this.angularVelocity;
    }

    /**
     * Gets the value of the current property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the current property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurrent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CurrentType }
     * 
     * 
     */
    public List<CurrentType> getCurrent() {
        if (current == null) {
            current = new ArrayList<CurrentType>();
        }
        return this.current;
    }

    /**
     * Gets the value of the volumeFlowRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the volumeFlowRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVolumeFlowRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VolumeFlowRateType }
     * 
     * 
     */
    public List<VolumeFlowRateType> getVolumeFlowRate() {
        if (volumeFlowRate == null) {
            volumeFlowRate = new ArrayList<VolumeFlowRateType>();
        }
        return this.volumeFlowRate;
    }

    /**
     * Gets the value of the linearDisplacement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linearDisplacement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinearDisplacement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearDisplacementType }
     * 
     * 
     */
    public List<LinearDisplacementType> getLinearDisplacement() {
        if (linearDisplacement == null) {
            linearDisplacement = new ArrayList<LinearDisplacementType>();
        }
        return this.linearDisplacement;
    }

    /**
     * Gets the value of the angularDisplacement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the angularDisplacement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAngularDisplacement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AngularDisplacementType }
     * 
     * 
     */
    public List<AngularDisplacementType> getAngularDisplacement() {
        if (angularDisplacement == null) {
            angularDisplacement = new ArrayList<AngularDisplacementType>();
        }
        return this.angularDisplacement;
    }

    /**
     * Gets the value of the charge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the charge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeType }
     * 
     * 
     */
    public List<ChargeType> getCharge() {
        if (charge == null) {
            charge = new ArrayList<ChargeType>();
        }
        return this.charge;
    }

    /**
     * Gets the value of the volume property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the volume property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVolume().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VolumeType }
     * 
     * 
     */
    public List<VolumeType> getVolume() {
        if (volume == null) {
            volume = new ArrayList<VolumeType>();
        }
        return this.volume;
    }

    /**
     * Gets the value of the linearMechanicalPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linearMechanicalPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinearMechanicalPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearMechanicalPortType }
     * 
     * 
     */
    public List<LinearMechanicalPortType> getLinearMechanicalPort() {
        if (linearMechanicalPort == null) {
            linearMechanicalPort = new ArrayList<LinearMechanicalPortType>();
        }
        return this.linearMechanicalPort;
    }

    /**
     * Gets the value of the angularMechanicalPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the angularMechanicalPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAngularMechanicalPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AngularMechanicalPortType }
     * 
     * 
     */
    public List<AngularMechanicalPortType> getAngularMechanicalPort() {
        if (angularMechanicalPort == null) {
            angularMechanicalPort = new ArrayList<AngularMechanicalPortType>();
        }
        return this.angularMechanicalPort;
    }

    /**
     * Gets the value of the electromagneticPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the electromagneticPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElectromagneticPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElectromagneticPortType }
     * 
     * 
     */
    public List<ElectromagneticPortType> getElectromagneticPort() {
        if (electromagneticPort == null) {
            electromagneticPort = new ArrayList<ElectromagneticPortType>();
        }
        return this.electromagneticPort;
    }

    /**
     * Gets the value of the hydraulicPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hydraulicPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHydraulicPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HydraulicPortType }
     * 
     * 
     */
    public List<HydraulicPortType> getHydraulicPort() {
        if (hydraulicPort == null) {
            hydraulicPort = new ArrayList<HydraulicPortType>();
        }
        return this.hydraulicPort;
    }

    /**
     * Gets the value of the linearMechanicalQuasiPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linearMechanicalQuasiPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinearMechanicalQuasiPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearMechanicalQuasiPortType }
     * 
     * 
     */
    public List<LinearMechanicalQuasiPortType> getLinearMechanicalQuasiPort() {
        if (linearMechanicalQuasiPort == null) {
            linearMechanicalQuasiPort = new ArrayList<LinearMechanicalQuasiPortType>();
        }
        return this.linearMechanicalQuasiPort;
    }

    /**
     * Gets the value of the angularMechanicalQuasiPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the angularMechanicalQuasiPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAngularMechanicalQuasiPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AngularMechanicalQuasiPortType }
     * 
     * 
     */
    public List<AngularMechanicalQuasiPortType> getAngularMechanicalQuasiPort() {
        if (angularMechanicalQuasiPort == null) {
            angularMechanicalQuasiPort = new ArrayList<AngularMechanicalQuasiPortType>();
        }
        return this.angularMechanicalQuasiPort;
    }

    /**
     * Gets the value of the electromagneticQuasiPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the electromagneticQuasiPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElectromagneticQuasiPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElectromagneticQuasiPortType }
     * 
     * 
     */
    public List<ElectromagneticQuasiPortType> getElectromagneticQuasiPort() {
        if (electromagneticQuasiPort == null) {
            electromagneticQuasiPort = new ArrayList<ElectromagneticQuasiPortType>();
        }
        return this.electromagneticQuasiPort;
    }

    /**
     * Gets the value of the hydraulicQuasiPort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hydraulicQuasiPort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHydraulicQuasiPort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HydraulicQuasiPortType }
     * 
     * 
     */
    public List<HydraulicQuasiPortType> getHydraulicQuasiPort() {
        if (hydraulicQuasiPort == null) {
            hydraulicQuasiPort = new ArrayList<HydraulicQuasiPortType>();
        }
        return this.hydraulicQuasiPort;
    }

}
