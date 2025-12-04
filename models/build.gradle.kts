// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

plugins {
    id("convention.kotlin")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(libs.serialization)
    implementation(libs.compose.runtime)
}
