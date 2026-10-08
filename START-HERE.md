# HM Home — first Android build

For Hanna & Miqdad's Lenovo M10. Android 10 or later. Landscape, warm ivory and espresso, with your HM logo.

## Install

1. Extract this ZIP on Windows.
2. Copy HM-Home-v0.1.apk to your Lenovo (USB, Drive, or another transfer method).
3. On the Lenovo, open the APK using Files.
4. If prompted, allow that app to install unknown apps, install HM Home, then turn that permission off again if you prefer.
5. Open HM Home. It needs internet to download the London prayer timetable and weather. No account login is needed for these.

The APK is signed for personal testing. No Play Store account is needed. The source includes personal-test.keystore (standard test password: android) so local debug builds can update this APK. Keep this ZIP and key private. Do not publish this key or use it for a public Play Store release.

## What works in this version

- Native Android dashboard and HM branding.
- Live London Hujjat prayer timetable and next-prayer countdown.
- Tomorrow's Fajr after Maghrib; London timezone and daylight-saving handling.
- Locally cached timetable. Missing times display unavailable rather than guessed times.
- London weather, attributed to Open-Meteo.
- Read-only combined agenda from calendars synced to the tablet, with calendar selection.
- Persistent permission to one selected local photo folder.
- Photo slideshow: shuffle or filename order, fit/fill, slide duration, inactivity timer.
- Clock and next-prayer overlay; optional night dimming between 22:00 and 06:00 London time.
- Tap a photo to return home. Back returns home.
- Keeps the screen awake while the app is in the foreground.
- Shortcut to the installed Google Home app for your devices.

## Local photographs

1. Create a folder such as Pictures/HM Photos on the tablet and put JPG, PNG or WebP files in it.
2. In HM Home, Photos → Choose local folder.
3. Select the folder with Android's picker and approve access.
4. Start slideshow, or let the app idle for two minutes (adjust in Settings).

Only files directly inside the selected folder are included. Folder contents refresh every minute or with Refresh folder. Portrait pictures fit with margins by default; Fill crops edges. Large photos are downsampled for the 2GB tablet; EXIF rotation is applied. Folder access persists across restarts unless Android revokes it or the folder is moved. Photos are never uploaded.

## Your four calendar sources

MSPH Google, Horizon Google, shared iCloud and Hanna's iCloud are the intended sources. This build uses Android's Calendar Provider: they must first exist in the tablet's synced calendars.

- Add the two Google accounts to the tablet and enable calendar sync.
- iCloud is not connected directly in this build. Use an existing trusted CalDAV sync setup to make its calendars available to Android; Apple app-specific credentials belong only in that sync app, not HM Home.
- In HM Home choose Calendars, grant read permission, then select the calendars you want displayed.
- Work account policies may restrict syncing to this tablet. Do not bypass them.
- Events and calendar colours are read from the tablet. The app cannot edit or send events.

## Google Home and Namaz announcements

This app does NOT yet embed the Google Home SDK or control lights directly. Living room controls opens Google Home. Direct device controls require the next integration phase, including Google's SDK download and OAuth configuration; we won't show pretend device states.

The working Cloudflare → Google Home → Nest reminder service remains separate. HM Home does not alter its secrets, switch or scheduler. It does not play Adhan locally or schedule Android background reminders in this build. Those are separate next features. Keep testing the deployed cloud schedule independently.

## Build from source on Windows

Install Android Studio with Android SDK Platform 35 and JDK 17. Open the source folder as a Gradle project. Gradle plugin 8.7.3; Gradle 8.9. If Android Studio asks for a compatible Gradle distribution, choose 8.9. Use Build → Build APK(s). No third-party app dependencies or API secrets are included. The included Gradle wrapper downloads Gradle 8.9. From PowerShell you can also run .\gradlew.bat assembleDebug after Android Studio has configured your SDK.

Java source lives under app/src/main/java/uk/msph/hmhome. PrayerClock.java contains the testable time logic; tests/PrayerClockTest.java exercises date padding, countdowns, next-day rollover and DST.

## Validation and limits

Native Java compilation, resource packaging and APK signing verification passed. Seven prayer/date/DST checks passed. Tablet behaviour still needs your on-device verification. This is version 0.1, a functional foundation, not the finished account-integrated dashboard.

Photo folder access, rendering and inactivity need testing with your actual tablet and photo collection. Weather is fixed to London initially. Source credentials and Google service-account keys are never needed in this Android build. No analytics or photo upload service is present.

Sources:
- https://developer.android.com/training/data-storage/shared/documents-files
- https://developer.android.com/identity/providers/calendar-provider
- https://open-meteo.com/
