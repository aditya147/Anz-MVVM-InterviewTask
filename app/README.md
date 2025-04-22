# 🧑‍💻 My ANZ Interview Task

An Android application developed for the ANZ interview task. It demonstrates modern Android app development practices using Jetpack Compose,Modularized Architecture, Hilt, and Kotlin Coroutines.

---

## 🚀 Features

- ✅ Fetch and display user data from API
- 🧭 MVVM architecture with ViewModel and Repository pattern
- 🌊 State management using Kotlin `StateFlow`
- 💉 Dependency Injection with Hilt
- 📱 Built with Jetpack Compose
- 🧪 Unit & UI testing (ViewModel & Compose UI)
- 📡 Network communication with Retrofit

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM 
- **Dependency Injection:** Hilt
- **Networking:** Retrofit, Coroutines, Flow
- **Testing:** JUnit, MockK, Turbine, Compose UI Test

---

## 🧪 Testing

Includes ViewModel and UI tests for stable, test-driven development:

- ✅ Unit tests with `JUnit` + `MockK`
- ✅ Flow testing with `Turbine`
- ✅ UI tests with `Compose Testing`


## 🧩 Project Structure

```text
.
├── data/               # Models & Repositories
├── di/                 # Hilt modules
├── ui/                 # Screens & ViewModels
├── utils/              # Helpers and constants
└── MainActivity.kt     # App Entry Point

## 🧠 Assumptions

** API responses are well-structured and reliable (no pagination/filters implemented)
** All user fields (e.g., name, address, photo) are non-null
** The task does not require local data caching or Room integration

## 📈 Possible Improvements

🔁 Add pagination support for large user datasets
💾 Add local caching using Room or DataStore
🌐 Handle edge cases like slow networks or offline mode
🔐 Add error messages with retry mechanisms and loading states
🧩 Add more comprehensive UI/UX design with animations
🧪 Increase test coverage with edge case and integration tests

## 👨‍🎓 Author
Aditya S Rajawat
📧 aditya.dijango@gmail.com
💼 https://www.linkedin.com/in/aditya-s-rajawat-84461a46/