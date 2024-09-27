plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.okhttp)
    alias(libs.plugins.captures2024.soongan.retrofit)
}

android {
    namespace = "com.captures2024.soongan.core.network"

    defaultConfig {
        val properties = loadProperties()

        val capturesBaseUrl = DefaultKeyValue.isAllowedBaseUrl(properties["capturesBaseUrl"] as? String)

        manifestPlaceholders["CAPTURES_BASE_URL"] = capturesBaseUrl

        buildConfigField("String", "CAPTURES_BASE_URL", "\"${capturesBaseUrl}\"")
    }
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))
}
