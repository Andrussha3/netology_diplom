package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import org.junit.Test
import ru.edu.qamid.BaseTest
import ru.edu.qamid.R
import ru.edu.qamid.page.MainPage

@HiltAndroidTest
@LargeTest
@Feature("Новости")
@Story("Просмотр списка новостей")
class NewsListTest : BaseTest() {

    @Test
    fun tc011_navigateToFullNewsList() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()))
        MainPage().clickAllNews()
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun tc012_navigateMainMenu() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun newsList_sortButton_clickable() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_sort_button)).perform(androidx.test.espresso.action.ViewActions.click())
    }

    @Test
    fun newsList_filterButton_visible() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_filter_button)).check(matches(isDisplayed()))
    }

    @Test
    fun newsList_navigateToFilterScreen() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun newsList_editButton_visible() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_edit_button)).check(matches(isDisplayed()))
    }

    @Test
    fun newsList_navigateToControlPanel() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun newsList_newsCards_visible() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
    }
}
