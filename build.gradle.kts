// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.dependencyGuard) apply false
    alias(libs.plugins.doctor)
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.app.versioning) apply false
    alias(libs.plugins.convention.app) apply false
    alias(libs.plugins.convention.android.library) apply false
    alias(libs.plugins.convention.jvm) apply false
    alias(libs.plugins.convention.kmp) apply false
    alias(libs.plugins.cacheFix) apply false
    alias(libs.plugins.composeGuard) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktfmt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.licensee) apply false
    alias(libs.plugins.sortDependencies) apply false
}

// https://docs.gradle.org/8.9/userguide/gradle_daemon.html#daemon_jvm_criteria
tasks.updateDaemonJvm.configure {
    languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

// https://github.com/ben-manes/gradle-versions-plugin?tab=readme-ov-file#rejectversionsif-and-componentselection
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
