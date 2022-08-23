package com.example.data.utils

import com.example.data.model.UserDataModel
import com.example.domain.entity.UserEntityModel

class TestDataGenerator {

    companion object {
        fun generateUser(): List<UserDataModel> {
            val item1 = UserDataModel(1,"name1", "url1")
            val item2 = UserDataModel(2,"name2", "url2")
            val item3 = UserDataModel(3,"name3", "url3")
            return listOf(item1, item2, item3)
        }
    }
}