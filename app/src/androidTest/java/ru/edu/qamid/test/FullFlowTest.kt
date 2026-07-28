package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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
@Feature("Комплексные сценарии")
@Story("Полные пользовательские сценарии")
class FullFlowTest : BaseTest() {

    @Test
    fun fullFlow_authToMainToNewsListToControlPanel() {
        loginAndWaitForMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))

        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))

        onView(withId(R.id.news_edit_button)).perform(ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.news_control_panel_swipe_to_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_authToMainToOurMissionAndBack() {
        loginAndWaitForMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))

        onView(withId(R.id.our_mission_image_button)).perform(ViewActions.click())
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()))

        MainPage().openMainMenu().clickMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_loginThenLogoutThenLoginAgain() {
        loginAndWaitForMain()
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))

        onView(withId(R.id.authorization_image_button)).perform(ViewActions.click())
        onView(withText(R.string.log_out)).perform(ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))

        onView(withId(R.id.login_edit_text)).perform(ViewActions.typeText(LOGIN))
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText(PASSWORD))
        onView(withId(R.id.enter_button)).perform(ViewActions.closeSoftKeyboard()).perform(ViewActions.click())
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_newsList_sortAndFilterNavigation() {
        loginAndWaitForMain()
        MainPage().openMainMenu().clickNews()
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))

        onView(withId(R.id.news_sort_button)).perform(ViewActions.click())

        onView(withId(R.id.news_filter_button)).perform(ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))

        onView(withId(R.id.filter_news_cancel_button)).perform(ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_mainScreen_newsBlockToggle() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.expand_material_button)).perform(ViewActions.click())

        onView(withId(R.id.expand_material_button)).perform(ViewActions.click())
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_splashScreen_initialState() {
        appAuth.authState = null
        getTargetContext().getSharedPreferences("auth", android.content.Context.MODE_PRIVATE).edit().clear().apply()
        launchActivity()
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_filterFromControlPanel() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))

        onView(withId(R.id.news_filter_button)).perform(ViewActions.click())
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_category_text_input_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_date_start_text_input_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_news_date_end_text_input_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.filter_news_cancel_button)).perform(ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun fullFlow_controlPanel_toNewsList_toMain() {
        loginAndWaitForMain()
        onView(withId(R.id.all_news_text_view)).perform(ViewActions.click())
        onView(withId(R.id.news_edit_button)).perform(ViewActions.click())
        onView(withId(R.id.news_control_panel_app_bar)).check(matches(isDisplayed()))

        onView(withId(R.id.main_menu_image_button)).perform(ViewActions.click())
        onView(withText(R.string.news)).perform(ViewActions.click())
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()))

        onView(withId(R.id.main_menu_image_button)).perform(ViewActions.click())
        onView(withText(R.string.main)).perform(ViewActions.click())
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    private fun getTargetContext(): android.content.Context {
        return androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
    }
}
