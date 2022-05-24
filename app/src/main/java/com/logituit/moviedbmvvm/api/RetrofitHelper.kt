package com.logituit.moviedbmvvm.api

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
@InstallIn(SingletonComponent::class)
@Module
object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    val moviesInstance: QuoteService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moviesInstance = retrofit.create(QuoteService::class.java)
    }
}
//    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
//        .readTimeout(15L, TimeUnit.SECONDS)
//        .connectTimeout(15L, TimeUnit.SECONDS)
//
//    fun getInstance(): Retrofit {
//
//        val client = provideOkHttpClientBuilder()
//            .addInterceptor { chain ->
//                val url = chain
//                    .request()
//                    .url()
//                    .newBuilder()
//                    .addQueryParameter("api_key", "689eeef66f23777a56e15ba2bdb4f9d8&language=en-US&page=1")
//                    .build()
//                chain.proceed(chain.request().newBuilder().url(url).build())
//            }
//            .build()
//        return Retrofit.Builder().client(client)
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}