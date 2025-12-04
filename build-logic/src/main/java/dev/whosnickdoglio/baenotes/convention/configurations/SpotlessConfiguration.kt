// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal class SpotlessConfiguration : Configuration {
    override fun configure(project: Project) =
        with(project) {
            pluginManager.apply("com.diffplug.spotless")
            dependOnBuildLogicTask("spotlessCheck")
            dependOnBuildLogicTask("spotlessApply")

            val catalog =
                extensions.findByType(VersionCatalogsExtension::class.java)
                    ?: error("No Catalog found!")

            val libs = catalog.named("libs")

            extensions.getByType(SpotlessExtension::class.java).run {
                format("misc") { formatExt ->
                    formatExt.target("*.md", ".gitignore")
                    formatExt.trimTrailingWhitespace()
                    formatExt.endWithNewline()
                }

                kotlin { ktExt ->
                    ktExt.ktfmt(libs.findVersion("ktfmt").get().requiredVersion).kotlinlangStyle()
                    ktExt.trimTrailingWhitespace()
                    ktExt.endWithNewline()
                    ktExt.targetExclude("**/build/generated/**")
                }
                kotlinGradle { ktsExt ->
                    ktsExt.ktfmt(libs.findVersion("ktfmt").get().requiredVersion).kotlinlangStyle()
                    ktsExt.trimTrailingWhitespace()
                    ktsExt.endWithNewline()
                }
            }
        }
}
