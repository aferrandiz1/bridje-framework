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

package org.bridje.sql.impl;

import java.sql.JDBCType;
import org.bridje.sql.ArithmeticExpr;
import org.bridje.sql.BooleanColumn;
import org.bridje.sql.BooleanExpr;
import org.bridje.sql.BuildForeignKeyStep;
import org.bridje.sql.BuildSchemaStep;
import org.bridje.sql.BuildTableStep;
import org.bridje.sql.Column;
import org.bridje.sql.DeleteStep;
import org.bridje.sql.Expression;
import org.bridje.sql.Index;
import org.bridje.sql.InsertIntoStep;
import org.bridje.sql.NumberColumn;
import org.bridje.sql.SQLType;
import org.bridje.sql.SelectStep;
import org.bridje.sql.StringColumn;
import org.bridje.sql.StringExpr;
import org.bridje.sql.Table;
import org.bridje.sql.UpdateStep;

public class SQLFactory
{
    private static ArithmeticExpr<Integer, Integer> COUNT_EXPR;

    private static SQLFactory INST;

    public static SQLFactory getInstance()
    {
        if(INST == null)
        {
            INST = new SQLFactory();
        }
        return INST;
    }

    private SQLFactory()
    {
    }

    public <T> SQLType<T, T> buildType(Class<T> javaType, JDBCType jdbcType, int length, int precision)
    {
        return new SQLTypeImpl(javaType, jdbcType, length, precision);
    }
    
    public <T> SQLType<T, T> buildType(Class<T> javaType, JDBCType jdbcType, int length)
    {
        return new SQLTypeImpl(javaType, jdbcType, length, 0);
    }

    public <T> SQLType<T, T> buildType(Class<T> javaType, JDBCType jdbcType)
    {
        return new SQLTypeImpl(javaType, jdbcType, 0, 0);
    }

    public <T, E> SQLType<T, E> buildType(Class<T> javaType, Class<E> javaReadType, JDBCType jdbcType, int length, int precision)
    {
        return new SQLTypeImpl(javaType, javaReadType, jdbcType, length, precision);
    }

    public <T, E> SQLType<T, E> buildType(Class<T> javaType, Class<E> javaReadType, JDBCType jdbcType, int length)
    {
        return new SQLTypeImpl(javaType, javaReadType, jdbcType, length, 0);
    }

    public <T, E> SQLType<T, E> buildType(Class<T> javaType, Class<E> javaReadType, JDBCType jdbcType)
    {
        return new SQLTypeImpl(javaType, javaReadType, jdbcType, 0, 0);
    }

    public BuildSchemaStep buildSchema(String name)
    {
        return new SchemaBuilder(name);
    }
    
    public BuildTableStep buildTable(String name)
    {
        return new TableBuilder(name);
    }
    
    public <T, E> Column<T, E> buildColumn(String name, SQLType<T, E> type, boolean key, boolean allowNull, T defValue)
    {
        return new ColumnImpl<>(name, type, key, allowNull, null);
    }
    
    public <T, E> NumberColumn<T, E> buildAiColumn(String name, SQLType<T, E> type, boolean key, boolean allowNull)
    {
        ColumnImpl result = new ColumnImpl<>(name, type, key, allowNull, null);
        result.setAutoIncrement(true);
        return result;
    }

    public <T, E> NumberColumn<T, E> buildNumberColumn(String name, SQLType<T, E> type, boolean key, boolean allowNull, T defValue)
    {
        return new ColumnImpl<>(name, type, key, allowNull, defValue);
    }

    public <T, E> StringColumn<T, E> buildStringColumn(String name, SQLType<T, E> type, boolean key, boolean allowNull, T defValue)
    {
        return new ColumnImpl<>(name, type, key, allowNull, defValue);
    }

    public <T, E> BooleanColumn<T, E> buildBoolColumn(String name, SQLType<T, E> type, boolean key, boolean allowNull, T defValue)
    {
        return new ColumnImpl<>(name, type, key, allowNull, defValue);
    }

