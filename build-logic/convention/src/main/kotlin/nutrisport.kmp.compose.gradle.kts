import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun KotlinDependencyHandler.lib(name: String) =
    implementation(libs.findLibrary(name).get())

kotlin {
    androidLibrary {
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
        androidResources {
            enable = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = project.name
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            lib("compose.runtime")
            lib("compose.foundation")
            lib("compose.material3")
            lib("compose.ui")
            lib("compose.components.resources")
            lib("compose.uiToolingPreview")
            lib("androidx.lifecycle.viewmodelCompose")
            lib("androidx.lifecycle.runtimeCompose")
        }
        commonTest.dependencies {
            lib("kotlin.test")
        }
    }
}
