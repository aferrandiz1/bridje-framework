/*
 * Copyright 2015 Bridje Framework.
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

package org.bridje.core.web.view;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataTable extends DataWidget
{
    @XmlAttribute(name = "items")
    private UIExpression items;

    @XmlAttribute(name = "as")
    private String as;

    @XmlElements(
    {
        @XmlElement(name = "column", type = DataTableColumn.class)
    })
    private List<DataTableColumn> columns;

    public UIExpression getItems()
    {
        return items;
    }

    public String getAs()
    {
        return as;
    }

    public List<DataTableColumn> getColumns()
    {
        return columns;
    }
}