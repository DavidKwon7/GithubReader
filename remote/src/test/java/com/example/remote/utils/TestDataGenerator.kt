package com.example.remote.utils

import com.example.remote.model.UserRemoteModel

class TestDataGenerator {

    companion object {
        fun generateUser(): List<UserRemoteModel> {
            val item1 = UserRemoteModel("name1", "url1")
            val item2 = UserRemoteModel("name2", "url2")
            val item3 = UserRemoteModel("name3", "url3")
            return listOf(item1, item2, item3)
        }
    }
}