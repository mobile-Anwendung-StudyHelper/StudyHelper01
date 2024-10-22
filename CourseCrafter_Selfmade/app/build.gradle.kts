plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.nfricke.coursecrafter_selfmade"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nfricke.coursecrafter_selfmade"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gson)
    implementation(libs.filament.android)
    implementation(libs.gridlayout)
    implementation(libs.cardview)
    implementation(libs.google.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("io.noties.markwon:core:4.6.2")
    implementation("io.noties.markwon:image:4.6.2")  //  Abhängigkeit für ImagesPlugin
    //implementation("io.noties.markwon:image-asset:4.6.2")
    //implementation("androidx.cardview:cardview:1.0.0")
}