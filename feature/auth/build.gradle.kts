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
        }

        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(libs.kmpauth.firebase)
            implementation(libs.kmpauth.google)
            implementation(libs.koin.compose.viewmodel)
        }
    }
}
