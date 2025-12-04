// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Plugin
import org.gradle.api.Project

internal abstract class ConfigurablePlugin : Plugin<Project> {

    internal abstract val configurations: List<Configuration>

    override fun apply(target: Project) {
        configurations.forEach { it.configure(target) }
    }
}
