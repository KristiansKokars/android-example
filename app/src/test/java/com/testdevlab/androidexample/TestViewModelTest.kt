package com.testdevlab.androidexample

import app.cash.turbine.test
import com.testdevlab.androidexample.repositories.Error
import com.testdevlab.androidexample.repositories.Success
import com.testdevlab.androidexample.repositories.TestRepository
import com.testdevlab.androidexample.ui.TestViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class TestViewModelTest {

    @RelaxedMockK private lateinit var repository: TestRepository
    private lateinit var viewModel: TestViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Default)
        viewModel = TestViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTestData() on Success should reset the loading state`() = runBlocking {
        // Given
        every { repository.getTestData() } returns flowOf(Success())

        // When
        launch {
            viewModel.getTestData()
        }

        // Then
        viewModel.onLoading.test {
            assertTrue(awaitItem())
            assertFalse(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getTestData() on Error should reset the loading state and update error state`() = runBlocking {
        // Given
        val errorMessage = "Error"
        every { repository.getTestData() } returns flowOf(Error(errorMessage))

        // When
        launch {
            viewModel.getTestData()
        }

        // Then
        viewModel.onLoading.test {
            assertTrue(awaitItem())
            assertFalse(awaitItem())
            cancelAndConsumeRemainingEvents()
        }

        viewModel.onError.test {
            assertEquals(errorMessage, awaitItem())
        }
    }
}
