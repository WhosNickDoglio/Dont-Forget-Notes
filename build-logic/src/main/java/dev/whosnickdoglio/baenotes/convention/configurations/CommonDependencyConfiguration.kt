// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal class CommonDependencyConfiguration : Configuration {
    override fun configure(project: Project): Unit =
        with(project) {
            val catalog =
                extensions.findByType(VersionCatalogsExtension::class.java)
                    ?: error("No Catalog found!")

            val libs = catalog.named("libs")

            dependencies.add(
                "implementation",
                dependencies.platform(libs.findLibrary("compose-bom").get()),
            )
            dependencies.add(
                "implementation",
                dependencies.platform(libs.findLibrary("kotlin-bom").get()),
            )
            dependencies.add("lintChecks", libs.findLibrary("lints-compose").get())
        }
}
