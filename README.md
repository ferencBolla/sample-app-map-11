# sample-app-map-11

Android sample app using Mapbox Maps SDK v11.

## Quick Start

1) Open the project in Android Studio.

2) Add your Mapbox public access token to `local.properties` at the project root:

```
MAPBOX_ACCESS_TOKEN=your_mapbox_public_token
```

- The token must be a public token starting with `pk.`
- Do not commit `local.properties` to version control.

3) Sync Gradle and run the app on a device or emulator.

## Notes

- The build reads `MAPBOX_ACCESS_TOKEN` and injects it as `R.string.mapbox_access_token` via `app/build.gradle.kts`.
- If the token is missing or invalid, the map will not load.

## Getting a Token

- Create a free Mapbox account and generate a Public access token from your Mapbox dashboard. Copy the token that begins with `pk.` and place it in `local.properties` as shown above.
