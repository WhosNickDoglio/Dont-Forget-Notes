// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.convention.configurations

import org.gradle.api.Project

internal fun Project.dependOnBuildLogicTask(taskName: String) {
    tasks.named(taskName).configure {
        it.dependsOn(gradle.includedBuild("build-logic").task(":$taskName"))
    }
}
