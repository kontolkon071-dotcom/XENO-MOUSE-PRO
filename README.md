# XENO MOUSE PRO — Android starter prototype

This is a starter UI project for the XENO MOUSE PRO concept.

## Included in this first prototype
- Dark / neon-purple dashboard
- Navigation for Home, Mouse, Games, Access, Turbo, and Capture
- Demo game profiles
- Mouse sensitivity sliders (UI only)
- Access-method status cards (Root, Wireless ADB, Shizuku)
- Screenshot / screen-recording feature placeholders

## Important limitations
This is **source code for a UI prototype**, not a finished APK and not a working game input mapper.
The sliders currently change only the app UI. They do not alter another game's aim, recoil, touch input, or mouse behavior.
Root / Wireless ADB / Shizuku cards are informational placeholders; no privileged commands are executed.
Android screen recording requires the official MediaProjection permission flow. This prototype does not start recording yet.
Do not use macros to automate firing, aiming, recoil, or other gameplay actions.

## Build
Open this folder in Android Studio with a recent Android Gradle plugin and Kotlin/Compose support.
If Android Studio asks to upgrade Gradle or plugin versions, accept its compatible suggested versions.
