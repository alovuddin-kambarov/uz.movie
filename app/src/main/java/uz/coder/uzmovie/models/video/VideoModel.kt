package uz.coder.uzmovie.models.video

import uz.coder.uzmovie.models.video.Result

data class VideoModel(
    val id: Int,
    val results: List<Result>
)