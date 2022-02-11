package com.testdevlab.androidexample.ui.architecture.fragments

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import com.adevinta.android.barista.rule.BaristaRule
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.ui.architecture.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleFragmentTest {

    @get:Rule
    val activityRule = BaristaRule.create(MainActivity::class.java)

    @Before
    fun setup() {
        activityRule.launchActivity()
    }

    @Test
    fun example_fragment_visible_on_activity_launch() {
        // When activity is launched

        // Then ExampleFragment is displayed
        assertDisplayed(R.id.example_layout)
    }

    @Test
    fun increase_button_click_increase_user_score() {
        // Given user score is zero
        assertDisplayed(R.id.user_score, R.string.zero) // R.string.zero == "0"

        // When increase button is clicked
        clickOn(R.id.increase_button)

        // Then user score is increased by one
        assertDisplayed(R.id.user_score, "1")
    }

    @Test
    fun decrease_button_click_decrease_user_score() {
        // Given user score is zero
        assertDisplayed(R.id.user_score, R.string.zero) // R.string.zero == "0"

        // When decrease button is clicked
        clickOn(R.id.decrease_button)

        // Then user score is decreased by one
        assertDisplayed(R.id.user_score, "-1")
    }

    @Test
    fun writing_user_score_sets_new_value_on_click() {
        // Given newValueEditText has valid number
        val newScore = "12"
        writeTo(R.id.new_value_edittext, newScore)

        // When acceptButton is clicked
        clickOn(R.id.accept_button)

        // Then user score is changed
        assertDisplayed(R.id.user_score, newScore)
    }

    @Test
    fun writing_invalid_user_score_shows_an_error() {
        // Given newValueEditText has an invalid input
        val invalidInput = "12bad"
        writeTo(R.id.new_value_edittext, invalidInput)

        // When acceptButton is clicked
        clickOn(R.id.accept_button)

        // Then error message is displayed
        assertDisplayed(R.string.fragment_example_error)
    }

    @Test
    fun first_fragment_opens_on_button_click() {
        // When openNextButton is clicked
        clickOn(R.id.open_next_button)

        // Then FirstFragment is displayed
        assertDisplayed(R.id.first_fragment_layout)
    }

    @Test
    fun user_score_displayed_on_the_first_fragment() {
        // Given user score was changed
        clickOn(R.id.increase_button)

        // When openNextButton is clicked
        clickOn(R.id.open_next_button)

        // Then user score is displayed on the FirstFragment
        assertDisplayed(R.id.user_score, "1")
    }
}
