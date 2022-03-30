package uz.coder.uzmovie.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/"
    const val apiKey = "44f66b1676556437f4731985995f2dea"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()

    }

    var apiService: ApiService = getRetrofit().create(ApiService::class.java)

}