# HomeOS — Modular MVP Starter

This repository separates HomeOS into independently developed components:

- `android-tv/` — Android TV launcher and media-player client (Kotlin / Media3)
- `android-phone/` — Android phone companion app (Kotlin; local-media and online-source integration scaffold)
- `raspberry-pi/` — Go Core, Mosquitto configuration, service and network setup templates
- `firmware/` — ESP32 firmware starter (ESP-IDF / MQTT)
- `protocols/` — versioned JSON schemas and MQTT topic contract
- `docs/` — architecture, security, build and test plans

## Important status

This is an **engineering starter/MVP scaffold**, not a production-certified smart-home system.
The Go Core has a minimal health/status API. Android projects contain build configuration and starter source.
Phone-to-TV streaming, hardware discovery, real device drivers, OTA, authentication provisioning,
and device-specific codec compatibility still require implementation and hardware testing.

## Start here

1. Read `docs/ARCHITECTURE.md`.
2. Read `docs/BUILD_STATUS.md` for what is implemented and what is still a placeholder.
3. Start the Go Core:
   ```bash
   cd raspberry-pi/core
   go run ./cmd/homeos
   ```
   Then open `http://127.0.0.1:8080/health`.
4. Validate schemas in `protocols/schemas/`.
5. Build Android projects in Android Studio after installing a compatible Android SDK.

## Hardware assumptions

- Android TV: Android 9 / API 28 or newer.
- Phone: Android 8+ recommended; actual background-service behavior varies by vendor/version.
- Raspberry Pi: 64-bit Raspberry Pi OS recommended.
- ESP32: ESP-IDF toolchain and a board-specific configuration required.

Do not expose MQTT or the Core API directly to the public internet. Use a VPN such as WireGuard/Tailscale
for remote access and configure device-specific credentials before connecting real equipment.
