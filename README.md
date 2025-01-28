# 🧪 Kotlin Testing Project - JUnit & Gradle Automation Framework

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-blue.svg)
![JUnit](https://img.shields.io/badge/JUnit-5.8.1-green.svg)
![Gradle](https://img.shields.io/badge/Gradle-7.3-yellow.svg)
![License](https://img.shields.io/badge/License-MIT-lightgray.svg)

## 📌 Project Overview

This project is a **unit testing framework** written in **Kotlin**, utilizing **JUnit 5** and **Gradle** for test execution and reporting. The purpose of this project is to **test core functionalities** of a **match simulation system**, which includes managing teams, capturing objectives, and determining the match winner.

This project has been created for **testing purposes** and serves as an example of implementing **JUnit testing in Kotlin**.

---

## 🚀 Key Features

✅ **Unit testing with JUnit 5** – Structured test cases for match and team mechanics  
✅ **Gradle-based test execution** – Easily run tests using Gradle commands  
✅ **Supports match simulation** – Includes mechanics like **tower destruction, capturing Baron and Dragon, and gold tracking**  
✅ **Automatically generated test reports** – HTML reports available after test execution  
✅ **Reusable modular structure** – Designed for easy expansion and modification

---

## 🛠 Tools and Technologies Used

| Component | Description |
|-----------|------------|
| **Programming Language** | Kotlin 1.9.23 |
| **Testing Framework** | JUnit 5 |
| **Build System** | Gradle |
| **IDE** | JetBrains IntelliJ IDEA (Community Edition) |
| **Test Reports** | Auto-generated HTML reports |

---

## 📋 Prerequisites

Before running this project, make sure you have the following installed:

- 🏗 **[JetBrains IntelliJ IDEA (Community Edition)](https://www.jetbrains.com/idea/download/)** – Recommended IDE for Kotlin development
- 📜 **[Java 20+](https://adoptium.net/)** – Required to run Kotlin and Gradle
- 🔧 **[Gradle 7+](https://gradle.org/install/)** – Build automation tool

To verify installations, run:
```sh
java -version
gradle -v
```

---

## 📥 Setup Instructions

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/filipjosipbionda/KotlinTestingProject.git
cd KotlinTestingProject
```

### 2️⃣ Open the Project in IntelliJ IDEA
- Open **IntelliJ IDEA**
- Select **"Open Project"** and navigate to the cloned directory
- Ensure the Kotlin and Gradle plugins are installed

### 3️⃣ Run the Project
To build the project using **Gradle**, execute:
```sh
./gradlew build
```
or on Windows:
```sh
gradlew.bat build
```

---

## 🧪 Running the Tests

### ✅ Method 1: Using IntelliJ IDEA
1. Open IntelliJ IDEA
2. Go to **"Run" → "Edit Configurations"**
3. Select **JUnit** as the test runner
4. Click **Run All Tests**

### ✅ Method 2: Using Gradle
Run all tests from the terminal:
```sh
./gradlew test
```
or on Windows:
```sh
gradlew.bat test
```

### 📜 Viewing the Test Report
Once the tests finish running, open the **HTML test report**:
```
build/reports/tests/test/index.html
```
To open it from the command line:
```sh
open build/reports/tests/test/index.html  # macOS/Linux
start build/reports/tests/test/index.html  # Windows
```

---

## 📌 Test Scenarios

The test suite enables testing of various **Champion functionalities**, tracking **team statistics**, and monitoring **match execution**. The goal is to validate essential gameplay mechanics through structured unit tests.

🔹 **Champion Testing** – Ensures that all available champion types function correctly  
🔹 **Team Performance Tracking** – Verifies that team statistics, such as kills, objectives, and gold, update properly  
🔹 **Match Execution Monitoring** – Checks that the match progresses as expected based on actions taken by champions and teams

📢 **Note:** This test suite provides a **basic implementation** of unit testing in Kotlin. More advanced systems would require significantly more complex tests, but the primary goal here is to demonstrate how to write and execute unit tests effectively.

---

## ⚠ Known Issues

⚠ Ensure that **Gradle dependencies** are correctly installed before running tests. Run:
```sh
./gradlew dependencies
```

⚠ If **HTML test reports** do not generate, verify that Gradle is configured to produce reports:
```kotlin
tasks.test {
    useJUnitPlatform()
    reports {
        html.required.set(true)
        junitXml.required.set(true)
    }
}
```

---

## 📄 License

📜 This project is licensed under the **MIT License**. See the [`LICENSE`](LICENSE) file for details.

---

