package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
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
@Story("Фильтрация новостей")
class NewsFilterTest : BaseTest() {

    @Test
    fun tc015_openFilterScreen() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun tc018_cancelFilterReturnsToNewsList() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_cancel_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun filterScreen_checkboxes_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_active_check_box)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_inactive_check_box)).check(matches(isDisplayed()))
    }

    @Test
    fun filterScreen_applyAndCancelButtons_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_apply_button)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_cancel_button)).check(matches(isDisplayed()))
    }

    @Test
    fun filterScreen_dateFields_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_date_start_text_input_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_date_end_text_input_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun filterScreen_filterFromControlPanel() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_filter_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_cancel_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
    }
}
