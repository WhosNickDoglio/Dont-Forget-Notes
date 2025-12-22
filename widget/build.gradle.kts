// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
}

convention { guard { compose() } }

android {
    testOptions {
        // https://robolectric.org/getting-started/#building-with-gradle
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.glance.appwidget)
    implementation(libs.glance.appwidget.preview)
    implementation(libs.glance.material)
    implementation(libs.glance.preview)
    implementation(libs.glanceTool.configuration)
    implementation(projects.models)

    testImplementation(libs.assertk)
    testImplementation(libs.glance.appwidget.testing)
    testImplementation(libs.glance.testing)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)

    lintChecks(libs.lints.compose)
}
