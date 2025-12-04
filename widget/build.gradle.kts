// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("convention.android")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.whosnickdoglio.baenotes.widget"
    testOptions {
        // https://robolectric.org/getting-started/#building-with-gradle
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material)
    implementation(libs.glance.preview)
    implementation(libs.glanceTool.configuration)
    implementation(projects.models)

    debugImplementation(libs.glance.appwidget.preview)

    testImplementation(libs.assertk)
    testImplementation(libs.glance.appwidget.testing)
    testImplementation(libs.glance.testing)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}
