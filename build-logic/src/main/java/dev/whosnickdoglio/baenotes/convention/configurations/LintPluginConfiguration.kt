// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

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
