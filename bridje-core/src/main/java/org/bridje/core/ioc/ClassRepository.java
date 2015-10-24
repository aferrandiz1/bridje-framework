/*
 * Copyright 2015 Bridje Framework.
 *
 * Alejandro Ferrandiz (acksecurity[at]hotmail.com)
 * Gilberto Vento (gilberto.vento[at]gmail.com)
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

package org.bridje.core.ioc;

import java.lang.annotation.Annotation;

/**
 *
 */
public interface ClassRepository
{
    /**
     * Navigates through all methods of all component classes registred 
     * in this repository, that are annotated with the given annotation.
     * This includes superclasses methods.
     * 
     * @param <A>
     * @param annotation
     * @param navigator 
     */
    public <A extends Annotation> void navigateAnnotMethods(Class<A> annotation, AnnotMethodNavigator<A> navigator);
    
    /**
     * Navigates through all fields of all component classes registred 
     * in this repository, that are annotated with the given annotation.
     * This includes superclasses fields.
     * 
     * @param <A>
     * @param annotation
     * @param navigator 
     */
    public <A extends Annotation> void navigateAnnotFileds(Class<A> annotation, AnnotFieldNavigator<A> navigator);
    
    /**
     * Navigates through all component classes registred 
     * in this repository, that are annotated with the given annotation.
     * This includes superclasses fields.
     * 
     * @param <A>
     * @param annotation
     * @param navigator 
     */
    public <A extends Annotation> void navigateAnnotClasses(A annotation, AnnotClassNavigator<A> navigator);
}