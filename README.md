# tolist ✅ (August, 2024)

**tolist** is a modern, fullstack to-do list application for Android, built to showcase the latest advancements in mobile development. The user interface is crafted entirely with Jetpack Compose, offering a clean, dynamic, and responsive experience.

This project serves as a practical implementation of modern Android architecture and best practices.

---

## About The Project

The goal of **tolist** is to provide a simple yet powerful tool for tracking daily tasks. It was developed with a focus on clean code, a reactive UI, and a decoupled architecture, making it both scalable and easy to maintain. This project emphasizes leveraging the latest technologies from Google, including Jetpack Compose for the UI and other Jetpack libraries for architecture and data handling.

---

## Tech Stack

This project follows a modern, fullstack approach, with a native Android client and a flexible backend.

### **Frontend (Android)**
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-000?style=for-the-badge&logo=jetpackcompose&logoColor=4285F4)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)

* **IDE:** Android Studio
* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Architecture:** MVVM (Model-View-ViewModel)

### **Backend (Fullstack Component)**

**Backend-as-a-Service (BaaS):** Firebase

---

## Features

* **Create, Read, Update & Delete (CRUD)** tasks seamlessly.
* **Mark Tasks Complete:** Easily toggle the completion status of any task.
* **Modern UI:** A beautiful and intuitive interface built entirely with Jetpack Compose.
* **State-driven UI:** The UI reacts to state changes, ensuring consistency and reliability.
* **Synchronization:** Syncs with a backend service to keep your to-do list updated across sessions (if a backend is configured).

---

## Architecture

This app is built using the **Model-View-ViewModel (MVVM)** architecture pattern, which is highly recommended for modern Android development.

* **Model:** Represents the data layer, handling data from both the local database (Room) and the remote API.
* **View:** The UI layer, built with Jetpack Compose. It observes the ViewModel for state changes and is responsible for displaying data to the user.
* **ViewModel:** Acts as a bridge between the Model and the View. It holds and processes all the data required by the UI and exposes it as observable state, surviving configuration changes.

---

## Getting Started

To get a local copy up and running, follow these simple steps.

### **Prerequisites**

* [Android Studio](https://developer.android.com/studio) (latest version recommended)
* JDK 11 or higher

### **Installation**

1.  **Clone the repository**
    ```sh
    git clone [https://github.com/your-username/tolist.git](https://github.com/your-username/tolist.git)
    ```

2.  **Open in Android Studio**
    * Open Android Studio.
    * Select `File > Open` and navigate to the cloned project directory.
    * Let Android Studio sync and build the project.

3.  **(Optional) Configure the Backend**
    * If you are connecting to a backend API, locate the network configuration file (e.g., in a `Constants.kt` or a DI module).
    * Update the `BASE_URL` to point to your running backend server.

4.  **Run the App**
    * Select an emulator or connect a physical device.
    * Click the 'Run' button in Android Studio.

