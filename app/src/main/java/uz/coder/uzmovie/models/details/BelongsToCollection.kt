package uz.coder.uzmovie.models.details

import java.io.Serializable

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
):Serializable