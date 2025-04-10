## 🔑 API Token Setup

This project uses the OpenWeather API, and requires an API token to function.

### 📍 Step-by-step:

1. Open your `build.gradle` (Module: app) file.
2. Locate the `buildConfigField` line for `OPEN_WEATHER_TOKEN`.

It should look like this:

```kotlin
buildConfigField("String", "OPEN_WEATHER_TOKEN", "\"<REPLACE_TOKEN_HERE>\"")