    public Index buildIndex(String name, Table table, Column<?, ?>[] columns)
    {
        return new IndexImpl(name, table, columns, false);
    }

    public Index buildIndex(Table table, Column<?, ?>... columns)
    {
        return new IndexImpl(null, table, columns, false);
    }

    public Index buildUnique(String name, Table table, Column<?, ?>... columns)
    {
        return new IndexImpl(name, table, columns, true);
    }

    public Index buildUnique(Table table, Column<?, ?>... columns)
    {
        return new IndexImpl(null, table, columns, true);
    }

    public BuildForeignKeyStep buildForeignKey(String name, Table table, Column<?, ?>[] columns)
    {
        return new ForeignKeyBuilder(name, table, columns);
    }
    
    public BuildForeignKeyStep buildForeignKey(Table table, Column<?, ?>[] columns)
    {
        return new ForeignKeyBuilder(null, table, columns);
    }

    public Index buildIndex(String name, Column<?, ?>[] columns)
    {
        return new IndexImpl(name, null, columns, false);
    }

    public Index buildIndex(Column<?, ?>... columns)
    {
        return new IndexImpl(null, null, columns, false);
    }

    public Index buildUnique(String name, Column<?, ?>... columns)
    {
        return new IndexImpl(name, null, columns, true);
    }

    public Index buildUnique(Column<?, ?>... columns)
    {
        return new IndexImpl(null, null, columns, true);
    }

    public BuildForeignKeyStep buildForeignKey(String name, Column<?, ?>[] columns)
    {
        return new ForeignKeyBuilder(name, null, columns);
    }
    
    public BuildForeignKeyStep buildForeignKey(Column<?, ?>[] columns)
    {
        return new ForeignKeyBuilder(null, null, columns);
    }
    
    public SelectStep select(Expression<?, ?>... columns)
    {
        return new SelectBuilder(columns);
    }

    public InsertIntoStep insertInto(Table table)
    {
        return new InsertBuilder(table);
    }

    public UpdateStep update(Table table)
    {
        return new UpdateBuilder(table);
    }

    public DeleteStep delete(Table... tables)
    {
        return new DeleteBuilder(tables);
    }

    public ArithmeticExpr<Number, Number> val(Number value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Byte, Byte> val(byte value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Byte, Byte> val(Byte value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Short, Short> val(short value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Short, Short> val(Short value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Integer, Integer> val(int value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Integer, Integer> val(Integer value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Long, Long> val(long value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Long, Long> val(Long value)
    {
        return new Literal<>(value);
    }
    
    public ArithmeticExpr<Float, Float> val(float value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Float, Float> val(Float value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Double, Double> val(double value)
    {
        return new Literal<>(value);
    }

    public ArithmeticExpr<Double, Double> val(Double value)
    {
        return new Literal<>(value);
    }

    public StringExpr<String, String> val(String value)
    {
        return new Literal<>(value);
    }

    public BooleanExpr<Boolean, Boolean> val(Boolean value)
    {
        return new Literal<>(value);
    }

    public BooleanExpr<Boolean, Boolean> val(boolean value)
    {
        return new Literal<>(value);
    }

    public Expression<Character, Character> val(char value)
    {
        return new Literal<>(value);
    }

    public Expression<Character, Character> val(Character value)
    {
        return new Literal<>(value);
    }

    public <T, E> ArithmeticExpr<T, E> number(T value)
    {
        return new Literal<>(value);
    }

    public <T, E> BooleanExpr<T, E> bool(T value)
    {
        return new Literal<>(value);
    }

    public <T, E> StringExpr<T, E> str(T value)
    {
        return new Literal<>(value);
    }

    public <T, E> Expression<T, E> custom(T value)
    {
        return new Literal<>(value);
    }

    public <T, E> Expression<T, E> param(SQLType<T, E> cls)
    {
        return new Param<>(cls);
    }

    public ArithmeticExpr<Integer, Integer> count()
    {
        if(COUNT_EXPR == null)
        {
            COUNT_EXPR = new SimpleExpressionImpl("count(*)", SQLType.INTEGER);
        }
        return COUNT_EXPR;
    }
}
