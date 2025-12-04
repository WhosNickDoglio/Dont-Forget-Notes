// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.JavaVersion

public object NotesConfiguration {
    public val javaVersion: JavaVersion = JavaVersion.VERSION_17
    public const val MIN_SDK: Int = 30
    public const val TARGET_SDK: Int = 36
    public const val COMPILE_SDK: Int = 36
}
