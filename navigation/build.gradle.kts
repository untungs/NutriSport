plugins {
    id("nutrisport.kmp.compose")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.navigation"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:auth"))
        }
    }
}