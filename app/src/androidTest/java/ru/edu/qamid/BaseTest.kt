package ru.edu.qamid

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Step
import org.junit.After
import org.junit.Before
import org.junit.Rule
import ru.edu.qamid.auth.AppAuth
import ru.edu.qamid.ui.AppActivity
import javax.inject.Inject

@Epic("V Hospice")
abstract class BaseTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    private val context: Context = ApplicationProvider.getApplicationContext()
    private var scenario: ActivityScenario<AppActivity>? = null

    @Inject
    lateinit var appAuth: AppAuth

    companion object {
        const val LOGIN = "login2"
        const val PASSWORD = "password2"
    }

    @Before
    fun setUpBase() {
        hiltRule.inject()
        OkHttpIdlingResource.register()
        launchActivity()
        waitForAppToSettle()
    }

    @Step("Ожидание завершения splash screen")
    private fun waitForAppToSettle(timeoutMs: Long = 8_000) {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (System.currentTimeMillis() < deadline) {
            try {
                onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
                return
            } catch (_: Throwable) {}
            try {
                onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
                return
            } catch (_: Throwable) {}
            android.os.SystemClock.sleep(100)
        }
        throw AssertionError("App did not reach auth or main screen within ${timeoutMs}ms")
    }

    @After
    fun tearDownBase() {
        OkHttpIdlingResource.unregister()
        closeActivity()
    }

    @Step("Авторизация и ожидание главного экрана")
    protected fun loginAndWaitForMain() {
        if (isViewDisplayed(R.id.main_swipe_refresh)) return
        onView(withId(R.id.login_edit_text)).perform(ViewActions.typeText(LOGIN))
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText(PASSWORD))
        onView(withId(R.id.enter_button)).perform(ViewActions.closeSoftKeyboard()).perform(ViewActions.click())
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()))
    }

    protected fun ensureOnAuthScreen() {
        if (isViewDisplayed(R.id.login_edit_text)) return
        appAuth.authState = null
        context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit().clear().apply()
        launchActivity()
        onView(withId(R.id.login_edit_text)).check(matches(isDisplayed()))
    }

    protected fun isViewDisplayed(viewId: Int): Boolean {
        return try {
            onView(withId(viewId)).check(matches(isDisplayed()))
            true
        } catch (_: Exception) {
            false
        }
    }

    protected fun launchActivity() {
        scenario?.close()
        scenario = ActivityScenario.launch(AppActivity::class.java)
    }

    protected fun closeActivity() {
        scenario?.close()
        scenario = null
    }
}
