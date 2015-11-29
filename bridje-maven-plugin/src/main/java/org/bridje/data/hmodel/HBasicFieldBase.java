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
 * Root class for all the basic fields objects.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class HBasicFieldBase extends HField
{
    @XmlAttribute
    private HFieldAccess access;
    
    /**
     * 
     * @return A HFieldAccess object representing the value of the access field.
     */
    public HFieldAccess getAccess()
    {
        return this.access;
    }

    /**
     * 
     * @param access The new value for the access field.
     */
    public void setAccess(HFieldAccess access)
    {
        this.access = access;
    }

}