package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class MainPage {

    @Step("Проверка, что главная загрузилась")
    fun assertMainScreenLoaded(): MainPage {
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
        return this
    }

    @Step("Нажатие «Все новости»")
    fun clickAllNews(): NewsListPage {
        onView(withId(R.id.all_news_text_view)).perform(click())
        return NewsListPage()
    }

    @Step("Разворачивание блока новостей")
    fun clickExpandNewsBlock(): MainPage {
        onView(withId(R.id.expand_material_button)).perform(click())
        return this
    }

    @Step("Открытие главного меню")
    fun openMainMenu(): MainMenu {
        onView(withId(R.id.main_menu_image_button)).perform(click())
        return MainMenu()
    }

    @Step("Нажатие «Наша миссия»")
    fun clickOurMission(): OurMissionPage {
        onView(withId(R.id.our_mission_image_button)).perform(click())
        return OurMissionPage()
    }

    @Step("Открытие меню авторизации")
    fun clickAuthorizationMenu(): AuthorizationMenu {
        onView(withId(R.id.authorization_image_button)).perform(click())
        return AuthorizationMenu()
    }

    class MainMenu {
        @Step("Выбор пункта «Новости» в меню")
        fun clickNews(): NewsListPage {
            onView(withText(R.string.news)).perform(click())
            return NewsListPage()
        }

        @Step("Выбор пункта «Главная» в меню")
        fun clickMain(): MainPage {
            onView(withText(R.string.main)).perform(click())
            onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
            return MainPage()
        }
    }

    class AuthorizationMenu {
        @Step("Нажатие «Выйти»")
        fun clickLogout(): AuthPage {
            onView(withText(R.string.log_out)).perform(click())
            onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
            return AuthPage()
        }
    }
}