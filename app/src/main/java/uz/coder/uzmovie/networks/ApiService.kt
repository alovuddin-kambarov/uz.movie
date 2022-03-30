package uz.coder.uzmovie.networks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.adkhamjon.promovie.models.Images.ImageModel
import uz.coder.uzmovie.models.MainClass
import uz.coder.uzmovie.models.video.VideoModel

interface ApiService {


    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String = ApiClient.apiKey,
        @Query("language") category: String = "en-US",
        @Query("page") page: Int
    ): Response<MainClass>


    @GET("3/search/multi")
    suspend fun getSearchMovies(
        @Query("api_key") api_key: String = ApiClient.apiKey,
        @Query("language") category: String = "en-US",
        @Query("query") query: String,
    ): Response<MainClass>

    @GET("3/movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = ApiClient.apiKey,
        @Query("language") category: String = "en-US"
    ): Response<VideoModel>

}


