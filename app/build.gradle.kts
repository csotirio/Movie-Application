plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {

    namespace = "com.movieApplication"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.movieApplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = false
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = null
        }

        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions.add("env")
    productFlavors {
        create("dev") {
            dimension = "env"
            resValue("string", "app_name", "Movies Application DEV")
            buildConfigField("String", "TMDB_HOST_NAME", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "TMDB_KEY", "\"6fadde8eff17f65589b1972a2ee6c284\"")
            applicationIdSuffix = ".dev"
        }
        create("prod") {
            dimension = "env"
            resValue("string", "app_name", "Movies Application")
            buildConfigField("String", "TMDB_HOST_NAME", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "TMDB_KEY", "\"6fadde8eff17f65589b1972a2ee6c284\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")

    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    // use for main dependencies
    implementation(composeBom)
    // use for testing dependencies
    androidTestImplementation(composeBom)

    // Compose libraries
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hiltVersion")}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra.get("hiltVersion")}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    val accompanistVersion by extra { "0.28.0" }
    implementation("com.google.accompanist:accompanist-insets:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-insets-ui:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-webview:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-flowlayout:$accompanistVersion")

    implementation("androidx.paging:paging-compose:3.2.0-rc01")
    implementation("androidx.paging:paging-common-ktx:3.1.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
        because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
    }
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
        because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
    }

    implementation("com.jakewharton.timber:timber:4.7.1")

    val okHttpVersion by extra { "4.9.0" }
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
    implementation("com.squareup.okhttp3:okhttp-sse:$okHttpVersion")

    val moshiVersion by extra { "1.13.0" }
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    val retrofitVersion by extra { "2.9.0" }
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    implementation("io.coil-kt:coil-compose:2.0.0")

    // test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}