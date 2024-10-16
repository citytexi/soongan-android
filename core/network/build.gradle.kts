plugins {
    captures("library")
    captures("network")
}

android {
    namespace = "com.captures2024.soongan.core.network"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))

    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
}