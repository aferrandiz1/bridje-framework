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

package org.bridje.tpl.impl.ftl;

import org.bridje.ioc.Component;
import org.bridje.ioc.Priority;
import org.bridje.tpl.TemplateLoader;
import org.bridje.tpl.TplEngine;
import org.bridje.tpl.TplEngineContext;

@Component
@Priority(100)
public class FtlTplEngine implements TplEngine
{
    @Override
    public String getExtension()
    {
        return "ftl";
    }

    @Override
    public TplEngineContext createContext(TemplateLoader loader)
    {
        return new FtlTplContextImpl(loader);
    }
    
}
