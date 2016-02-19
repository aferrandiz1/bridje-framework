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

package org.bridje.tls.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ThreadLocalStorage
{
    private ThreadLocal<Map<Class,List<Object>>> threadLocalStorage;

    public ThreadLocalStorage()
    {
        threadLocalStorage = new ThreadLocal<>();
    }

    public <T> T get(Class<T> cls)
    {
        Map<Class, List<Object>> map = threadLocalStorage.get();
        if(map != null)
        {
            List<Object> objects = map.get(cls);
            if(objects != null && !objects.isEmpty())
            {
                return (T)objects.get(objects.size()-1);
            }
        }
        return null;
    }
    
    public void pop(Class cls)
    {
        Map<Class, List<Object>> map = threadLocalStorage.get();
        if(map != null)
        {
            List<Object> objects = map.get(cls);
            if(objects != null && !objects.isEmpty())
            {
                objects.remove(objects.size()-1);
            }
        }
    }

    public void put(Object obj)
    {
        Class cls = obj.getClass();
        Map<Class, List<Object>> map = threadLocalStorage.get();
        if(map == null)
        {
            map = new HashMap<>();
            threadLocalStorage.set(map);
        }
        List<Object> objects = map.get(cls);
        if(objects == null)
        {
            objects = new ArrayList<>();
            map.put(cls, objects);
        }
        objects.add(obj);
    }
}
