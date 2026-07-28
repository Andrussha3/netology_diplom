package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class AuthPage {

    @Step("Ввод логина: {login}")
    fun enterLogin(login: String): AuthPage {
        onView(withId(R.id.login_edit_text)).perform(ViewActions.typeText(login))
        return this
    }

    @Step("Ввод пароля: {password}")
    fun enterPassword(password: String): AuthPage {
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText(password))
        return this
    }

    @Step("Нажатие кнопки входа (ожидаем успех)")
    fun clickLogin(): MainPage {
        onView(withId(R.id.enter_button))
            .perform(ViewActions.closeSoftKeyboard())
            .perform(ViewActions.click())
        return MainPage()
    }

    @Step("Нажатие кнопки входа (ожидаем ошибку, остаемся на экране авторизации)")
    fun clickLoginExpectingError(): AuthPage {
        onView(withId(R.id.enter_button))
            .perform(ViewActions.closeSoftKeyboard())
            .perform(ViewActions.click())
        return this
    }

    @Step("Нажатие кнопки входа без закрытия клавиатуры")
    fun clickLoginWithoutCloseKeyboard(): AuthPage {
        onView(withId(R.id.enter_button)).perform(ViewActions.click())
        return this
    }

    @Step("Проверка, что экран авторизации отображается")
    fun assertAuthScreenVisible(): AuthPage {
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
        return this
    }
}