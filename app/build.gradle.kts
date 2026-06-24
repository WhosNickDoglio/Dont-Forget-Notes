// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.app)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.licensee)
    alias(libs.plugins.app.versioning)
    alias(libs.plugins.metro)
}

licensee {
    allow("Apache-2.0")
    // androidx.glance:glance-appwidget-external-protobuf
    allow("BSD-3-Clause")
}

android {
    defaultConfig { applicationId = "dev.whosnickdoglio.baenotes" }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            )
        }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.work.runtime)
    implementation(libs.metrox.android)
    implementation(projects.configuration)
    implementation(projects.graphOwner)
    implementation(projects.models)
    implementation(projects.widget)

    coreLibraryDesugaring(libs.desguar)

    lintChecks(libs.lints.compose)
}
