# 📈 Stocks & ETFs Explorer

An Android application for browsing, searching, and analyzing stocks & ETFs using [Alpha Vantage](https://www.alphavantage.co) APIs.

---

## ✨ Features

- Explore screen with Recently Searched, Top Gainers & Losers
- Search functionality with history
- Product page with stock overview and chart
- View All page with lazy grid and pagination

---

## 🔧 Tech Stack

- Kotlin + Jetpack Compose
- MVVM + Hilt + Retrofit
- MPAndroidChart for line graphs
- DataStore for caching searches
- Alpha Vantage API

---

## 🔑 API Key

1. Get a free API key from: https://www.alphavantage.co/support/#api-key
2. Add it to `build.gradle`:

```kotlin
buildConfigField("String", "ALPHA_VANTAGE_API_KEY", "\"YOUR_API_KEY\"")
