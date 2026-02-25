# 🚀 NutriSport

[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS-orange.svg)](https://kotlinlang.org/lp/compose-multiplatform/)
[![Status](https://img.shields.io/badge/Status-Under%20Development-blue.svg)](https://github.com/untungs/NutriSport)

**NutriSport** is a modern **E-commerce application** designed for selling healthy sports nutrition and wellness products. It serves as a comprehensive learning project utilizing the power of Kotlin Multiplatform (KMP) to deliver a unified experience across **Android and iOS**.

---

## ✨ Key Features & Learning Goals

This project is built as a deep dive into KMP, leveraging modern best practices:

*   **Cross-Platform UI:** 100% native UI implemented using **Compose Multiplatform**.
*   **Unified Business Logic:** Core business logic, data handling, and networking shared between platforms.
*   **Modern Tooling:** Utilizing the latest infrastructure, including **Android Gradle Plugin (AGP) 9** and **Gradle Build Logic Conventions** for improved modularity and build speed.
*   **Modern Design:** Implementation of a **Dark Theme** based on **Material 3**.

This project is based on the excellent foundation provided by [stevdza-san's online course](https://github.com/stevdza-san/NutriSport), extended with newer architectural patterns and tooling.

---

## 🛠️ Tech Stack

| Category | Technology | Purpose |
| :--- | :--- | :--- |
| **Core Framework** | [Kotlin Multiplatform](https://www.jetbrains.com/kotlin-multiplatform/) | Targets Android and iOS platforms. |
| **UI Layer** | [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) | Declarative UI for both mobile platforms. |
| **Concurrency** | [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) | Handling asynchronous operations efficiently. |
| **Navigation** | [Compose Navigation](https://kotlinlang.org/docs/multiplatform/compose-navigation-routing.html) | Multiplatform navigation routing. |
| **Dependency Injection**| [Koin](https://insert-koin.io/) | Lightweight, modular DI framework. |
| **Authentication** | [KMPAuth](https://github.com/mirzemehdi/KMPAuth/) | Shared multiplatform authentication handling. |

---

## 🖼️ Screenshots (Placeholder)

*TBA*

---

## 💡 Getting Started

### Prerequisites

*   Android Studio (latest version recommended)
*   Xcode (for iOS compilation on macOS)
*   Kotlin tooling setup

### Clone the Repository

```bash
git clone https://github.com/untungs/NutriSport.git
cd NutriSport
```

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :androidApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :androidApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

