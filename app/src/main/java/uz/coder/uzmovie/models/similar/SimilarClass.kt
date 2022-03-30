package uz.coder.uzmovie.models.similar

import uz.coder.uzmovie.models.similar.Results
import java.io.Serializable

data class SimilarClass(
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
): Serializable