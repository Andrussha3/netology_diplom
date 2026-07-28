package ru.edu.qamid.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

class CreateEditNewsPage {

    @Step("Заполнение категории новости")
    fun enterCategory(category: String): CreateEditNewsPage {
        onView(withId(R.id.news_category_auto_complete)).perform(replaceText(category))
        return this
    }

    @Step("Заполнение заголовка новости")
    fun enterTitle(title: String): CreateEditNewsPage {
        onView(withId(R.id.news_title_edit_text)).perform(replaceText(title))
        return this
    }

    @Step("Заполнение описания новости")
    fun enterDescription(description: String): CreateEditNewsPage {
        onView(withId(R.id.news_description_edit_text)).perform(replaceText(description))
        return this
    }

    @Step("Сохранение новости")
    fun clickSave(): CreateEditNewsPage {
        onView(withId(R.id.news_save_button)).perform(click())
        return this
    }

    @Step("Отмена создания новости")
    fun clickCancel(): CreateEditNewsPage {
        onView(withId(R.id.news_cancel_button)).perform(click())
        return this
    }

    @Step("Заполнение всех полей новости")
    fun fillNews(category: String, title: String, description: String) {
        enterCategory(category)
        enterTitle(title)
        enterDescription(description)
    }
}
