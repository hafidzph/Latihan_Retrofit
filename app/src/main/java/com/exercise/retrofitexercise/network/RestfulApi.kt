package com.exercise.retrofitexercise.network

import com.exercise.retrofitexercise.model.DataNews
import com.exercise.retrofitexercise.model.ResponseNews
import com.exercise.retrofitexercise.model.ResponseDataFilmItem
import com.exercise.retrofitexercise.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestfulApi {
    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm(): Call<List<ResponseDataFilmItem>>

    @POST("news")
    fun postDataNews(@Body bodyNews: DataNews): Call<ResponseNews>

    @PUT("news/{id}")
    fun putDataNews(@Path("id") id: Int,
                    @Body bodyNews: DataNews): Call<List<ResponseNews>>

    @PUT("news/{id}")
    fun getNewsById(@Path("id") id: Int): Call<ResponseDataNewsItem>
}