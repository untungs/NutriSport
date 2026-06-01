plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.admin"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(libs.koin.compose.viewmodel)
        }
    }
}
