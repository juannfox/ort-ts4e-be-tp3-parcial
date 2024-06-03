package com.example.parcial.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations")
data class DestinationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "image") val image: String)
