package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class NewsControlPanelPage {

    @Step("Нажатие «Добавить новость»")
    fun clickAddNews(): CreateEditNewsPage {
        onView(withId(R.id.add_news_image_view)).perform(click())
        return CreateEditNewsPage()
    }

    @Step("Нажатие сортировки новостей")
    fun clickSort(): NewsControlPanelPage {
        onView(withId(R.id.news_sort_button)).perform(click())
        return this
    }

    @Step("Нажатие фильтра новостей")
    fun clickFilter(): FilterNewsPage {
        onView(withId(R.id.news_filter_button)).perform(click())
        return FilterNewsPage()
    }

    @Step("Открытие главного меню")
    fun openMainMenu(): MainPage.MainMenu {
        onView(withId(R.id.main_menu_image_button)).perform(click())
        return MainPage.MainMenu()
    }

    @Step("Нажатие «Наша миссия»")
    fun clickOurMission(): OurMissionPage {
        onView(withId(R.id.our_mission_image_button)).perform(click())
        return OurMissionPage()
    }

    @Step("Открытие меню авторизации")
    fun openAuthorizationMenu(): MainPage.AuthorizationMenu {
        onView(withId(R.id.authorization_image_button)).perform(click())
        return MainPage.AuthorizationMenu()
    }
}
