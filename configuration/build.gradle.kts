// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("convention.android")
    alias(libs.plugins.kotlin.compose)
}

android { namespace = "dev.whosnickdoglio.baenotes.configuration" }

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.coroutines.android)
    implementation(libs.glance.appwidget)
    implementation(libs.glanceTool.configuration)
    implementation(libs.immutableCollections)
    implementation(projects.models)
    implementation(projects.widget)

    debugImplementation(libs.compose.ui.tooling)
}
