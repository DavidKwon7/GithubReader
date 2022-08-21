package com.example.domain.utils

import com.example.domain.entity.UserEntityModel

class TestDataGenerator {

    companion object {
        fun generateUser(): List<UserEntityModel> {
            val item1 = UserEntityModel("name1", "url1")
            val item2 = UserEntityModel("name2", "url2")
            val item3 = UserEntityModel("name3", "url3")
            return listOf(item1, item2, item3)
        }
    }
}