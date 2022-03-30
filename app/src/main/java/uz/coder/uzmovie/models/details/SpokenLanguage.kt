package uz.coder.uzmovie.models.details

import java.io.Serializable

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
): Serializable