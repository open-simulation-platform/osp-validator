
package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShaftSpeedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShaftSpeedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://opensimulationplatform.com/MSMI/OSPModelDescription}AngularVelocityType">
 *       &lt;sequence>
 *         &lt;element name="Variable" type="{http://opensimulationplatform.com/MSMI/OSPModelDescription}VariableType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShaftSpeedType", namespace = "http://opensimulationplatform.com/MSMI/OSPModelDescription")
public class ShaftSpeedType
    extends AngularVelocityType
{


}
