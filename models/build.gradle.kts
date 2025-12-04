// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    alias(libs.plugins.convention.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(libs.serialization)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.kotlin.bom))
    implementation(libs.compose.runtime)
}
