// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal class KotlinConfiguration : Configuration {
    override fun configure(project: Project) =
        with(project) {
            val kotlin = extensions.getByType(KotlinProjectExtension::class.java)
            val catalog =
                extensions.findByType(VersionCatalogsExtension::class.java)
                    ?: error("No Catalog found!")

            val libs = catalog.named("libs")

            with(kotlin) {
                explicitApi()
                jvmToolchain(libs.findVersion("jdk").get().requiredVersion.toInt())
            }

            tasks.withType(KotlinCompile::class.java).configureEach {
                it.compilerOptions {
                    allWarningsAsErrors.set(true)
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }

            tasks.withType(JavaCompile::class.java).configureEach {
                it.sourceCompatibility = NotesConfiguration.javaVersion.toString()
                it.targetCompatibility = NotesConfiguration.javaVersion.toString()
            }
        }
}
