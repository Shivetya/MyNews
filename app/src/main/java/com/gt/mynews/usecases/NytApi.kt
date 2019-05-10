package com.gt.mynews.usecases

import com.gt.mynews.data.ArticleApiResponse

import retrofit2.Call
import retrofit2.http.GET

interface NytApi {

    @GET("svc/mostpopular/v2/viewed/7.json")
    fun getMostPopularApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/home.json")
    fun getTopStoriesApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/science.json")
    fun getScienceApi() : Call<ArticleApiResponse>

    @GET("svc/topstories/v2/technology.json")
    fun getTechnologyApi() : Call<ArticleApiResponse>
}