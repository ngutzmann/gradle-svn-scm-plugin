/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nebula.plugin.scm.svn

import nebula.plugin.scm.ScmPlugin
import nebula.plugin.scm.svn.providers.SVNProvider
import org.gradle.api.Plugin
import org.gradle.api.Project

class SVNScmPlugin implements Plugin<Project> {
    private static final String EXTENSION_NAME = 'svnScm'
    @Override
    void apply(Project project) {
        project.plugins.apply(ScmPlugin)
        def extension = project.rootProject.extensions.findByName(EXTENSION_NAME)
        if (!extension) {
            extension = project.rootProject.extensions.create(EXTENSION_NAME, SVNScmExtension, project.rootProject.projectDir.path)
            project.rootProject.scmFactory.createMethod = { new SVNProvider(extension.getRootDirectory()) }
        }
    }
}
