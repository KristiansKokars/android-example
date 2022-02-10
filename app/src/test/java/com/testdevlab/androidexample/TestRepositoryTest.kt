package com.testdevlab.androidexample

import com.testdevlab.androidexample.repositories.Error
import com.testdevlab.androidexample.repositories.NetworkClient
import com.testdevlab.androidexample.repositories.Success
import com.testdevlab.androidexample.repositories.TestRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class TestRepositoryTest {

    @RelaxedMockK
    private lateinit var networkClient: NetworkClient

    private lateinit var repository: TestRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = TestRepositoryImpl(networkClient)
    }

    @Test
    fun `getTestData() should update local database on Success`() = runBlocking {
        // Given a successful response
        val mockData = listOf("a", "b", "c")
        every { networkClient.getTestData() } returns mockData

        // When getTestData is called
        val response = repository.getTestData().single()

        // Then the returned response is success
        assertTrue(response is Success)

        // And data is stored in data base
        assertEquals(repository.testItems.replayCache.last(), mockData)
    }

    @Test
    fun `getTestData() should not update local database on Error`() = runBlocking {
        // Given a successful response
        val errorMessage = "Failed to get data"
        every { networkClient.getTestData() } throws Exception(errorMessage)

        // When getTestData is called
        val response = repository.getTestData().single()

        // Then the returned response is error
        assertTrue(response is Error)
        assertEquals((response as Error).error, errorMessage)

        // And data is not stored in data base
        assertEquals(repository.testItems.replayCache.lastOrNull(), null)
    }
}
