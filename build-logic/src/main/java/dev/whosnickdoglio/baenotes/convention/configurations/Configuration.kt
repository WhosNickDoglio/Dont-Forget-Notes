// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Project

internal fun interface Configuration {
    fun configure(project: Project)
}
