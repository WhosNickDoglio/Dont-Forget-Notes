// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

import dev.whosnickdoglio.baenotes.convention.configurations.NotesConfiguration
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("convention.app")
    alias(libs.plugins.licensee)
    //    alias(libs.plugins.dependencyGuard)
}

// dependencyGuard { configuration("releaseRuntimeClasspath") }

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        jvmTarget = JvmTarget.fromTarget(NotesConfiguration.javaVersion.toString())
    }
}

licensee {
    allow("Apache-2.0")
    // androidx.glance:glance-appwidget-external-protobuf
    allow("BSD-3-Clause")
}

android {
    namespace = "dev.whosnickdoglio.baenotes"
    compileSdk = NotesConfiguration.COMPILE_SDK

    defaultConfig {
        applicationId = "dev.whosnickdoglio.baenotes"
        minSdk = NotesConfiguration.MIN_SDK
        targetSdk = NotesConfiguration.TARGET_SDK
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isDebuggable = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
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
        baseline = project.file("lint-baseline.xml")
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(projects.configuration)
    implementation(projects.models)
    implementation(projects.widget)

    coreLibraryDesugaring(libs.desguar)
}
