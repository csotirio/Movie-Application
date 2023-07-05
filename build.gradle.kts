// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlinVersion by rootProject.extra { "1.7.20" }
    val hiltVersion by rootProject.extra { "2.44.2" }
    val navVersion by rootProject.extra { "2.5.3" }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${hiltVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${navVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}