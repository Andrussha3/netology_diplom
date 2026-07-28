package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
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
@Feature("Наша миссия")
@Story("Экран «Наша миссия»")
class OurMissionTest : BaseTest() {

    @Test
    fun tc031_ourMissionScreenDisplays() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun ourMission_titleDisplayed() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
        onView(withText(R.string.our_mission_title_text)).check(matches(isDisplayed()))
    }

    @Test
    fun ourMission_mainMenu_navigatesToMain() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.main)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun ourMission_mainMenu_navigatesToNews() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.news)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun ourMission_logout_navigatesToAuth() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.authorization_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.log_out)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun ourMission_firstCard_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
        onView(withText("«Хоспис для меня - это то, каким должен быть мир.\"")).check(matches(isDisplayed()))
    }
}
