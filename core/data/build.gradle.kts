import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.Properties

plugins {
    id("nutrisport.kmp.library")
    alias(libs.plugins.buildkonfig)
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}

buildkonfig {
    packageName = "io.untungs.nutrisport.core.data"
    objectName = "DataBuildKonfig"

    defaultConfigs {
        buildConfigField(STRING, "SUPABASE_URL", localProperties.getProperty("supabase.url") ?: "")
        buildConfigField(STRING, "SUPABASE_KEY", localProperties.getProperty("supabase.key") ?: "")
    }
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
