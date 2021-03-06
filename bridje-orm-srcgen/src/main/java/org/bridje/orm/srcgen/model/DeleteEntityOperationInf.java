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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * This class represents a detele entity operation for an Entity, The delete
 * entity operation will be added to the ORM model class in the source code
 * generation.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteEntityOperationInf extends OperationInfBase
{
    @Override
    public OperationType getOperationType()
    {
        return OperationType.DELETE_ENTITY;
    }

    @Override
    public OperationInfBase clone(EntityInfBase entity)
    {
        DeleteEntityOperationInf result = new DeleteEntityOperationInf();
        clone(result, entity);
        return result;
    }

    @Override
    public String getSignature()
    {
        return getName() + "()";
    }
}
