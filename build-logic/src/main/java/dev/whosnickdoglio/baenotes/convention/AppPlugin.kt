/*
 * MIT License
 *
 * Copyright (c) 2024. Nicholas Doglio
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

package dev.whosnickdoglio.baenotes.convention

import dev.whosnickdoglio.baenotes.convention.configurations.CommonDependencyConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.ComposeGuardConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.ConfigurablePlugin
import dev.whosnickdoglio.baenotes.convention.configurations.Configuration
import dev.whosnickdoglio.baenotes.convention.configurations.KotlinConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.LintPluginConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.SpotlessConfiguration
import org.gradle.api.Project

internal class AppPlugin : ConfigurablePlugin() {
    override val configurations: List<Configuration> =
        listOf(
            SpotlessConfiguration(),
            KotlinConfiguration(),
            CommonDependencyConfiguration(),
            ComposeGuardConfiguration(),
            LintPluginConfiguration(),
        )

    override fun apply(target: Project) {
        target.pluginManager.run {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("org.gradle.android.cache-fix")
        }
        super.apply(target)
    }
}
