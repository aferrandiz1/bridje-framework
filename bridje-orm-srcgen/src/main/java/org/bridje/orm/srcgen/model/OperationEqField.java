/*
 * Copyright 2016 Bridje Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bridje.orm.srcgen.model;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This object represents a set field value for an operation.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationEqField
{
    @XmlAttribute(name = "field")
    private String fieldName;

    @XmlAttribute(name = "value")
    private String value;

    @XmlTransient
    private FieldInfBase field;

    @XmlTransient
    private OperationInfBase operation;

    /**
     * The field to set.
     * 
     * @return The field to set.
     */
    public FieldInfBase getField()
    {
        if(field == null)
        {
            field = operation.getEntity().findField(fieldName);
        }
        return field;
    }

    /**
     * Gets the name of the field for this condition.
     * 
     * @return The name of the field for this condition.
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * Sets the name of the field for this condition.
     * 
     * @param fieldName The name of the field for this condition.
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * The operation of this condition.
     * 
     * @return The operation of this condition.
     */
    public OperationInfBase getOperation()
    {
        return operation;
    }

    /**
     * The value to set
     * 
     * @return The value to set
     */
    public String getValue()
    {
        return value;
    }

    /**
     * The value to set
     * 
     * @param value The value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Called by JAXB after unmarshal.
     * 
     * @param u The unmarshaller.
     * @param parent The parent object.
     */
    public void afterUnmarshal(Unmarshaller u, Object parent)
    {
        operation = (OperationInfBase)parent;
    }

    /**
     * The operator for this condition.
     * 
     * @return The operator for this condition.
     */
    public String getOperator()
    {
        return "eq";
    }

    /**
     * Clones this condition.
     * 
     * @param operation The parent operation.
     * @return The cloned object.
     */
    public OperationEqField clone(OperationInfBase operation)
    {
        OperationEqField res = new OperationEqField();
        res.operation = this.operation;
        res.fieldName = this.fieldName;
        res.value = this.value;

        return res;
    }
}
