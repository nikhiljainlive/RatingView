# RatingView
[![](https://jitpack.io/v/nikhiljainlive/RatingView.svg)](https://jitpack.io/#nikhiljainlive/RatingView) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [ ![](https://api.bintray.com/packages/nikhiljain/RatingView/com.nikhiljain.ratingview/images/download.svg) ](https://bintray.com/nikhiljain/RatingView/com.nikhiljain.ratingview/_latestVersion)

RatingView shows ratings count for any item.

### Steps I followed to publish this library to Bintray:

- Added properties into local.properties
- Added publish.gradle to library level directory and set up configuration for bintray
- Applied publish.gradle to library level build.gradle
- Fired './gradlew build install' to generate release .aar file
- Fired './gradlew build  bintrayUpload' to upload it to bintray
- Fired './gradlew assemble' when facing issue for not generating install task
- tried cleaning projects several times when I was facing issues.
