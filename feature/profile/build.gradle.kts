plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.profile"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
        }
    }
}
