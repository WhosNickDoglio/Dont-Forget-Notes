/*
 * MIT License
 *
 * Copyright (c) 2023-2024. Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.whosnickdoglio.baenotes.convention.configurations

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Project

internal class LintPluginConfiguration : Configuration {
    override fun configure(project: Project) =
        with(project) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            pluginManager.apply("com.squareup.sort-dependencies")
            pluginManager.apply("com.squareup.sort-dependencies")
            pluginManager.apply("com.autonomousapps.dependency-analysis")
            dependOnBuildLogicTask("detekt")
            tasks.withType(Detekt::class.java).configureEach { detekt -> detekt.jvmTarget = "22" }
            afterEvaluate { // Not sure why I need this?
                dependOnBuildLogicTask("detektMain")
                dependOnBuildLogicTask("detektTest")
            }
            dependOnBuildLogicTask("sortDependencies")
            dependOnBuildLogicTask("checkSortDependencies")
        }
}
