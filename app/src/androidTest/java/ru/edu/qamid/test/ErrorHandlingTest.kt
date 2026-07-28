package ru.edu.qamid.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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

@HiltAndroidTest
@LargeTest
@Feature("Авторизация")
@Story("Обработка ошибок авторизации")
class ErrorHandlingTest : BaseTest() {

    @Test
    fun tc005_wrongCredentials_shouldStayOnAuth() {
        ensureOnAuthScreen()
        onView(withId(R.id.login_edit_text)).perform(ViewActions.typeText("wrong_user"))
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText("wrong_pass"))
        onView(withId(R.id.enter_button)).perform(ViewActions.closeSoftKeyboard()).perform(ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun tc007_emptyCredentials_shouldStayOnAuth() {
        ensureOnAuthScreen()
        onView(withId(R.id.enter_button)).perform(ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyLoginField_shouldStayOnAuth() {
        ensureOnAuthScreen()
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText(PASSWORD))
        onView(withId(R.id.enter_button)).perform(ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyPasswordField_shouldStayOnAuth() {
        ensureOnAuthScreen()
        onView(withId(R.id.login_edit_text)).perform(ViewActions.typeText(LOGIN))
        onView(withId(R.id.enter_button)).perform(ViewActions.closeSoftKeyboard()).perform(ViewActions.click())
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }
}
