package ru.edu.qamid.test

import androidx.test.espresso.Espresso
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
import ru.edu.qamid.page.MainPage

@HiltAndroidTest
@LargeTest
@Feature("Главный экран")
@Story("Основные элементы главного экрана")
class MainScreenTest : BaseTest() {

    @Test
    fun tc009_newsBlockDisplayedOnMainScreen() {
        loginAndWaitForMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun tc010_expandAndCollapseNewsBlock() {
        loginAndWaitForMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.expand_material_button)).perform(androidx.test.espresso.action.ViewActions.click())
    }

    @Test
    fun mainScreen_allNewsClick_navigatesToNewsList() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun mainScreen_ourMissionButton_navigatesToOurMission() {
        loginAndWaitForMain()
        onView(withId(R.id.our_mission_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun mainScreen_mainMenu_navigatesToNews() {
        loginAndWaitForMain()
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.news)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun mainScreen_mainMenu_navigatesToMainStaysOnMain() {
        loginAndWaitForMain()
        onView(withId(R.id.main_menu_image_button)).perform(androidx.test.espresso.action.ViewActions.click())
        onView(withText(R.string.main)).perform(androidx.test.espresso.action.ViewActions.click())
        Espresso.pressBack()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun mainScreen_appBarElements_visible() {
        loginAndWaitForMain()
        onView(withId(R.id.main_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed()))
    }
}
