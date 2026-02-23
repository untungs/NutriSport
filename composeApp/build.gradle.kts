import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.app"
    }

    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            baseName = "NutriSport"
            isStatic = true
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}

