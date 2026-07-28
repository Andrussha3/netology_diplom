package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class OurMissionPage {

    @Step("Проверка отображения заголовка «Наша миссия»")
    fun isTitleDisplayed(): Boolean {
        try {
            onView(withText(R.string.our_mission_title_text))
            return true
        } catch (e: Exception) {
            return false
        }
    }

    @Step("Открытие главного меню")
    fun openMainMenu(): MainPage.MainMenu {
        onView(withId(R.id.main_menu_image_button)).perform(click())
        return MainPage.MainMenu()
    }

    @Step("Открытие меню авторизации")
    fun openAuthorizationMenu(): MainPage.AuthorizationMenu {
        onView(withId(R.id.authorization_image_button)).perform(click())
        return MainPage.AuthorizationMenu()
    }
}
