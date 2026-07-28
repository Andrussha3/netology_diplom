package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import org.junit.Test
import ru.edu.qamid.BaseTest
import ru.edu.qamid.R

@HiltAndroidTest
@LargeTest
@Feature("Новости")
@Story("Панель управления новостями")
class NewsControlPanelTest : BaseTest() {

    @Test
    fun tc019_openControlPanel() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_control_panel_swipe_to_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun tc023_createNewsScreen_opens() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.add_news_image_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_category_auto_complete)).check(matches(isDisplayed()))
    }

    @Test
    fun tc024_cancelCreatingNews() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.add_news_image_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_cancel_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.fragment_positive_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_swipe_to_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun controlPanel_sortButton_clickable() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_sort_button)).perform(androidx.test.espresso.action.ViewActions.click())
    }

    @Test
    fun controlPanel_filterButton_navigatesToFilter() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun controlPanel_mainMenu_navigatesToMain() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.main)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun controlPanel_mainMenu_navigatesToNews() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.news)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun controlPanel_ourMissionButton_navigates() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun controlPanel_recyclerView_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
    }
}
