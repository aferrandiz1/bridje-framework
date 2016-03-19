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

package org.bridje.dm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity extends EntityBase
{
    @XmlAttribute
    @XmlID
    private String name;

    @XmlAttribute
    private String table;

    @XmlAttribute(name = "extends")
    @XmlIDREF
    private AbstractEntity extendsFrom;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTable()
    {
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    public AbstractEntity getExtends()
    {
        return extendsFrom;
    }

    public void setExtends(AbstractEntity extendsFrom)
    {
        this.extendsFrom = extendsFrom;
    }
}