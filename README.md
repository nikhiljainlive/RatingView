# RatingView
Just testing a simple library


Steps I followed to publish this library to Bintray:

- Added properties into local.properties
- Added publish.gradle to library level directory and set up configuration for bintray
- Applied publish.gradle to library level build.gradle
- Fired './gradlew build install' to generate release .aar file
- Fired './gradlew build  bintrayUpload' to upload it to bintray
- Fired './gradlew assemble' when facing issue for not generating install task
- tried cleaning projects several times when I was facing issues.