
package com.opensimulationplatform.systemstructure.xml.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for functions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="functions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LinearTransformation" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}linearTransformationFunction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Sum" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}sumFunction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VectorSum" type="{http://opensimulationplatform.com/MSMI/OSPSystemStructure}vectorSumFunction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "functions", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure", propOrder = {
    "linearTransformation",
    "sum",
    "vectorSum"
})
public class Functions {

    @XmlElement(name = "LinearTransformation", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<LinearTransformationFunction> linearTransformation;
    @XmlElement(name = "Sum", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<SumFunction> sum;
    @XmlElement(name = "VectorSum", namespace = "http://opensimulationplatform.com/MSMI/OSPSystemStructure")
    protected List<VectorSumFunction> vectorSum;

    /**
     * Gets the value of the linearTransformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linearTransformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinearTransformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearTransformationFunction }
     * 
     * 
     */
    public List<LinearTransformationFunction> getLinearTransformation() {
        if (linearTransformation == null) {
            linearTransformation = new ArrayList<LinearTransformationFunction>();
        }
        return this.linearTransformation;
    }

    /**
     * Gets the value of the sum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SumFunction }
     * 
     * 
     */
    public List<SumFunction> getSum() {
        if (sum == null) {
            sum = new ArrayList<SumFunction>();
        }
        return this.sum;
    }

    /**
     * Gets the value of the vectorSum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vectorSum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVectorSum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorSumFunction }
     * 
     * 
     */
    public List<VectorSumFunction> getVectorSum() {
        if (vectorSum == null) {
            vectorSum = new ArrayList<VectorSumFunction>();
        }
        return this.vectorSum;
    }

}
