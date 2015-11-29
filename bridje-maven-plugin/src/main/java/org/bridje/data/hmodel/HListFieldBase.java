/**
 * 
 * Copyright 2015 Bridje Framework.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *     
 */

package org.bridje.data.hmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.Unmarshaller;

/**
 * Represents a field that holds a collection of objects.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class HListFieldBase extends HField
{
    @XmlAttribute
    private String of;
    
    @XmlAttribute
    private Boolean wrapper;
    
    @XmlElements(
    {
        @XmlElement(name = "element", type = HListElement.class)
    })
    private java.util.List<HListElement> elements;
    
    /**
     * 
     * @return A String object representing the value of the of field.
     */
    public String getOf()
    {
        return this.of;
    }

    /**
     * 
     * @param of The new value for the of field.
     */
    public void setOf(String of)
    {
        this.of = of;
    }

    /**
     * 
     * @return A Boolean object representing the value of the wrapper field.
     */
    public Boolean getWrapper()
    {
        if(this.wrapper == null)
        {
            this.wrapper = false;
        }
        return this.wrapper;
    }

    /**
     * 
     * @param wrapper The new value for the wrapper field.
     */
    public void setWrapper(Boolean wrapper)
    {
        this.wrapper = wrapper;
    }

    /**
     * 
     * @return A java.util.List&lt;HListElement&gt; object representing the value of the elements field.
     */
    public java.util.List<HListElement> getElements()
    {
        return this.elements;
    }

    /**
     * 
     * @param elements The new value for the elements field.
     */
    public void setElements(java.util.List<HListElement> elements)
    {
        this.elements = elements;
    }

    @XmlTransient
    private HEntityData entity;

    /**
     * The HEntityData object representing the parent of this object.
     */
    public HEntityData getEntity()
    {
        return this.entity;
    }

    /**
     * The HEntityData object representing the parent of this object.
     */
    void setEntity(HEntityData entity)
    {
        this.entity = entity;
    }

    /**
     * This method is call by the JAXB Unmarshaller after the object's creation.
     * @param unmarshaller The unmarshaller object being used.
     * @param parent The parent object for this object.
     */
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent)
    {
        if(parent instanceof HEntityData)
        {
            setEntity((HEntityData)parent);
        }
    }

}