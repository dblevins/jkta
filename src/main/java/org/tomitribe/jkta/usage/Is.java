/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tomitribe.jkta.usage;

import java.io.File;
import java.io.FileFilter;

public interface Is {

    class Scannable implements FileFilter {

        private Clazz clazz = new Clazz();
        private Zip zip = new Zip();

        @Override
        public boolean accept(final File pathname) {
            return zip.accept(pathname) || clazz.accept(pathname);
        }
    }

    class Clazz implements FileFilter {
        @Override
        public boolean accept(final File pathname) {
            return pathname.isFile() && accept(pathname.getName());
        }

        public static boolean accept(final String path) {
            return path.endsWith(".class");
        }
    }

    class Zip implements FileFilter {
        @Override
        public boolean accept(final File pathname) {
            return pathname.isFile() && accept(pathname.getName());
        }

        public static boolean accept(final String path) {
            if (path.endsWith(".zip")) return true;
            if (!path.endsWith("ar")) return false; // optimization
            return path.endsWith(".jar") ||
                    path.endsWith(".ear") ||
                    path.endsWith(".war") ||
                    path.endsWith(".rar");
        }
    }
}