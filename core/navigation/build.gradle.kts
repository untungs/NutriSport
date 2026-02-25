plugins {
    id("nutrisport.kmp.compose")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.core.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.auth)
            implementation(libs.compose.navigation)
        }
    }
}