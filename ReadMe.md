PPOB Express
============


*Supported Services:*

- [MobilePulsa](https://developer.iak.id/)

*App Library:*

- UI
    - [Splashscreen API](https://developer.android.com/develop/ui/views/launch/splash-screen/migrate)
    - Glide
    - Material 2
    - [Navigation API](https://developer.android.com/guide/navigation/get-started)
    - [Lottie](https://lottiefiles.com/blog/working-with-lottie/getting-started-with-lottie-animations-in-android-app)
    - [ZigZagView](https://github.com/beigirad/ZigzagView)
    - [Custom TextInputLayout](https://github.com/AndroidLab/InputLayoutInnerHint)
- Monitor
    - Firebase Distribute
    - [Google Admob](https://developers.google.com/admob/android/banner/anchored-adaptive)
- Network
    - Retrofit
    - Okhttp
    - Flow
    - Coroutines
- Dependency Injection
    - Hilt
- Database
    - Room

Continues Integrations & Continue Delivery (CI/CD)
=================================================

The config of CI/CD is based of tutorial on this Medium :

- [Part 1](https://proandroiddev.com/ci-cd-pipeline-for-flavoured-android-apps-using-fastlane-and-github-actions-51667b7175af)
- [Part 2](https://proandroiddev.com/ci-cd-for-android-devs-ii-github-actions-masterclass-8a033bbaf42d)
- [Part 3](https://proandroiddev.com/ci-cd-for-android-developers-iii-building-pipelines-with-github-actions-e328f26f414a)

Tech Stack :

- Ruby
- FastLane
- Firebase Distribute
- Github Actions

FAQ
===

<details><summary>How to run Fastlane</summary>
   > fastlane android NAME_OF_LANE
  </details>

<details><summary>Fastlane: the server responded with status 404</summary>
  Need to accept something on menu Firebase Distribute Menu, yep.. click that get started button.
  </details>

<details><summary>Fastlane: Invalid request</summary>    
  In my case, i forget to create group of tester.. so maybe you need to create it.
   </details>

Asset
=====

- [Icon Kitchen](https://icon.kitchen)