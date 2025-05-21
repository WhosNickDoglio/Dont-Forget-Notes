import dev.whosnickdoglio.baenotes.convention.configurations.NotesConfiguration

/*
 * MIT License
 *
 * Copyright (c) 2024. Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

plugins {
    id("convention.app")
    alias(libs.plugins.licensee)
    //    alias(libs.plugins.dependencyGuard)
}

// dependencyGuard { configuration("releaseRuntimeClasspath") }

licensee {
    allow("Apache-2.0")
    // androidx.glance:glance-appwidget-external-protobuf
    allow("BSD-3-Clause")
}

android {
    namespace = "dev.whosnickdoglio.baenotes"
    compileSdk = NotesConfiguration.COMPILE_SDK

    defaultConfig {
        applicationId = "dev.whosnickdoglio.baenotes"
        minSdk = NotesConfiguration.MIN_SDK
        targetSdk = NotesConfiguration.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isDebuggable = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = NotesConfiguration.javaVersion
        targetCompatibility = NotesConfiguration.javaVersion
    }
    kotlinOptions {
        allWarningsAsErrors = true
        jvmTarget = NotesConfiguration.javaVersion.toString()
    }
    lint {
        disable.addAll(
            setOf(
                "GradleDependency",
                "ObsoleteLintCustomCheck",
                "OldTargetApi",
                "AndroidGradlePluginVersion",
            )
        )
        htmlReport = false
        xmlReport = false
        textReport = true
        absolutePaths = false
        checkTestSources = true
        warningsAsErrors = true
        baseline = project.file("lint-baseline.xml")
    }
}

dependencies {
    implementation(projects.configuration)
    implementation(projects.models)
    implementation(projects.widget)

    coreLibraryDesugaring(libs.desguar)
}
