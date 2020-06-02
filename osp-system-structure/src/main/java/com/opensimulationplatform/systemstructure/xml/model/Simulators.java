
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for simulators complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="simulators">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Simulator" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="InitialValues" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="InitialValue" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;choice>
 *                                       &lt;element name="Real">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="Integer">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="Boolean">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="String">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/choice>
 *                                     &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="stepSize" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simulators", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", propOrder = {
    "simulator"
})
public class Simulators {

    @XmlElement(name = "Simulator", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<Simulators.Simulator> simulator;

    /**
     * Gets the value of the simulator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simulator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimulator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Simulators.Simulator }
     * 
     * 
     */
    public List<Simulators.Simulator> getSimulator() {
        if (simulator == null) {
            simulator = new ArrayList<Simulators.Simulator>();
        }
        return this.simulator;
    }


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
     *         &lt;element name="InitialValues" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="InitialValue" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;choice>
     *                             &lt;element name="Real">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="Integer">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="Boolean">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="String">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/choice>
     *                           &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *       &lt;attribute name="stepSize" type="{http://www.w3.org/2001/XMLSchema}double" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "initialValues"
    })
    public static class Simulator {

        @XmlElement(name = "InitialValues", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
        protected Simulators.Simulator.InitialValues initialValues;
        @XmlAttribute(name = "name", required = true)
        protected java.lang.String name;
        @XmlAttribute(name = "source", required = true)
        @XmlSchemaType(name = "anyURI")
        protected java.lang.String source;
        @XmlAttribute(name = "stepSize")
        protected Double stepSize;

        /**
         * Gets the value of the initialValues property.
         * 
         * @return
         *     possible object is
         *     {@link Simulators.Simulator.InitialValues }
         *     
         */
        public Simulators.Simulator.InitialValues getInitialValues() {
            return initialValues;
        }

        /**
         * Sets the value of the initialValues property.
         * 
         * @param value
         *     allowed object is
         *     {@link Simulators.Simulator.InitialValues }
         *     
         */
        public void setInitialValues(Simulators.Simulator.InitialValues value) {
            this.initialValues = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String }
         *     
         */
        public java.lang.String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String }
         *     
         */
        public void setName(java.lang.String value) {
            this.name = value;
        }

        /**
         * Gets the value of the source property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String }
         *     
         */
        public java.lang.String getSource() {
            return source;
        }

        /**
         * Sets the value of the source property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String }
         *     
         */
        public void setSource(java.lang.String value) {
            this.source = value;
        }

        /**
         * Gets the value of the stepSize property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getStepSize() {
            return stepSize;
        }

        /**
         * Sets the value of the stepSize property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setStepSize(Double value) {
            this.stepSize = value;
        }


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
         *         &lt;element name="InitialValue" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;choice>
         *                   &lt;element name="Real">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="Integer">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="Boolean">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="String">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/choice>
         *                 &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "initialValue"
        })
        public static class InitialValues {

            @XmlElement(name = "InitialValue", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", required = true)
            protected List<Simulators.Simulator.InitialValues.InitialValue> initialValue;

            /**
             * Gets the value of the initialValue property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the initialValue property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getInitialValue().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Simulators.Simulator.InitialValues.InitialValue }
             * 
             * 
             */
            public List<Simulators.Simulator.InitialValues.InitialValue> getInitialValue() {
                if (initialValue == null) {
                    initialValue = new ArrayList<Simulators.Simulator.InitialValues.InitialValue>();
                }
                return this.initialValue;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;choice>
             *         &lt;element name="Real">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="Integer">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="Boolean">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="String">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/choice>
             *       &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "real",
                "integer",
                "_boolean",
                "string"
            })
            public static class InitialValue {

                @XmlElement(name = "Real", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
                protected Simulators.Simulator.InitialValues.InitialValue.Real real;
                @XmlElement(name = "Integer", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
                protected Simulators.Simulator.InitialValues.InitialValue.Integer integer;
                @XmlElement(name = "Boolean", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
                protected Simulators.Simulator.InitialValues.InitialValue.Boolean _boolean;
                @XmlElement(name = "String", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
                protected Simulators.Simulator.InitialValues.InitialValue.String string;
                @XmlAttribute(name = "variable", required = true)
                @XmlSchemaType(name = "anySimpleType")
                protected java.lang.String variable;

                /**
                 * Gets the value of the real property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Real }
                 *     
                 */
                public Simulators.Simulator.InitialValues.InitialValue.Real getReal() {
                    return real;
                }

                /**
                 * Sets the value of the real property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Real }
                 *     
                 */
                public void setReal(Simulators.Simulator.InitialValues.InitialValue.Real value) {
                    this.real = value;
                }

                /**
                 * Gets the value of the integer property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Integer }
                 *     
                 */
                public Simulators.Simulator.InitialValues.InitialValue.Integer getInteger() {
                    return integer;
                }

                /**
                 * Sets the value of the integer property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Integer }
                 *     
                 */
                public void setInteger(Simulators.Simulator.InitialValues.InitialValue.Integer value) {
                    this.integer = value;
                }

                /**
                 * Gets the value of the boolean property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Boolean }
                 *     
                 */
                public Simulators.Simulator.InitialValues.InitialValue.Boolean getBoolean() {
                    return _boolean;
                }

                /**
                 * Sets the value of the boolean property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.Boolean }
                 *     
                 */
                public void setBoolean(Simulators.Simulator.InitialValues.InitialValue.Boolean value) {
                    this._boolean = value;
                }

                /**
                 * Gets the value of the string property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.String }
                 *     
                 */
                public Simulators.Simulator.InitialValues.InitialValue.String getString() {
                    return string;
                }

                /**
                 * Sets the value of the string property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Simulators.Simulator.InitialValues.InitialValue.String }
                 *     
                 */
                public void setString(Simulators.Simulator.InitialValues.InitialValue.String value) {
                    this.string = value;
                }

                /**
                 * Gets the value of the variable property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link java.lang.String }
                 *     
                 */
                public java.lang.String getVariable() {
                    return variable;
                }

                /**
                 * Sets the value of the variable property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link java.lang.String }
                 *     
                 */
                public void setVariable(java.lang.String value) {
                    this.variable = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Boolean {

                    @XmlAttribute(name = "value", required = true)
                    protected boolean value;

                    /**
                     * Gets the value of the value property.
                     * 
                     */
                    public boolean isValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     */
                    public void setValue(boolean value) {
                        this.value = value;
                    }

                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Integer {

                    @XmlAttribute(name = "value", required = true)
                    protected int value;

                    /**
                     * Gets the value of the value property.
                     * 
                     */
                    public int getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     */
                    public void setValue(int value) {
                        this.value = value;
                    }

                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Real {

                    @XmlAttribute(name = "value", required = true)
                    protected double value;

                    /**
                     * Gets the value of the value property.
                     * 
                     */
                    public double getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     */
                    public void setValue(double value) {
                        this.value = value;
                    }

                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class String {

                    @XmlAttribute(name = "value", required = true)
                    protected java.lang.String value;

                    /**
                     * Gets the value of the value property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link java.lang.String }
                     *     
                     */
                    public java.lang.String getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link java.lang.String }
                     *     
                     */
                    public void setValue(java.lang.String value) {
                        this.value = value;
                    }

                }

            }

        }

    }

}
