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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * This enums holds the SQL types that a string field can have.
 */
@XmlType
@XmlEnum
public enum StringFieldSQLType
{
    /**
     * JDBCType.VARCHAR
     */
    @XmlEnumValue("VARCHAR")
    VARCHAR,
    /**
     * JDBCType.NVARCHAR
     */
    @XmlEnumValue("NVARCHAR")
    NVARCHAR,
    /**
     * JDBCType.LONGVARCHAR
     */
    @XmlEnumValue("LONGVARCHAR")
    LONGVARCHAR,
    /**
     * JDBCType.CHAR
     */
    @XmlEnumValue("CHAR")
    CHAR,
    /**
     * JDBCType.NCHAR
     */
    @XmlEnumValue("NCHAR")
    NCHAR;

}
