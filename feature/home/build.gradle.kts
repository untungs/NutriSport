plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.home"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.core.navigation)
            implementation(libs.compose.navigation)
            implementation(libs.koin.compose.viewmodel)
        }
    }
}
