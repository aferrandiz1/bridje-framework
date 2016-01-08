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

package org.bridje.core.impl.cli;

import java.lang.reflect.Method;

/**
 *
 */
class CommandMethodInfo
{
    private final String name;
    
    private final Method method;
    
    private final Object component;

    public CommandMethodInfo(String name, Method method, Object component)
    {
        this.name = name;
        this.method = method;
        this.component = component;
    }

    public String getName()
    {
        return name;
    }

    public Method getMethod()
    {
        return method;
    }

    public Object getComponent()
    {
        return component;
    }
}
