plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.auth"
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.auth)
            implementation(libs.compose.uiTooling)
        }

        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(libs.kmpauth.firebase)
            implementation(libs.kmpauth.google)
            implementation(libs.messagebar.kmp)
        }
    }
}
