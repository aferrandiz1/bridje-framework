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
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents an entity, entity classes will be generated out of the
 * information present in this object.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityInf extends EntityInfBase
{
    @XmlAttribute
    private String table;

    /**
     * The name of the table for this entity.
     *
     * @return An string representing the table name for this entity.
     */
    public String getTable()
    {
        if (this.table == null)
        {
            this.table = Utils.toSQLName(this.getName());
        }
        return table;
    }

    /**
     * The name of the table for this entity.
     *
     * @param table An string representing the table name for this entity.
     */
    public void setTable(String table)
    {
        this.table = table;
    }
}
