plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    //buildscript :  Firebase를 쓰기위한 작업
    id("com.google.gms.google-services")
}

android {
    namespace = "com.athena.projectgroupwareapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.athena.projectgroupwareapp"
        minSdk = 27
        targetSdk = 33
        versionCode = 6
        versionName = "1.5.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

// debug버전도 찾아보기
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {
    implementation("com.github.bumptech.glide:glide:4.15.1") //글라이드
    implementation("de.hdodenhof:circleimageview:3.1.0") //서클뷰

    //카카오 맵
    implementation(files("libs/libDaumMapAndroid.jar"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    // Firebase core library - 꼭 필요한 코어 라이브러리
    implementation(platform("com.google.firebase:firebase-bom:31.3.0"))
    // Firebase product SDK - 데이터베이스
    implementation("com.google.firebase:firebase-firestore-ktx")
    // Firebase product SDK - 이미지
    implementation("com.google.firebase:firebase-storage-ktx")

    // Compose UI 라이브러리 의존성 추가
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.ui:ui-tooling:1.0.5")
    implementation ("androidx.compose.foundation:foundation:1.0.5")
    implementation ("androidx.compose.material3:material3:1.0.0-beta02")

    //splash
    implementation ("androidx.core:core-splashscreen:1.0.1")



    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")

}