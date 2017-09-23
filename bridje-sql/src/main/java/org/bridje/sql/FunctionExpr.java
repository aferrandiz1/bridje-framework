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
import org.bridje.sql.expr.BooleanExpr;
import org.bridje.sql.expr.Expression;
import org.bridje.sql.expr.StringExpr;

class FunctionExpr<T> extends ExpressionBase<T> implements BooleanExpr<T>, StringExpr<T>, ArithmeticExpr<T>
{
    private final String name;

    private final Expression<?>[] params;

    public FunctionExpr(String name, Class<T> type, Expression<?>... params)
    {
        super(type);
        this.name = name;
        this.params = params;
    }

    @Override
    public void writeSQL(SQLBuilder builder)
    {
        builder.append(name);
        builder.append('(');
        builder.appendAll(params, ", ");
        builder.append(')');
    }
}
