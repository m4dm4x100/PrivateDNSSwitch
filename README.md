# Private DNS Switch

An Android Quick Settings tile to switch Private DNS on/off. Intended for OnePlus Nord CE 3 and Android 10+.

## Android limitation and setup

Android protects Private DNS settings. A normal app cannot change them unless the user grants WRITE_SECURE_SETTINGS through ADB (no root required). Enable USB debugging, connect the phone, then run:

```sh
adb shell pm grant com.m4dm4x100.privatednsswitch android.permission.WRITE_SECURE_SETTINGS
```

Install the APK, open Quick Settings → Edit (pencil) → add **Private DNS**. Tap the tile to switch off/on. When turning back on, it restores the configured hostname if one is saved; otherwise it selects Automatic DNS (opportunistic).

## Build and download

GitHub Actions builds a debug APK on pushes and uploads it as the PrivateDNSSwitch-apk artifact. Push a version tag such as v1.0.0 to publish a GitHub Release with the APK attached.
