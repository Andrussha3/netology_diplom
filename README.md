# Дипломный проект по автоматизации тестирования

Полный план автоматизации и тестовая документация расположены в следующих файлах:

* **План автоматизации:** [Plan.md](./Plan.md)
* **Тест-кейсы:** [Cases.csv](./Cases.csv)
* **Чек-лист:** [Check.csv](./Check.csv)
* **Отчёт по тестированию:** [Result.md](./Result.md)

---

## Автоматизированные UI-тесты

### Предусловия

- Android SDK 36, JDK 17
- Эмулятор Android API 36 или физическое устройство
- Подключённое устройство/эмулятор (`adb devices`)

### Запуск тестов с Allure-отчётом

**Все тесты:**
```bash
./gradlew connectedDebugAndroidTest fetchAllureResults allureReport
```

**Отдельный класс:**
```bash
./gradlew connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.class=ru.edu.qamid.test.AuthTest" fetchAllureResults allureReport
```

**Отдельный тест:**
```bash
./gradlew connectedDebugAndroidTest "-Pandroid.testInstrumentationRunnerArguments.class=ru.edu.qamid.test.AuthTest#tc001_successfulAuthorization" fetchAllureResults allureReport
```

Готовый Allure-отчёт откроется в браузере из `app/build/reports/allure-report/allureReport/index.html`.

### Запуск без отчёта (только тесты)

```bash
./gradlew connectedDebugAndroidTest
```

### Структура тестов

```
app/src/androidTest/java/ru/edu/qamid/
├── CustomTestRunner.kt              # Allure + Hilt test runner
├── BaseTest.kt                      # Базовый класс тестов
├── di/TestNetworkModule.kt          # MockWebServer DI-модуль
├── OkHttpIdlingResource.kt          # IdlingResource для OkHttp
├── page/                            # Page Object Model
│   ├── AuthPage.kt
│   ├── MainPage.kt
│   ├── NewsListPage.kt
│   ├── NewsControlPanelPage.kt
│   ├── CreateEditNewsPage.kt
│   ├── FilterNewsPage.kt
│   └── OurMissionPage.kt
└── test/                            # Тестовые классы
    ├── AuthTest.kt                  # TC-001 – TC-006
    ├── MainScreenTest.kt            # TC-009, TC-010
    ├── NewsListTest.kt              # TC-011, TC-012
    ├── NewsFilterTest.kt            # TC-015, TC-018
    ├── NewsControlPanelTest.kt      # TC-019, TC-023, TC-024
    ├── OurMissionTest.kt            # TC-031
    ├── LogoutTest.kt                # TC-033, TC-034
    ├── ErrorHandlingTest.kt         # TC-005, TC-007
    └── FullFlowTest.kt              # Комплексные сценарии
```

### Принципы

- Page Object Model — без прямого взаимодействия с view в тестах
- Нет `Thread.sleep()` — Espresso автоматически ожидает
- Независимость тестов — каждый тест создаёт своё состояние

### Зависимости для тестов

- **JUnit 4** + **Espresso** — UI-тесты
- **Allure Kotlin Android + JUnit4** — отчёты (`io.qameta.allure:allure-kotlin-android:2.4.0`, `allure-kotlin-junit4:2.4.0`)
- **Hilt Android Testing** — DI в тестах
- **UIAutomator** — системные действия
