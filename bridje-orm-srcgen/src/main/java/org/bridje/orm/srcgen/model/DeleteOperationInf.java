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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * This class represents a detele operation for an Entity, The delete operation
 * will be added to the ORM model class in the source code generation.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteOperationInf extends ParametizedOperationInf
{
    @XmlElements(
    {
        @XmlElement(name = "eq", type = OperationEqField.class)
    })
    private List<OperationEqField> conditions;

    /**
     * The list of fields and the values that will be set to does fields in the
     * create operation.
     * 
     * @return A list of fields to set and their values.
     */
    public List<OperationEqField> getConditions()
    {
        if (conditions == null)
        {
            conditions = new ArrayList<>();
        }
        return conditions;
    }

    @Override
    public OperationType getOperationType()
    {
        return OperationType.DELETE;
    }

    @Override
    public OperationInfBase clone(EntityInfBase entity)
    {
        DeleteOperationInf result = new DeleteOperationInf();
        clone(result, entity);
        result.conditions = cloneConditions(this.conditions);
        return result;
    }

    private List<OperationEqField> cloneConditions(List<OperationEqField> conditions)
    {
        List<OperationEqField> result = new ArrayList<>();
        conditions.forEach(op -> result.add(op.clone(this)));
        return result;
    }
}
