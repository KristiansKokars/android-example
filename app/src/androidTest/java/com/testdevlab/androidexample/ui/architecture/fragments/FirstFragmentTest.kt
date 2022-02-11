package com.testdevlab.androidexample.ui.architecture.fragments

import com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.rule.BaristaRule
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.ui.architecture.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FirstFragmentTest {

    @get:Rule
    val activityRule = BaristaRule.create(MainActivity::class.java)

    @Before
    fun setup() {
        activityRule.launchActivity()
        clickOn(R.id.open_next_button)
    }

    @Test
    fun settings_icon_visible_in_image_view() {
        // When fragment launched

        // Then settings icon drawable is displayed inside settings
        assertHasDrawable(R.id.settings, R.drawable.ic_settings)
    }
}
