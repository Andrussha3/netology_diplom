package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class FilterNewsPage {

    @Step("Применение фильтра")
    fun clickApply(): FilterNewsPage {
        onView(withId(R.id.filter_news_apply_button)).perform(click())
        return this
    }

    @Step("Отмена фильтра")
    fun clickCancel(): FilterNewsPage {
        onView(withId(R.id.filter_news_cancel_button)).perform(click())
        return this
    }

    @Step("Переключение чекбокса «Active»")
    fun clickActiveCheckbox(): FilterNewsPage {
        onView(withId(R.id.filter_news_active_check_box)).perform(click())
        return this
    }

    @Step("Переключение чекбокса «Inactive»")
    fun clickInactiveCheckbox(): FilterNewsPage {
        onView(withId(R.id.filter_news_inactive_check_box)).perform(click())
        return this
    }
}
