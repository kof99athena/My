// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath ("com.google.gms:google-services:4.3.15")
    }
}
//buildscript :  Firebase를 쓰기위한 작업


plugins {

    id("com.android.application") version "8.1.0-alpha11" apply false
    id("com.android.library") version "8.1.0-alpha11" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false


}