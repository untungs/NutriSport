import nutrisport.kmp.libs

plugins {
    id("nutrisport.kmp.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    androidLibrary {
        androidResources.enable = true
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs("compose.runtime"))
            implementation(libs("compose.foundation"))
            implementation(libs("compose.material3"))
            implementation(libs("compose.ui"))
            implementation(libs("compose.components.resources"))
            implementation(libs("compose.uiToolingPreview"))
            implementation(libs("androidx.lifecycle.viewmodelCompose"))
            implementation(libs("androidx.lifecycle.runtimeCompose"))
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs("compose.uiTooling"))
}
