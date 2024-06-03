package com.example.parcial.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.parcial.entities.Favourite

@Entity(tableName = "favourites", primaryKeys = ["type", "type_id"])
data class FavouriteEntity(
    @ColumnInfo(name = "type") val type: String, //Tipo destino o tipo oferta //Podría ser un Enum
    @ColumnInfo(name = "type_id") val typeId: String, //Identificador del destino o de la oferta (destino: nombredetino+ciudad))
    // TODO: considerar que si existiera a función d elogin debería considerar en la clave el id del usuario
    //@ColumnInfo(name = "is_checked") val isChecked: Boolean
)

fun Favourite.toDatabase() = FavouriteEntity(type = type, typeId = typeId)
