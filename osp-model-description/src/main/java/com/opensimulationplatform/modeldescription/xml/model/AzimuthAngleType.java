
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AzimuthAngleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AzimuthAngleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://opensimulationplatform.com/osp-is/OSPModelDescription}AngularDisplacementType">
 *       &lt;sequence>
 *         &lt;element name="Variable" type="{http://opensimulationplatform.com/osp-is/OSPModelDescription}VariableType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AzimuthAngleType", namespace = "http://opensimulationplatform.com/osp-is/OSPModelDescription")
public class AzimuthAngleType
    extends AngularDisplacementType
{


}
