// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

internal class AndroidLibraryConfiguration : Configuration {
    override fun configure(project: Project): Unit =
        with(project) {
            extensions.getByType(LibraryExtension::class.java).run {
                compileSdk = NotesConfiguration.COMPILE_SDK

                defaultConfig {
                    minSdk = NotesConfiguration.MIN_SDK
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
                compileOptions {
                    sourceCompatibility = NotesConfiguration.javaVersion
                    targetCompatibility = NotesConfiguration.javaVersion
                }
                lint {
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
                    baseline = file("lint-baseline.xml")
                }
            }
        }
}
