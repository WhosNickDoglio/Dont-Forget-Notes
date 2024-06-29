plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.ktfmt)
}

ktfmt { kotlinLangStyle() }

kotlin { jvmToolchain(21) }

lint {
    baseline = file("lint-baseline.xml")
    abortOnError = true
    warningsAsErrors = true
}

dependencies {
    api(libs.serialization)
}
