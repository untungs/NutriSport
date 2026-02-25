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
        androidMain.dependencies {
            implementation(libs.compose.uiTooling)
        }

        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.navigation)
            implementation(projects.core.di)
            implementation(libs.kmpauth.google)
        }
    }
}
