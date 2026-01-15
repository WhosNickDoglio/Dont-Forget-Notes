// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://central.sonatype.com/repository/maven-snapshots/")
    }
}

rootProject.name = "Dont-Forget-Notes"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("com.gradle.develocity") version "4.3.1"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "2.4.0"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}

include(
    ":app",
    ":configuration",
    ":models",
    ":widget",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
