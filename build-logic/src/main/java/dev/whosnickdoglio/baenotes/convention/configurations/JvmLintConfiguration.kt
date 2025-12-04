// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import com.android.build.api.dsl.Lint
import org.gradle.api.Project

internal class JvmLintConfiguration : Configuration {
    override fun configure(project: Project) =
        with(project) {
            pluginManager.apply("com.android.lint")
            dependOnBuildLogicTask("lint")
            extensions.getByType(Lint::class.java).run {
                disable.addAll(
                    setOf(
                        "GradleDependency",
                        "ObsoleteLintCustomCheck",
                        "OldTargetApi",
                        "AndroidGradlePluginVersion",
                    )
                )
                htmlReport = false
                xmlReport = false
                textReport = true
                absolutePaths = false
                checkTestSources = true
                warningsAsErrors = true
                baseline = project.file("lint-baseline.xml")
            }
        }
}
