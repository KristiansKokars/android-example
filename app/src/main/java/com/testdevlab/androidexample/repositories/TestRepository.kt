package com.testdevlab.androidexample.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow

abstract class Response
class Success : Response()
class Error(val error: String) : Response()

interface TestRepository {
    val testItems: Flow<List<String>>
    fun getTestData(): Flow<Response>
    fun addTestData(data: String): Flow<Response>
    fun deleteTestData(data: String): Flow<Response>
}

class TestRepositoryImpl(
    private val networkClient: NetworkClient
) : TestRepository {

    private val itemDataBase = mutableListOf<String>()
    private val _testItems = MutableSharedFlow<List<String>>(replay = 1)

    override val testItems = _testItems.asSharedFlow()

    override fun getTestData() = flow {
        try {
            val items = networkClient.getTestData()
            itemDataBase.clear()
            itemDataBase.addAll(items)
            _testItems.emit(itemDataBase)
            emit(Success())
        } catch (e: Exception) {
            emit(Error(e.message!!))
        }
    }

    override fun addTestData(data: String) = flow {
        try {
            itemDataBase.add(networkClient.addTestData(data))
            _testItems.emit(itemDataBase)
            emit(Success())
        } catch (e: Exception) {
            emit(Error(e.message!!))
        }
    }

    override fun deleteTestData(data: String) = flow {
        try {
            itemDataBase.remove(networkClient.deleteTestData(data))
            _testItems.emit(itemDataBase)
            emit(Success())
        } catch (e: Exception) {
            emit(Error(e.message!!))
        }
    }

}
