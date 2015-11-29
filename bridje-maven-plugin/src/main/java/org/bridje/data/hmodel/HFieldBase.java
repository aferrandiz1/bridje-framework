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
 * Root class for all the fields objects.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class HFieldBase
{
    @XmlAttribute
    private String name;
    
    @XmlAttribute
    private String description;
    
    @XmlAttribute
    private Boolean required;
    
    @XmlAttribute
    private Boolean readonly;
    
    @XmlAttribute
    private Boolean isTransient;
    
    /**
     * The field name
     * @return A String object representing the value of the name field.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * The field name
     * @param name The new value for the name field.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * The fiel description
     * @return A String object representing the value of the description field.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * The fiel description
     * @param description The new value for the description field.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * If this field is required.
     * @return A Boolean object representing the value of the required field.
     */
    public Boolean getRequired()
    {
        if(this.required == null)
        {
            this.required = false;
        }
        return this.required;
    }

    /**
     * If this field is required.
     * @param required The new value for the required field.
     */
    public void setRequired(Boolean required)
    {
        this.required = required;
    }

    /**
     * If this field is read only.
     * @return A Boolean object representing the value of the readonly field.
     */
    public Boolean getReadonly()
    {
        if(this.readonly == null)
        {
            this.readonly = false;
        }
        return this.readonly;
    }

    /**
     * If this field is read only.
     * @param readonly The new value for the readonly field.
     */
    public void setReadonly(Boolean readonly)
    {
        this.readonly = readonly;
    }

    /**
     * If this field transient.
     * @return A Boolean object representing the value of the isTransient field.
     */
    public Boolean getIsTransient()
    {
        if(this.isTransient == null)
        {
            this.isTransient = false;
        }
        return this.isTransient;
    }

    /**
     * If this field transient.
     * @param isTransient The new value for the isTransient field.
     */
    public void setIsTransient(Boolean isTransient)
    {
        this.isTransient = isTransient;
    }

}