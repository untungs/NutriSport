plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.auth"
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiTooling)
        }

        commonMain.dependencies {
            implementation(project(":shared"))
            implementation(libs.messagebar.kmp)
        }
    }
}
