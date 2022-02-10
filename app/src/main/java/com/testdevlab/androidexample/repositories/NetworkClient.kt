package com.testdevlab.androidexample.repositories

interface NetworkClient {
    fun getTestData(): List<String>
    fun addTestData(data: String): String
    fun deleteTestData(data: String): String
}
