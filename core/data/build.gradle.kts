plugins {
    id("nutrisport.kmp.library")
}

kotlin {
    androidLibrary {
        namespace = "io.untungs.nutrisport.core.data"
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
        }

        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.supabase.bom))
            implementation(projects.core.domain)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.firestore)
            implementation(libs.supabase.storage)
            implementation(libs.koin.core)
        }
    }
}
