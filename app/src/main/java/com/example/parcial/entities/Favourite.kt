package com.example.parcial.entities

import com.example.parcial.data.database.entities.FavouriteEntity

data class Favourite(
    var type: String,
    var typeId: String
) {
}

fun FavouriteEntity.toDomain() = Favourite(type = type, typeId = typeId)
