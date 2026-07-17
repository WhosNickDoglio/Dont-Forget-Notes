// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.metro)
}

convention { published { composeGuard() } }

android { lint { disable.addAll(listOf("ComposeUnstableCollections", "Instantiatable")) } }

dependencies {
    api(libs.compose.ui)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(projects.models)
    implementation(projects.widget)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.coroutines.android)
    implementation(libs.glance.appwidget)
    implementation(libs.glanceTool.configuration)
    implementation(libs.metrox.android)

    lintChecks(libs.lints.compose)
}
