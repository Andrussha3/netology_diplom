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

### Запуск тестов

```bash
./gradlew app:connectedAndroidTest
```

### Структура

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
└── test/                       # Тестовые классы
    ├── AuthTest.kt
    ├── MainScreenTest.kt
    ├── NewsListTest.kt
    ├── NewsControlPanelTest.kt
    ├── NewsFilterTest.kt
    ├── OurMissionTest.kt
    ├── LogoutTest.kt
    └── ErrorHandlingTest.kt
```

### Принципы

- Page Object Model — без прямого взаимодействия с view в тестах
- Нет `Thread.sleep()` — Espresso автоматически ожидает
- MockWebServer — все API-запросы замоканы
- Независимость тестов — каждый тест создаёт своё состояние
