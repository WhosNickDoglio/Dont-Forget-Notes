// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention

import dev.whosnickdoglio.baenotes.convention.configurations.CommonDependencyConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.ConfigurablePlugin
import dev.whosnickdoglio.baenotes.convention.configurations.Configuration
import dev.whosnickdoglio.baenotes.convention.configurations.JvmLintConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.KotlinConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.LintPluginConfiguration
import dev.whosnickdoglio.baenotes.convention.configurations.SpotlessConfiguration
import org.gradle.api.Project

internal class KotlinLibPlugin : ConfigurablePlugin() {
    override val configurations: List<Configuration> =
        listOf(
            JvmLintConfiguration(),
            SpotlessConfiguration(),
            KotlinConfiguration(),
            LintPluginConfiguration(),
            CommonDependencyConfiguration(),
        )

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.jvm")
        super.apply(target)
    }
}
