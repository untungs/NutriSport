plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.auth"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.kmpauth.firebase)
            implementation(libs.kmpauth.google)
            implementation(libs.messagebar.kmp)
        }
    }
}
