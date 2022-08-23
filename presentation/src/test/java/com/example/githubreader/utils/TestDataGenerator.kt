package com.example.githubreader.utils

import com.example.domain.entity.UserEntityModel

class TestDataGenerator {

    companion object {
        fun generateUser(): List<UserEntityModel> {
            val item1 = UserEntityModel(1,1,"name1", "url1")
            val item2 = UserEntityModel(2,2,"name2", "url2")
            val item3 = UserEntityModel(3,3,"name3", "url3")
            return listOf(item1, item2, item3)
        }
    }
}