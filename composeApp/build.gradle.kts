import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.app"
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.data)
            implementation(projects.core.ui)
            implementation(projects.core.navigation)
            implementation(projects.feature.auth)
            implementation(libs.kmpauth.google)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.messagebar.kmp)
        }
    }
}
