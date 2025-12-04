// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
}

convention { guard { compose() } }

android { lint { disable.add("ComposeUnstableCollections") } }

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.coroutines.android)
    implementation(libs.glance.appwidget)
    implementation(libs.glanceTool.configuration)
    implementation(libs.immutableCollections)
    implementation(projects.models)
    implementation(projects.widget)

    lintChecks(libs.lints.compose)
}
