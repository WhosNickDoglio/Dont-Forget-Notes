// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

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
