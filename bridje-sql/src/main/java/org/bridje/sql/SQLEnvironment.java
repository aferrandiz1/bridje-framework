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

import java.sql.SQLException;

public interface SQLEnvironment extends AutoCloseable
{
    SQLDialect getDialect();
    
    void fixTable(Table... table) throws SQLException;

    int executeUpdate(SQLQuery query, Object... parameters) throws SQLException;

    SQLResultSet execute(SQLQuery query, Object... parameters) throws SQLException;

    int executeUpdate(SQLStatement stmt) throws SQLException;

    SQLResultSet execute(SQLStatement stmt) throws SQLException;

    void begin() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;
}