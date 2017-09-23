/*
 * Copyright 2017 Bridje Framework.
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

package org.bridje.sql;

import org.bridje.sql.expr.ArithmeticExpr;
import org.bridje.sql.expr.SQLType;
import org.bridje.sql.expr.StringExpr;

public class StringColumn<T> extends Column<T> implements StringExpr<T>
{
    public StringColumn(Table table, String name, SQLType<T> type, boolean allowNull, boolean autoIncrement, T defValue)
    {
        super(table, name, type, allowNull, autoIncrement, defValue);
    }

    @Override
    public StringExpr<T> trim()
    {
        return new FunctionExpr("trim", getType(), this);
    }

    @Override
    public ArithmeticExpr<Integer> length()
    {
        return new FunctionExpr("length", Integer.class, this);
    }
}
