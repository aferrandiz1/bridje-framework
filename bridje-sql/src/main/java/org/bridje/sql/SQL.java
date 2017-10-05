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

import java.sql.JDBCType;
import org.bridje.sql.impl.SQLFactory;

public class SQL
{
    private static final SQLFactory FACT = SQLFactory.getInstance();

    public static final <T> SQLType<T> buildType(Class<T> javaType, JDBCType jdbcType, int length, int precision)
    {
        return FACT.buildType(javaType, jdbcType, length, precision);
    }
    
    public static final <T> SQLType<T> buildType(Class<T> javaType, JDBCType jdbcType, int length)
    {
        return FACT.buildType(javaType, jdbcType, length);
    }

    public static final <T> SQLType<T> buildType(Class<T> javaType, JDBCType jdbcType)
    {
        return FACT.buildType(javaType, jdbcType);
    }

    public static final BuildSchemaStep buildSchema(String name)
    {
        return FACT.buildSchema(name);
    }
    
    public static final BuildTableStep buildTable(String name)
    {
        return FACT.buildTable(name);
    }

    public static final <T> Column<T> buildColumn(String name, SQLType<T> type, boolean key, boolean allowNull, T defValue)
    {
        return FACT.buildColumn(name, type, key, allowNull, defValue);
    }
    
    public static final <T> NumberColumn<T> buildAiColumn(String name, SQLType<T> type, boolean key, boolean allowNull)
    {
        return FACT.buildAiColumn(name, type, key, allowNull);
    }

    public static final <T> NumberColumn<T> buildNumberColumn(String name, SQLType<T> type, boolean key, boolean allowNull, T defValue)
    {
        return FACT.buildNumberColumn(name, type, key, allowNull, defValue);
    }

    public static final <T> StringColumn<T> buildStringColumn(String name, SQLType<T> type, boolean key, boolean allowNull, T defValue)
    {
        return FACT.buildStringColumn(name, type, key, allowNull, defValue);
    }

    public static final <T> BooleanColumn<T> buildBoolColumn(String name, SQLType<T> type, boolean key, boolean allowNull, T defValue)
    {
        return FACT.buildBoolColumn(name, type, key, allowNull, defValue);
    }

    public static final Index buildIndex(String name, Table table, Column<?>... columns)
    {
        return FACT.buildIndex(name, table, columns);
    }

    public static final Index buildIndex(Table table, Column<?>... columns)
    {
        return FACT.buildIndex(table, columns);
    }

    public static final Index buildUnique(String name, Table table, Column<?>... columns)
    {
        return FACT.buildUnique(name, table, columns);
    }

    public static final Index buildUnique(Table table, Column<?>... columns)
    {
        return FACT.buildUnique(table, columns);
    }

    public static final BuildForeignKeyStep buildForeignKey(String name, Table table, Column<?>... columns)
    {
        return FACT.buildForeignKey(name, table, columns);
    }

    public static final BuildForeignKeyStep buildForeignKey(Table table, Column<?>... columns)
    {
        return FACT.buildForeignKey(table, columns);
    }
    
    public static final Index buildIndex(String name, Column<?>... columns)
    {
        return FACT.buildIndex(name, columns);
    }

    public static final Index buildIndex(Column<?>... columns)
    {
        return FACT.buildIndex(columns);
    }

    public static final Index buildUnique(String name, Column<?>... columns)
    {
        return FACT.buildUnique(name, columns);
    }

    public static final Index buildUnique(Column<?>... columns)
    {
        return FACT.buildUnique(columns);
    }

    public static final BuildForeignKeyStep buildForeignKey(String name, Column<?>... columns)
    {
        return FACT.buildForeignKey(name, columns);
    }

    public static final BuildForeignKeyStep buildForeignKey(Column<?>... columns)
    {
        return FACT.buildForeignKey(columns);
    }

    public static final SelectStep select(Expression<?>... columns)
    {
        return FACT.select(columns);
    }

    public static final InsertIntoStep insertInto(Table table)
    {
        return FACT.insertInto(table);
    }

    public static final UpdateStep update(Table table)
    {
        return FACT.update(table);
    }

    public static final DeleteStep delete(Table... tables)
    {
        return FACT.delete(tables);
    }

    public static final ArithmeticExpr<Number> val(Number value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Byte> val(byte value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Byte> val(Byte value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Short> val(short value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Short> val(Short value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Integer> val(int value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Integer> val(Integer value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Long> val(long value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Long> val(Long value)
    {
        return FACT.val(value);
    }
    
    public static final ArithmeticExpr<Float> val(float value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Float> val(Float value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Double> val(double value)
    {
        return FACT.val(value);
    }

    public static final ArithmeticExpr<Double> val(Double value)
    {
        return FACT.val(value);
    }

    public static final StringExpr<String> val(String value)
    {
        return FACT.val(value);
    }

    public static final BooleanExpr<Boolean> val(Boolean value)
    {
        return FACT.val(value);
    }

    public static final BooleanExpr<Boolean> val(boolean value)
    {
        return FACT.val(value);
    }

    public static final Expression<Character> val(char value)
    {
        return FACT.val(value);
    }

    public static final Expression<Character> val(Character value)
    {
        return FACT.val(value);
    }

    public static final <T> ArithmeticExpr<T> number(T value)
    {
        return FACT.number(value);
    }

    public static final <T> BooleanExpr<T> bool(T value)
    {
        return FACT.bool(value);
    }

    public static final <T> StringExpr<T> str(T value)
    {
        return FACT.str(value);
    }

    public static final <T> Expression<T> custom(T value)
    {
        return FACT.custom(value);
    }

    public static final <T> Expression<T> param(SQLType<T> type)
    {
        return FACT.param(type);
    }

    public static final ArithmeticExpr<Integer> count()
    {
        return FACT.count();
    }
}
