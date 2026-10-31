# HomeOS TV — Stage 1

A native Android TV launcher shell targeting Android 9 (API 28) and later.

## Included in this stage
- Persian, right-to-left dashboard shell with a restrained white/gray palette.
- TV landscape layout with large focusable cards for D-pad navigation.
- Explicit unpaired/not-configured states; no fabricated sensor values.
- Android TV `LEANBACK_LAUNCHER`, standard launcher, and HOME intent filters.
- Media3 dependencies retained for the later phone-to-TV playback stage.

## Not implemented yet
- Live Raspberry Pi connection, MQTT, device discovery, pairing/authentication.
- Real device controls, camera streams, automations, or weather data.
- 3D floor plan and phone media session handoff.

## Build locally
Open this `android-tv` directory in Android Studio. Use JDK 17 and install Android SDK Platform 35. Android Gradle Plugin 8.7.3 uses Gradle 8.9. Then run `:app:assembleDebug`.

## Build with GitHub Actions
Push this repository to GitHub, then open **Actions → Build HomeOS TV APK → Run workflow**. Download the `HomeOS-TV-debug-apk` artifact after the workflow succeeds. The workflow builds a debug APK; it is not a signed release APK.

## Install / select launcher
Install the APK on the TV. Use the system Home-app chooser to select HomeOS TV. The system may require the user to confirm the default launcher. Keep the stock launcher available until HomeOS passes device testing.
