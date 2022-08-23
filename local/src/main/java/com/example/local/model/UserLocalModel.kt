package com.example.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favoriteModel")
@Parcelize
data class UserLocalModel(
    @PrimaryKey val uid: Int?,
    val id: Int?,
    val name: String?,
    val url: String?
) : Parcelable