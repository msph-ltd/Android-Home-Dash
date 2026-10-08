# Build verification

The distributed APK was assembled using official Android SDK Platform 35 / Build Tools 35.0.0:

- aapt2 resource compilation/linking (min API 29, target API 35).
- Java 8 bytecode compilation against android.jar plus core-lambda-stubs.jar.
- D8 dex generation/desugaring for API 29.
- zipalign and apksigner, with the included personal testing key.
- apksigner verification passed (v3 signature).
- Seven JVM tests for prayer calculation passed.

Native app networking uses HTTPS only; Calendar Provider permission and SAF folder permissions are requested at the point of use. No broad photo-storage permission, location permission, analytics or shared network secrets.

The source is also a conventional Android Studio/Gradle project. The test key is for personal development only, and is excluded by .gitignore. Gradle wrapper files are from the official Gradle v8.9.0 repository.

Runtime smoke test: installed and launched on an Android 10 emulator at 1280×800. No AndroidRuntime application exception was recorded. The software-emulated device displayed a System UI ANR during startup, so complete real-device photo-picker and slideshow validation is still required. Prayer and weather HTTPS fetches need verification on the Lenovo's Wi-Fi.
