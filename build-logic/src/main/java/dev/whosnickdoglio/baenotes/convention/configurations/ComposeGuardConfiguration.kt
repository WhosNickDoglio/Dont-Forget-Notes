// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Project

internal class ComposeGuardConfiguration : Configuration {
    override fun configure(project: Project) {
        project.pluginManager.withPlugin("org.jetbrains.kotlin.plugin.compose") {
            project.pluginManager.apply("com.joetr.compose.guard")
        }
    }
}
