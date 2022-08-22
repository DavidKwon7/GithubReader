package com.example.local.utils

import com.example.local.model.UserLocalModel

class TestDataGenerator {

    companion object {
        fun generateUser(): List<UserLocalModel> {
            val item1 = UserLocalModel(1, "name1", "url1")
            val item2 = UserLocalModel(2, "name2", "url2")
            val item3 = UserLocalModel(3, "name3", "url3")
            return listOf(item1, item2, item3)
        }

        fun generateFavoriteItem(): UserLocalModel {
            return UserLocalModel(1, "name test", "url test")
        }
    }
}