package ru.edu.qamid.test

import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import org.junit.Test
import ru.edu.qamid.BaseTest
import ru.edu.qamid.page.AuthPage
import ru.edu.qamid.page.MainPage

@HiltAndroidTest
@LargeTest
@Epic("V Hospice")
@Feature("Авторизация")
@Story("Выход из системы")
class LogoutTest : BaseTest() {

    @Test
    @Description("Выход из системы с главного экрана — возврат к экрану авторизации")
    fun tc033_logoutReturnsToAuthScreen() {
        loginAndWaitForMain()
        MainPage().clickAuthorizationMenu().clickLogout().assertAuthScreenVisible()
    }

    @Test
    @Description("Сессия очищена после выхода — перезапуск приложения показывает экран логина")
    fun tc034_sessionClearedAfterLogout() {
        loginAndWaitForMain()
        MainPage().clickAuthorizationMenu().clickLogout()
        closeActivity()
        launchActivity()
        AuthPage().assertAuthScreenVisible()
    }

    @Test
    @Description("Выход из системы через экран новостей")
    fun logoutFromNewsList() {
        loginAndWaitForMain()
        MainPage().clickAllNews()
        MainPage().clickAuthorizationMenu().clickLogout().assertAuthScreenVisible()
    }
}