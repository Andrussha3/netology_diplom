# Дипломный проект по автоматизации тестирования

Полный план автоматизации и тестовая документация расположены в следующих файлах:

* 📋 **План автоматизации:** [Plan.md](./Plan.md)
* 📑 **Тест-кейсы:** [Cases.csv](./Cases.csv)
* ✅ **Чек-лист:** [Check.csv](./Check.csv)
* 📊 **Отчёт по автотестам:** [Result.md](./Result.md)

---

## Автоматизированные UI-тесты

### Предусловия

- Android SDK 36, JDK 17
- Эмулятор Android API 36 или физическое устройство
- Подключённое устройство/эмулятор (`adb devices`)

### Сборка APK

**Debug APK (для тестов):**
```bash
./gradlew app:assembleDebug
```
APK будет в `app/build/outputs/apk/debug/app-debug.apk`

**Release APK:**
```bash
./gradlew app:assembleRelease
```
APK будет в `app/build/outputs/apk/release/app-release.apk` (подписанный, minifyEnabled=true)

**Установка на устройство:**
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Запуск тестов

**Все тесты (Gradle):**
```bash
./gradlew app:connectedAndroidTest
```

**Все тесты (adb instrument):**
```bash
adb shell am instrument -w -r \
  -e class ru.edu.qamid.test.AuthTest,ru.edu.qamid.test.MainScreenTest,ru.edu.qamid.test.NewsListTest,ru.edu.qamid.test.NewsControlPanelTest,ru.edu.qamid.test.NewsFilterTest,ru.edu.qamid.test.OurMissionTest,ru.edu.qamid.test.LogoutTest,ru.edu.qamid.test.ErrorHandlingTest,ru.edu.qamid.test.FullFlowTest \
  ru.edu.qamid.test/ru.edu.qamid.CustomTestRunner
```

**Отдельный класс:**
```bash
adb shell am instrument -w -r \
  -e class ru.edu.qamid.test.AuthTest \
  ru.edu.qamid.test/ru.edu.qamid.CustomTestRunner
```

**Отдельный тест:**
```bash
adb shell am instrument -w -r \
  -e class "ru.edu.qamid.test.AuthTest#testSuccessfulLogin" \
  ru.edu.qamid.test/ru.edu.qamid.CustomTestRunner
```

### Получение Allure-результатов

После прогонов тестов результаты Allure скачиваются с устройства:

```bash
./gradlew fetchAllureResults
```

Или вручную:
```bash
adb pull /sdcard/allure-results app/build/allure-results
```

### Allure Report

Для генерации и открытия отчёта (требуется установленный Allure CLI):

```bash
# Генерация отчёта
allure generate app/build/allure-results -o allure-report --clean

# Открытие отчёта в браузере
allure open allure-report
```

**Windows (PowerShell):**
```powershell
allure generate app\build\allure-results -o allure-report --clean
allure open allure-report
```

> **Важно:** Allure-результаты сохраняются на устройстве в `/sdcard/allure-results`. Gradle-таск `fetchAllureResults` автоматически скачивает их в `app/build/allure-results` после прогонов (`connectedDebugAndroidTest.finalizedBy fetchAllureResults`).

### Структура тестов

```
app/src/androidTest/java/ru/edu/qamid/
├── di/TestNetworkModule.kt     # MockWebServer DI-модуль
├── page/                       # Page Object Model
│   ├── AuthPage.kt
│   ├── MainPage.kt
│   ├── NewsListPage.kt
│   ├── NewsControlPanelPage.kt
│   ├── CreateEditNewsPage.kt
│   ├── FilterNewsPage.kt
│   └── OurMissionPage.kt
├── test/                       # Тестовые классы
│   ├── AuthTest.kt
│   ├── MainScreenTest.kt
│   ├── NewsListTest.kt
│   ├── NewsControlPanelTest.kt
│   ├── NewsFilterTest.kt
│   ├── OurMissionTest.kt
│   ├── LogoutTest.kt
│   ├── ErrorHandlingTest.kt
│   └── FullFlowTest.kt
└── CustomTestRunner.kt         # Allure + Hilt test runner
```

### Принципы

- Page Object Model — без прямого взаимодействия с view в тестах
- Нет `Thread.sleep()` — Espresso автоматически ожидает
- MockWebServer — все API-запросы замоканы
- Независимость тестов — каждый тест создаёт своё состояние

### Зависимости для тестов

- **JUnit 4** + **Espresso** — UI-тесты
- **Allure Kotlin Android + JUnit4** — отчёты (`io.qameta.allure:allure-kotlin-android:2.4.0`, `allure-kotlin-junit4:2.4.0`)
- **Hilt Android Testing** — DI в тестах
- **MockWebServer** — мок бэкенда
- **UIAutomator** — системные действия