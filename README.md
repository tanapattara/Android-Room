# Android-Room

## CodeLab

[Android room with view - Kotlin](https://developer.android.com/codelabs/android-room-with-a-view-kotlin#3)

## update gradle file

Apply the `kapt` annotation processor Kotlin plugin by adding it in the plugins section defined on the top of your `build.gradle` (Module: app) file.

```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
}
```

Replace the dependencies block with:

```
dependencies {
    implementation "androidx.appcompat:appcompat:$rootProject.appCompatVersion"
    implementation "androidx.activity:activity-ktx:$rootProject.activityVersion"

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation "androidx.room:room-ktx:$rootProject.roomVersion"
    kapt "androidx.room:room-compiler:$rootProject.roomVersion"
    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"

    // Lifecycle components
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.lifecycleVersion"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.lifecycleVersion"
    implementation "androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion"

    // Kotlin components
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines"

    // UI
    implementation "androidx.constraintlayout:constraintlayout:$rootProject.constraintLayoutVersion"
    implementation "com.google.android.material:material:$rootProject.materialVersion"

    // Testing
    testImplementation "junit:junit:$rootProject.junitVersion"
    androidTestImplementation "androidx.arch.core:core-testing:$rootProject.coreTestingVersion"
    androidTestImplementation ("androidx.test.espresso:espresso-core:$rootProject.espressoVersion", {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    androidTestImplementation "androidx.test.ext:junit:$rootProject.androidxJunitVersion"
}
```

Gradle may complain about missing or undefined versions at this point. They should be fixed with the next step.

In your `build.gradle` file, make the following changes: at the top of the buildscript block add the kotlin_version and then, add the version numbers to the end of the file, as given in the code below.

```
buildscript {
    ext.kotlin_version = '1.5.31'
}
ext {
    activityVersion = '1.4.0'
    appCompatVersion = '1.4.0'
    constraintLayoutVersion = '2.1.2'
    coreTestingVersion = '2.1.0'
    coroutines = '1.5.2'
    lifecycleVersion = '2.4.0'
    materialVersion = '1.4.0'
    roomVersion = '2.3.0'
    // testing
    junitVersion = '4.13.2'
    espressoVersion = '3.4.0'
    androidxJunitVersion = '1.1.3'
}
```
