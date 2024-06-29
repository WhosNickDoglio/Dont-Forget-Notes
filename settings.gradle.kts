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
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

rootProject.name = "Bae-Notes"

include(":models")
include(":widget")
include(":configuration")
include(":app")


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("glance-experimental-tools") {
    dependencySubstitution {
        substitute(module("com.google.android.glance.tools:appwidget-configuration"))
            .using(project(":appwidget-configuration"))
    }
}
