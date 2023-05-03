# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.kakao.sdk.**.model.* { <fields>; }


# https://github.com/square/okhttp/pull/6792
-dontwarn org.bouncycastle.jsse.**
-dontwarn org.conscrypt.*
-dontwarn org.openjsse.**


#데이터 클래스 난독화 풀기
-keep class com.athena.projectgroupwareapp.drawer.approval.**
-keep class com.athena.projectgroupwareapp.drawer.approval.** { *; }

-keep class com.athena.projectgroupwareapp.drawer.approval.recycler.**
-keep class com.athena.projectgroupwareapp.drawer.approval.recycler.** { *; }

-keep class com.athena.projectgroupwareapp.drawer.attendance.**
-keep class com.athena.projectgroupwareapp.drawer.attendance.** { *; }

-keep class com.athena.projectgroupwareapp.drawer.attendance.recycler.**
-keep class com.athena.projectgroupwareapp.drawer.attendance.recycler.** { *; }

-keep class com.athena.projectgroupwareapp.login.**
-keep class com.athena.projectgroupwareapp.login.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab1.**
-keep class com.athena.projectgroupwareapp.main.tab1.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab2.chatting.**
-keep class com.athena.projectgroupwareapp.main.tab2.chatting.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab2.recycler.**
-keep class com.athena.projectgroupwareapp.main.tab2.recycler.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab3.business.**
-keep class com.athena.projectgroupwareapp.main.tab3.business.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab3.it.**
-keep class com.athena.projectgroupwareapp.main.tab3.it.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab3.management.**
-keep class com.athena.projectgroupwareapp.main.tab3.management.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab3.recycler.**
-keep class com.athena.projectgroupwareapp.main.tab3.recycler.** { *; }

-keep class com.athena.projectgroupwareapp.main.tab3.**
-keep class com.athena.projectgroupwareapp.main.tab3.**  { *; }


#카카오맵 지도 난독화 풀기
-keep class net.daum.**
-keep class net.daum.** { *; }