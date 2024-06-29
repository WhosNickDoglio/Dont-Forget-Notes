plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktfmt)
}

ktfmt { kotlinLangStyle() }

kotlin { jvmToolchain(21) }

android {
    namespace = "dev.whosnickdoglio.baenotes.widget"
    compileSdk = 34

    defaultConfig { minSdk = 24 }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        allWarningsAsErrors = true
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures { compose = true }

    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = true
        warningsAsErrors = true
    }
}

dependencies {
    lintChecks(libs.lint.compose)

    implementation(projects.models)

    implementation(libs.androidx.core)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material)

    implementation(libs.immutableCollections)

    implementation(libs.glance.appwidget)
    implementation(libs.glanceTool.configuration)
}
