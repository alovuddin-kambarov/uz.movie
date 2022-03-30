package uz.coder.uzmovie.models

import uz.coder.uzmovie.models.MovieClass
import java.io.Serializable

data class MainClass(
    val page: Int,
    val results: List<MovieClass>,
    val total_pages: Int,
    val total_results: Int
): Serializable