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

@HiltAndroidTest
@LargeTest
@Epic("V Hospice")
@Feature("Авторизация")
@Story("Авторизация пользователя")
class AuthTest : BaseTest() {

    @Test
    @Description("Успешная авторизация с верными учетными данными (login2 / password2)")
    fun tc001_successfulAuthorization() {
        ensureOnAuthScreen()
        AuthPage()
            .enterLogin(LOGIN)
            .enterPassword(PASSWORD)
            .clickLogin()
            .assertMainScreenLoaded()
    }

    @Test
    @Description("Попытка входа с пустыми полями — остаемся на экране авторизации")
    fun tc002_loginWithEmptyFields() {
        ensureOnAuthScreen()
        AuthPage()
            .clickLoginWithoutCloseKeyboard()
            .assertAuthScreenVisible()
    }

    @Test
    @Description("Пустой логин, введен пароль — ошибка, остаемся на экране авторизации")
    fun tc003_loginWithEmptyLogin() {
        ensureOnAuthScreen()
        AuthPage()
            .enterPassword(PASSWORD)
            .clickLoginExpectingError()
            .assertAuthScreenVisible()
    }

    @Test
    @Description("Введен логин, пустой пароль — ошибка, остаемся на экране авторизации")
    fun tc004_loginWithEmptyPassword() {
        ensureOnAuthScreen()
        AuthPage()
            .enterLogin(LOGIN)
            .clickLoginExpectingError()
            .assertAuthScreenVisible()
    }

    @Test
    @Description("Неверные учетные данные — ошибка авторизации, остаемся на экране авторизации")
    fun tc005_loginWithWrongCredentials() {
        ensureOnAuthScreen()
        AuthPage()
            .enterLogin("wrong")
            .enterPassword("wrong")
            .clickLoginExpectingError()
            .assertAuthScreenVisible()
    }

    @Test
    @Description("Логин/пароль с пробелами по краям — триминг и успешная авторизация")
    fun tc006_loginWithTrimmedSpaces() {
        ensureOnAuthScreen()
        AuthPage()
            .enterLogin(" $LOGIN ")
            .enterPassword(" $PASSWORD ")
            .clickLogin()
            .assertMainScreenLoaded()
    }
}